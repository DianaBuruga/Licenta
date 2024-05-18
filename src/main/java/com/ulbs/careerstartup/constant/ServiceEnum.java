package com.ulbs.careerstartup.constant;

public enum ServiceEnum {
    BIBLIOGRAPHY("bibliographies"),
    COMPANY("companies"),
    COURSE("courses"),
    EVENT("events"),
    EXPERIENCE("experiences"),
    FACULTY("faculties"),
    JOB_CANDIDATE("jobCandidates"),
    JOB_HISTORY("jobHistories"),
    LANGUAGE("languages"),
    MESSAGE("messages"),
    NOTIFICATION("notifications"),
    POSTED_JOB("postedJobs"),
    REFERRAL("referrals"),
    REVIEW("reviews"),
    SKILL("skills"),
    SPECIALIZATION("specializations"),
    USER("users");

    private String value;

    ServiceEnum(String value) {
        this.value = value;
    }

    public static ServiceEnum getByValue(String value) {
        for (ServiceEnum enumValue : ServiceEnum.values()) {
            if (enumValue.getValue().equals(value)) {
                return enumValue;
            }
        }
        throw new IllegalArgumentException("No enum constant for value: " + value);
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return formatEnumName(super.toString());
    }

    private String formatEnumName(String name) {
        String[] parts = name.split("_");
        StringBuilder formattedName = new StringBuilder();
        formattedName.append("com.ulbs.careerstartup.service.");
        for (String part : parts) {
            formattedName.append(part.substring(0, 1).toUpperCase())
                    .append(part.substring(1).toLowerCase());
        }
        formattedName.append("Service");
        return formattedName.toString();
    }
}
