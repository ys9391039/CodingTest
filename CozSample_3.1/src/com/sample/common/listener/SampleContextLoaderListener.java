package com.sample.common.listener;

import javax.servlet.ServletContextEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ContextLoaderListener;

import com.sample.common.util.DefaultConfig;

public class SampleContextLoaderListener extends ContextLoaderListener {

    private Log log = LogFactory.getLog(SampleContextLoaderListener.class);
    
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            super.contextDestroyed(sce);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }
    
    public void contextInitialized(ServletContextEvent sce) { 
        try {
            
        	String contextPath = sce.getServletContext().getContextPath();
            
        	String country = "";
            if ( contextPath.lastIndexOf("-") > 0 ) {
                country = contextPath.replaceAll("/", "").substring(0, contextPath.indexOf("-"));
            }
            
            DefaultConfig.refreshConfiguration(contextPath, country);
            DefaultConfig.setContextPathMap(Thread.currentThread(), contextPath);
            
            super.contextInitialized(sce);
        } 
        catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }
}


