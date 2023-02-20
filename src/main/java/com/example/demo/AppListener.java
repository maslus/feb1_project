package com.example.demo;

import com.example.demo.schedule.EveryDayUpdate;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServlet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@WebListener
public class AppListener extends HttpServlet implements ServletContextListener {

    ScheduledExecutorService service;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //Запустим задание по расписанию
        try {
            service = Executors.newScheduledThreadPool(1);
            service.scheduleAtFixedRate(new EveryDayUpdate(), 0, 1, TimeUnit.DAYS);
            service.awaitTermination(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }

}
