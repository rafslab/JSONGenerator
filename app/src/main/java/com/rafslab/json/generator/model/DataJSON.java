package com.rafslab.json.generator.model;

import java.io.Serializable;
import java.util.List;

public class DataJSON implements Serializable {
    private String fieldName;
    private String fieldValue;
    private List<NestedJSON> nestedJSONList;

    public DataJSON() {
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

    public List<NestedJSON> getNestedJSONList() {
        return nestedJSONList;
    }

    public void setNestedJSONList(List<NestedJSON> nestedJSONList) {
        this.nestedJSONList = nestedJSONList;
    }
}
