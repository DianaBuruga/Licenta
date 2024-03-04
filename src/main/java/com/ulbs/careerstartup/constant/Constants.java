package com.ulbs.careerstartup.constant;


import java.time.ZoneId;


public class Constants {

    private Constants() {
    }

    public static final String ROMANIAN_PHONE_REGEX = "^(\\+40|0040)?(7[0-8]\\d{7})$";
    public static final String ULBSIBIU_SUFFIX = "@ulbsibiu.ro";
    public static final String GMAIL_SUFFIX = "@gmail.";
    public static final ZoneId ROMANIA_TIMEZONE = ZoneId.of("Europe/Bucharest");
    public static final String ATTACHMENT_FILENAME = "attachment; filename=";
    public static final String INLINE_FILENAME = "inline; filename=";
    public static final String BY_CRITERIA = "/by-criteria/";
    public static final String NOT_FOUND = " not found";
}
