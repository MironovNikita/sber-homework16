package org.application;

import org.application.cachedProxy.CachedProxy;
import org.application.calculator.Calculator;
import org.application.calculator.CalculatorImpl;
import org.application.database.DataBase;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = CachedProxy.cached(new CalculatorImpl(), new DataBase());

        long startTime1 = System.currentTimeMillis();
        System.out.println("Ряд Фибоначчи для числа 20: " + calculator.fibonacci(20));
        System.out.println("Ряд Фибоначчи для числа 30: " + calculator.fibonacci(30));
        System.out.println("Ряд Фибоначчи для числа 40: " + calculator.fibonacci(40));
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Время, затраченное на работу без кэша: "
                + (System.currentTimeMillis() - startTime1) + " мc");
        System.out.println("---------------------------------------------------------------------------------------");

        long startTime2 = System.currentTimeMillis();
        System.out.println("Ряд Фибоначчи для числа 20: " + calculator.fibonacci(20));
        System.out.println("Ряд Фибоначчи для числа 30: " + calculator.fibonacci(30));
        System.out.println("Ряд Фибоначчи для числа 40: " + calculator.fibonacci(40));
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Время, затраченное на работу из кэша: "
                + (System.currentTimeMillis() - startTime2) + " мс");
        System.out.println("---------------------------------------------------------------------------------------");
    }
}