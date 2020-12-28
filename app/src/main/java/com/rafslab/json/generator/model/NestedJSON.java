package com.rafslab.json.generator.model;

import java.io.Serializable;

public class NestedJSON implements Serializable {
    private String fieldName;
    private String fieldValue;

    public NestedJSON() {
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }
}
