package org.application.calculator;

import org.application.cachedProxy.Cacheable;

import java.util.List;

public interface Calculator {
    @Cacheable
    List<Integer> fibonacci (int number);
}
