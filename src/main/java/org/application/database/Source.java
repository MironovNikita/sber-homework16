package org.application.database;

import java.util.List;

public interface Source {
    void saveData(int number, List<Integer> data);
    List<Integer> getData(int number);
}
