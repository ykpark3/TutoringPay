package com.pay.tutoring;

public class AppManager {
    private static AppManager instance = null;

    private AppManager() {
    }

    public static AppManager getInstance() {
        if (instance == null)
            instance = new AppManager();
        return instance;
    }
}
