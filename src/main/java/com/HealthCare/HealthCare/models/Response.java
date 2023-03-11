package com.HealthCare.HealthCare.models;

public class Response {
    private String res;

    public Response(String res) {
        this.res = res;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    @Override
    public String toString() {
        return "Response{" +
                "res='" + res + '\'' +
                '}';
    }
}

