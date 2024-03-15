package org.application.calculator;

import java.util.ArrayList;
import java.util.List;

public class CalculatorImpl implements Calculator {

    @Override
    public List<Integer> fibonacci(int number) {
        if (number < 0 || number > 46) {
            throw new IllegalArgumentException("Переданное число вне допустимого диапазона от 0 до 46 включительно!");
        }
        List<Integer> result = new ArrayList<>();

        if (number == 0) {
            result.add(0);
            return result;
        }

        result.add(0);
        result.add(1);

        for (int i = 2; i <= number; i++) {
            result.add(result.get(i - 1) + result.get(i - 2));
        }

        return result;
    }
}
