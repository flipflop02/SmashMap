package com.smashmap.tournaments.wrapper;

public class GraphQLResponse {

    private JsonData data;

    private Object errors;

    public void setData(JsonData data) {
        this.data = data;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }

    public JsonData getData() {
        return data;
    }

    public Object getErrors() {
        return errors;
    }
}
