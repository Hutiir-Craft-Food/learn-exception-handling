package com.learn.exceptionhandling.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

public class ExceptionMessageBundle {
    private static final String bundleName = "i18n/Exceptions";

    public static String getMessage(ExceptionMessageKeys messageKey) {
        return ResourceBundle.getBundle(bundleName)
                .getString(messageKey.name());
    }

    public static String getLocalizedMessage(ExceptionMessageKeys messageKey, Locale locale) {
        return ResourceBundle.getBundle(bundleName, locale)
                .getString(messageKey.name());
    }
}
