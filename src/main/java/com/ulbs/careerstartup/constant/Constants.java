package com.ulbs.careerstartup.constant;


import java.time.ZoneId;


public class Constants {

    public static final String PHONE_REGEX = "^(\\+?[0-9]{1,5})?[0]?[7-9][0-9]{8}$";
    public static final String ULBSIBIU_SUFFIX = "@ulbsibiu.ro";
    public static final String GMAIL_SUFFIX = "@gmail.";
    public static final ZoneId ROMANIA_TIMEZONE = ZoneId.of("Europe/Bucharest");
    public static final String ATTACHMENT_FILENAME = "attachment; filename=";
    public static final String INLINE_FILENAME = "inline; filename=";
    public static final String BY_CRITERIA = "/by-criteria/";
    public static final String NOT_FOUND = " not found";
    public static final String FIND_BY_CRITERIA = "findByCriteria";
    public static final String DESCRIPTION = "description";
    public static final String NAME = " name";

    private Constants() {
    }
}
