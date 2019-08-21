package com.billie.core.configuration;

import com.billie.core.core.BillieException;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;

public class ClientManager {
    public static CloseableHttpClient configureHttpClient() {
        return HttpClients.createDefault();
    }

    public static CloseableHttpClient configureHttpsClient() {
        try {
            return HttpClients.custom()
                    .setSSLContext(new SSLContextBuilder()
                            .loadTrustMaterial(null, (certificate, authType) -> true)
                            .build())
                    .setSSLHostnameVerifier(new NoopHostnameVerifier())
                    .build();
        } catch (Exception e) {
            throw new BillieException("Client manager cannot configure client for SSL due to: \n" + e.getMessage());
        }
    }
}
