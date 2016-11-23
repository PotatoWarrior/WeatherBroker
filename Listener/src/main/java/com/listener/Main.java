package com.listener;

import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Main {
    public static void main(String[] args) throws Exception{
        new FileSystemXmlApplicationContext("Listener/src/applicationContext.xml");
    }
}
