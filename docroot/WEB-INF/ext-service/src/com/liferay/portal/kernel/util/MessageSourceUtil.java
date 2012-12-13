package com.liferay.portal.kernel.util;

import java.util.Locale;
import java.util.ResourceBundle;

//import java.lang.reflect.Field;
//import com.liferay.portal.model.PortletInfo;
//import com.liferay.portlet.PortletResourceBundle;
import com.rcs.service.NoSuchMessageSourceException;
import com.rcs.service.model.MessageSource;
import com.rcs.service.service.MessageSourceLocalServiceUtil;
import com.rcs.service.service.persistence.MessageSourcePK;

public class MessageSourceUtil {
	
    public static String getMessage(ResourceBundle resourceBundle, String key) {    	
    	/*
    	try {
        	String bundleName = "Default";
            Field portletInfoField = PortletResourceBundle.class.getDeclaredField("_portletInfo");
            portletInfoField.setAccessible(true);            
            PortletInfo portletInfo = (PortletInfo)portletInfoField.get(resourceBundle);
            bundleName = portletInfo.getTitle();
            System.out.println("MessageSourceUtil:getMessage:bundleName: " + bundleName);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }*/
    	
        try {
            if(key == null || resourceBundle == null){
                return  null;
            }
            Locale locale = null;
            try{
                locale = resourceBundle.getLocale();
            }catch (Exception ignored){}
            if(locale == null){
                locale  = Locale.US;
            }
            return getMessage(locale, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getMessage(Locale locale, String key) {    	    	      
        try {            
        	// It retrieves the value based on the key and locale, it does not take 
        	// into account the bundle name         	
        	MessageSourcePK messageSourcePK = new MessageSourcePK(key, locale.toString());
        	String value = null;        	
        	try {
        		MessageSource ms = MessageSourceLocalServiceUtil.getMessageSource(messageSourcePK);
        		value = ms.getValue();        		
        	} catch(NoSuchMessageSourceException ignored) {}
            return (String) value;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
