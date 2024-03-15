package org.application.cachedProxy;

import org.application.calculator.Calculator;
import org.application.database.Source;

import java.lang.reflect.Proxy;

public class CachedProxy {
    public static Calculator cached(Calculator calculator, Source source) {
        return (Calculator) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                new Class[]{Calculator.class},
                new CachedProxyHandler(calculator, source)
        );
    }
}
