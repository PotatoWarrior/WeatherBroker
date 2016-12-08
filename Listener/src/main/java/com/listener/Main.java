package com.listener;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


public class Main extends HttpServlet {
    @Override
    public void init() throws ServletException {
        new ClassPathXmlApplicationContext("applicationContext.xml");
    }
}
