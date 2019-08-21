package com.billie.core.core;

import java.util.List;

public class MultipleResponse<T> extends CoreResponse {
    private Class<T> clazz;

    public MultipleResponse(CoreResponse parent, Class<T> clazz) {
        super(parent.status, parent.body, parent.headers);
        this.clazz = clazz;
    }

    public List<T> parsed() {
        return JSON_READER.deserializeArrayResponse(this, clazz);
    }

    @Override
    public MultipleResponse<T> verifyStatus(int expectedStatusCode) {
        super.verifyStatus(expectedStatusCode);
        return this;
    }
}
