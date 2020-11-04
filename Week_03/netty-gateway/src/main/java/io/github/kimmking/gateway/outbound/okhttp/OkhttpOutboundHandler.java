package io.github.kimmking.gateway.outbound.okhttp;

import com.google.common.collect.Lists;
import io.github.kimmking.gateway.outbound.httpclient4.NamedThreadFactory;
import io.github.kimmking.gateway.router.HttpEndpointRouterService;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.*;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class OkhttpOutboundHandler {

    private static final Logger logger = LoggerFactory.getLogger(OkhttpOutboundHandler.class);


    private OkHttpClient httpclient = new OkHttpClient();
    private ExecutorService proxyService;
    private String backendUrl;
    private HttpEndpointRouterService routerService;

    public OkhttpOutboundHandler(String backendUrl) {
        this.backendUrl = backendUrl.endsWith("/") ? backendUrl.substring(0, backendUrl.length() - 1) : backendUrl;
        int cores = Runtime.getRuntime().availableProcessors() * 2;
        long keepAliveTime = 1000;
        int queueSize = 2048;
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();//.DiscardPolicy();
        proxyService = new ThreadPoolExecutor(cores, cores,
                keepAliveTime, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(queueSize),
                new NamedThreadFactory("proxyService"), handler);
        routerService = new HttpEndpointRouterService();
    }

    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx) {
//        final String url = this.backendUrl + fullRequest.uri();
        List<String> routers = Lists.newArrayList("http://localhost:8801", "http://localhost:8802", "http://localhost:8803");
        //通过路由进行负载均衡
        final String url = routerService.route(routers);

        //换成自己之前用Okhttp写的 http GET 调用
        proxyService.submit(() -> fetchGet(fullRequest, ctx, url));
    }

    private void fetchGet(final FullHttpRequest inbound, final ChannelHandlerContext ctx, final String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = httpclient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                logger.error("onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    handleResponse(inbound, ctx, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                logger.info("onResponse: " + response.body().toString());
            }
        });
    }

    private void handleResponse(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, final Response endpointResponse) throws Exception {
        FullHttpResponse response = null;
        try {
            byte[] body = endpointResponse.body().bytes();

            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", Integer.parseInt(endpointResponse.header("Content-Length")));

        } catch (Exception e) {
            e.printStackTrace();
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
            exceptionCaught(ctx, e);
        } finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    //response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(response);
                }
            }
            ctx.flush();
            //ctx.close();
        }
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
