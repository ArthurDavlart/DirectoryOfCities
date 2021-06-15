package com.directoryofcities.common;

public class ConsoleLoggerImpl implements Logger{
    @Override
    public void write(String msg) {
        System.out.println(msg);
    }
}
