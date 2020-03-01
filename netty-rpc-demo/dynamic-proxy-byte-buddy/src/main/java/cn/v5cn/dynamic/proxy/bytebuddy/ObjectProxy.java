package cn.v5cn.dynamic.proxy.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * java.lang.Object类的代理，重写toString方法
 * @author ZYW
 * @version 1.0
 * @date 2020-03-01 21:36
 */
public class ObjectProxy {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Class<?> dynamicType = new ByteBuddy().subclass(Object.class)
                .method(ElementMatchers.named("toString"))
                .intercept(FixedValue.value("Hello World!"))
                .make()
                .load(ObjectProxy.class.getClassLoader())
                .getLoaded();

        System.out.println(dynamicType.newInstance().toString());
    }
}
