package com.ulbs.careerstartup.specification.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
public class SearchCriteria implements Serializable {

    private String key;
    private String operation;
    private Object value;
}

