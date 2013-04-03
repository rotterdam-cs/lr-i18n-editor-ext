package com.liferay.portal.kernel.util;

import org.apache.commons.lang.StringUtils;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.text.MessageFormat;

import java.util.Locale;
import java.util.ResourceBundle;
import org.apache.commons.lang.StringUtils;

public class ResourceBundleUtil {
    public static final String NULL_VALUE = "NULL_VALUE";

    //private static final Log _logger = LogFactoryUtil.getLog(ResourceBundleUtil.class);
    
    public static String getString(
            ResourceBundle resourceBundle, Locale locale, String key,
            Object[] arguments) {
    	  	
    	String value = MessageSourceUtil.getMessage(locale, key);
    	if(StringUtils.isBlank(value)) {
    		value = getString(resourceBundle, key);
    	}

        if (value == null) {
            return null;
        }

        // Get the value associated with the specified key, and substitute any
        // arguuments like {0}, {1}, {2}, etc. with the specified argument
        // values.
        
        if ((arguments != null) && (arguments.length > 0)) {
            MessageFormat messageFormat = new MessageFormat(value, locale);
            value = messageFormat.format(arguments);
        }

        return value;
    }

    public static String getString(ResourceBundle resourceBundle, String key) {
    	   	
        ResourceBundleThreadLocal.setReplace(true);        
        String value = null;
        String defaultVal = value;
        value = MessageSourceUtil.getMessage(resourceBundle, key);
        if (value == null || StringUtils.equals(value, defaultVal)) {
            try {
                value = resourceBundle.getString(key);
            } finally {
                ResourceBundleThreadLocal.setReplace(false);
            }
        }
        if (NULL_VALUE.equals(value)) {
            value = null;
        }

        return value;
    }

    public static String getString(Locale locale, String key) {
    	   	
    	if(StringUtils.isBlank(key)) {
    		return null;
    	}    	
        String value = MessageSourceUtil.getMessage(locale, key);
        if (NULL_VALUE.equals(value)) {
            value = null;
        }
        return value;
    }
}
