package com.billie.core.core;

import com.billie.core.configuration.ClientManager;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;

import java.util.HashMap;
import java.util.Map;

public class CoreRequest<T extends HttpUriRequest> {
    protected final URIBuilder uri;
    protected T uriRequest;

    public CoreRequest(String baseUrl) {
        try {
            uri = new URIBuilder(baseUrl);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void path(String value) {
        uri.setPath(value);
    }

    public void contentType(String value) {
        uriRequest.setHeader("Content-Type", value);
    }


    public CoreResponse run() {
        try {
            CloseableHttpClient client = uriRequest.getURI().getScheme().equals("https") ?
                    ClientManager.configureHttpClient() :
                    ClientManager.configureHttpsClient();

            CoreResponse response;
            try (CloseableHttpResponse httpResponse = client.execute(uriRequest)) {

                int status = httpResponse.getStatusLine().getStatusCode();
                String body = httpResponse.getEntity() == null ? "" : IOUtils.toString(httpResponse.getEntity().getContent());

                Map<String, String> headers = new HashMap<>();
                for (Header header : httpResponse.getAllHeaders()) {
                    headers.put(header.getName(), header.getValue());
                }
                response = new CoreResponse(status, body, headers);
            }
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
