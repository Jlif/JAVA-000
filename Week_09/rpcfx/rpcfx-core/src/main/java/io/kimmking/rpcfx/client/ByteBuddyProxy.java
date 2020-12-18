package io.kimmking.rpcfx.client;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * @author jiangchen
 * @date 2020/12/18
 */
public class ByteBuddyProxy {
    //创建代理对象
    public <T> T getInstance(Class<T> clazz, Object handler) {
        Class<? extends T> klass = new ByteBuddy()
                .subclass(clazz)
                .method(ElementMatchers.isDeclaredBy(clazz))
                .intercept(MethodDelegation.to(handler))
                .make()
                .load(clazz.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded();
        T t = null;
        try {
            t = klass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }
}
