package com.liferay.portal.kernel.util;

import java.util.Locale;
import java.util.ResourceBundle;

import com.rcs.service.NoSuchMessageSourceException;
import com.rcs.service.model.MessageSource;
import com.rcs.service.service.MessageSourceLocalServiceUtil;
import com.rcs.service.service.persistence.MessageSourcePK;

public class MessageSourceUtil {
	
    public static String getMessage(ResourceBundle resourceBundle, String key) {

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
            /*Object com.rcs.i18n.common.service.MessageSourceService messageSourceService = ObjectFactory.getBean("messageSourceService");
            Object value = messageSourceService.getClass().getMethod("getMessage", String.class, Locale.class).invoke(messageSourceService, key, locale);*/
        	
            /*MessageSource message = messageSourcePersistence.getMessage(key, locale.toString());
            return message == null ? key : message.getValue();*/
        	//String value = messageSourceService.getMessage(key, locale);
        	MessageSourcePK messageSourcePK = new MessageSourcePK(key, locale.toString());
        	String value = null;        	
        	try {
        		MessageSource ms = MessageSourceLocalServiceUtil.getMessageSource(messageSourcePK);
        	//	MessageSource message = ms.getMessage(key, locale.toString());
        		value = ms.getValue();
        		System.out.println("value: " + value);
        	} catch(NoSuchMessageSourceException ex) {
        		return null;
        	}
        	//String value = MessageSourceUtil.getMessage(locale, key);
        	//MessageSourcePersistence.get
            return (String) value;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
