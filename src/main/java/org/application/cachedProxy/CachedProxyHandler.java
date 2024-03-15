package org.application.cachedProxy;

import org.application.database.Source;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

public class CachedProxyHandler implements InvocationHandler {
    private final Object target;
    private final Source source;

    public CachedProxyHandler(Object target, Source source) {
        this.target = target;
        this.source = source;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Cacheable.class)) {
            Integer number = (Integer) args[0];

            List<Integer> dbResult = source.getData(number);

            if (!dbResult.isEmpty()) {
                System.out.printf("Результат для значения %d найден в кэше%n", number);
                return dbResult;
            }

            try {
                System.out.printf("Сохранение результата для значения %d...%n", number);
                List<Integer> result = (List<Integer>) method.invoke(target, args);
                source.saveData(number, result);
                return result;
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
        return method.invoke(target, args);
    }
}
