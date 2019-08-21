package com.billie.core.core;

import org.testng.Assert;

import java.util.Map;

public class CoreResponse {

    public static final JsonReader JSON_READER = new JsonReader();

    public final int status;
    public final String body;
    public final Map<String, String> headers;

    public CoreResponse(int status, String body, Map<String, String> headers) {
        this.status = status;
        this.body = body;
        this.headers = headers;
    }

    public CoreResponse verifyStatus(int expectedStatusCode) {
        Assert.assertEquals(status, expectedStatusCode, "The response does not contain " + expectedStatusCode + " Status Code");
        return this;
    }
}
