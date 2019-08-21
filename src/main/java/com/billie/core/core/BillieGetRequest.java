package com.billie.core.core;

import com.billie.core.core.CoreRequest;
import com.billie.core.core.CoreResponse;
import org.apache.http.client.methods.HttpGet;

public class BillieGetRequest extends CoreRequest<HttpGet> {

    public BillieGetRequest(String baseUrl) {
        super(baseUrl);
        uriRequest = new HttpGet();
        contentType("application/json");
    }

    public CoreResponse run() {
        try {
            uriRequest.setURI(uri.build());
            return super.run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
