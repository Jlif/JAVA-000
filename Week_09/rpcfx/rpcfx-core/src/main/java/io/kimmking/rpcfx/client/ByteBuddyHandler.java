package io.kimmking.rpcfx.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author jiangchen
 * @date 2020/12/18
 */
public class ByteBuddyHandler {

    static {
        ParserConfig.getGlobalInstance().addAccept("io.kimmking");
    }

    public static final MediaType JSONTYPE = MediaType.get("application/json; charset=utf-8");
    //复用 OkHttpClient
    private final static OkHttpClient client = new OkHttpClient();
    private String serviceName;
    private String url;

    public ByteBuddyHandler(String serviceName, String url) {
        this.serviceName = serviceName;
        this.url = url;
    }

    // 可以尝试，自己去写对象序列化，二进制还是文本的，，，rpcfx是xml自定义序列化、反序列化，json: code.google.com/p/rpcfx
    // int byte char float double long bool
    // [], data class

    @RuntimeType
    public Object interceptor(@Origin Method method, @AllArguments Object[] params) throws Throwable {
        RpcfxRequest request = new RpcfxRequest();
        request.setServiceClass(serviceName);
        request.setMethod(method.getName());
        request.setParams(params);

        RpcfxResponse response = post(request, url);

        // 这里判断response.status，处理异常
        // 考虑封装一个全局的RpcfxException

        return JSON.parse(response.getResult().toString());
    }

    private RpcfxResponse post(RpcfxRequest req, String url) throws IOException {
        String reqJson = JSON.toJSONString(req);
        System.out.println("req json: " + reqJson);

        // 1.可以复用client
        // 2.尝试使用httpclient或者netty client
        final Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(JSONTYPE, reqJson))
                .build();
        String respJson = client.newCall(request).execute().body().string();
        System.out.println("resp json: " + respJson);
        return JSON.parseObject(respJson, RpcfxResponse.class);
    }

}
