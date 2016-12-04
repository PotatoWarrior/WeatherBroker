package com.listener;

import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {
    public static void main(String[] args) throws Exception{
        new FileSystemXmlApplicationContext("Listener/src/applicationContext.xml");
    }
}
