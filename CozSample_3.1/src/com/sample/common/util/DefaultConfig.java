package com.sample.common.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.configuration.CombinedConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DefaultConfig {

    /** The log. */
    private static Log log = LogFactory.getLog(DefaultConfig.class);
    public static ConcurrentHashMap<String, CombinedConfiguration> configurationMap;
    public static ConcurrentHashMap<Thread, String> contextPathMap;

    private static Thread	lastThread = null;
    
    protected static Map<String, CombinedConfiguration> getConfigurationMap() {
        return configurationMap;
    }

    /**
     * Refresh the configuration .
     */
    public static void refreshConfiguration(String contextPath, String country) {
        CombinedConfiguration config = init(contextPath, country);
        setConfigurationMap(contextPath, config);
    }

    /**
     * Set the context path map.<br/>
     * key = Thread
     *  
     * @param thread
     * @param contextPath
     */
    public static void setContextPathMap(Thread thread, String contextPath) {
        if (contextPathMap == null) {
            contextPathMap = new ConcurrentHashMap<Thread, String>(); 
        }
        lastThread = thread;
        contextPathMap.put(lastThread, contextPath);
    }
    
    /**
     * key = contextPath
     * 
     * @param contextPath
     * @return
     */
    public static void setConfigurationMap(String contextPath, CombinedConfiguration config) {
        if (configurationMap == null) {
            configurationMap = new ConcurrentHashMap<String, CombinedConfiguration>();
        }
        configurationMap.put(contextPath, config);
    }

    /**
     * Initializations the configuration.
     * 
     * @return the combined configuration
     */
    private static CombinedConfiguration init(String contextPath, String country) {
        
        CombinedConfiguration config = new CombinedConfiguration();
        String serverType = null;
        
        try {
            //XMLConfiguration systemConf = new XMLConfiguration("D:\\Workspace\\NaumAD\\DADAdmin\\WebContent\\WEB-INF\\config\\systemConf.xml");
            XMLConfiguration systemConf = new XMLConfiguration("D:\\Workspace\\Sample\\CozSample_3.1\\config\\properties\\local_properties2.xml");
            //XMLConfiguration localeConf = new XMLConfiguration(getConfigFileName("localeConf"));

            /* configuration files combine */
            config.addConfiguration(systemConf);
            //config.addConfiguration(localeConf);
            
            config.setForceReloadCheck(true);
            
        }
        catch ( ConfigurationException e ) {
            config = null;
            log.error(e, e);
        }
        return config;
    }
 
    /**
     * 
     */
    private static CombinedConfiguration getProperConfig() {
        String contextPath = "";
        CombinedConfiguration config = null;
        if (contextPathMap != null && !contextPathMap.isEmpty()) {
            contextPath = contextPathMap.get(lastThread);
        }
        
        if ( configurationMap != null && !configurationMap.isEmpty() && contextPath != null) {
            config = configurationMap.get(contextPath);
        }
        return config;
    }
 
    /**
     * Gets the string.
     * 
     * @param key the key
     * 
     * @return the string
     */
    public static String getString(String key) {
        CombinedConfiguration config = getProperConfig();
        if ( config != null) {
            return config.getString(key);
        }
        else {
            return null;
        }
    }

}
