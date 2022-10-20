package com.prisa.poc.utils;

public class Flags {

    /** Variables */

    private static final String BROWSER = "browser";
    private final String browser = System.getProperty(BROWSER);
    private static Flags instance;

    /** Methods */

    public static Flags getInstance() {
        if (instance == null) { instance = new Flags(); }
        return instance;
    }

    public String getBrowser() { return this.browser; }
}