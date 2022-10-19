package com.prisa.poc.utils;

public class Flags {

    /** Variables */

    private static final String BROWSER = "browser";
    private static final String HEADLESS = "headless";
    private final String browser = System.getProperty(BROWSER);
    private final boolean isHeadless = this.parseBoolean(System.getProperty(HEADLESS));
    private static Flags instance;


    /** Methods */

    private boolean parseBoolean(String string) {
        String result = string == null ? "false" : string;
        result = result.toLowerCase().trim();
        return (result.equals("true") || result.equals("false")) && Boolean.parseBoolean(result);
    }

    public static Flags getInstance() {
        if (instance == null) { instance = new Flags(); }
        return instance;
    }

    public String getBrowser() { return this.browser; }
}