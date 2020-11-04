package io.github.kimmking.gateway.router;

import java.util.List;

/**
 * @author jiangchen
 * @date 2020/11/04
 */
public class HttpEndpointRouterService implements HttpEndpointRouter {

    /**
     * 根据路由列表随机获取路由
     *
     * @param endpoints
     * @return
     */
    @Override
    public String route(List<String> endpoints) {
        int index = (int) (Math.random() * endpoints.size());
        return endpoints.get(index);
    }
}
