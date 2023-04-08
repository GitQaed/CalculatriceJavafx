package models;

import java.util.ArrayList;

public class History {
    private static ArrayList<Calculs> historyCalculs = new ArrayList<Calculs>();

    public static ArrayList<Calculs> getHistoryCalculs() {
        return historyCalculs;
    }
}
