package io.kimmking.rpcfx.client;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jiangchen
 * @date 2020/12/18
 */
public class ByteBuddyProxy {

    private final static ConcurrentHashMap<String, Object> proxyMap = new ConcurrentHashMap<>(16);

    //创建代理对象
    public static <T> T getInstance(Class<T> clazz, String url) {
        String name = clazz.getName();
        if (proxyMap.containsKey(name)) {
            return (T) proxyMap.get(name);
        }
        Class<?> klass = new ByteBuddy()
                .subclass(Object.class)
                .implement(clazz)
//                .name(clazz.getName() + "$ByteBuddy")
                .method(ElementMatchers.any())
                .intercept(MethodDelegation.to(new ByteBuddyHandler(clazz.getName(), url)))
                .make()
                .load(ByteBuddyProxy.class.getClassLoader())
                .getLoaded();
        T t = null;
        try {
            t = (T) klass.newInstance();
            proxyMap.put(name, t);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }
}
