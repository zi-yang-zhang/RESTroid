package org.restroid.restfulAPI;


import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.net.URI;

/**
 * Created by Robert Zhang on 10/16/14.
 */
public class RESTConnector {
    private Credentials credentials;
    private CloseableHttpClient client;
    private HttpRequestBase request;
    private HttpClientContext context;
    private HttpHost host;

    private RESTConnector(RESTConnectorBuilder builder) {
        this.credentials = builder.credentials;
        this.client = builder.client;
        this.host = builder.host;
        this.context = builder.context;
    }

    public HttpResponse authenticatedConnect(String uri) throws IOException, AuthenticationException {
        request.setURI(URI.create(uri));
        BasicScheme authScheme = new BasicScheme();
        request.addHeader(authScheme.authenticate(credentials, request, context));
        return this.client.execute(host, request, context);
    }

    public HttpResponse unauthenticatedConnect(String uri) throws IOException {
        request.setURI(URI.create(uri));
        return this.client.execute(host, request, context);
    }

    public HttpRequestBase getRequest() {
        return this.request;
    }

    public RESTConnector setHTTPMethod(String methodType) {
        switch (methodType) {
            case HttpGet.METHOD_NAME:
                this.request = new HttpGet();
                break;
            case HttpPost.METHOD_NAME:
                this.request = new HttpPost();
                break;
            case HttpPut.METHOD_NAME:
                this.request = new HttpPut();
                break;
            case HttpDelete.METHOD_NAME:
                this.request = new HttpDelete();
                break;
        }
        return this;
    }

    public static class RESTConnectorBuilder {
        private CredentialsProvider credProvider;
        private Credentials credentials;
        private CloseableHttpClient client;
        private HttpClientContext context;
        private HttpHost host;

        public RESTConnectorBuilder(URI hostURI) {
            this.client = HttpClientSingletonGenerator.HTTP_CLIENT.SINGLETON.getHttpClient();
            this.host = new HttpHost(hostURI.getHost(), hostURI.getPort(), hostURI.getScheme());
        }

        public RESTConnectorBuilder setCredentials(String username, String password) {
            this.credProvider = new BasicCredentialsProvider();
            this.credentials = new UsernamePasswordCredentials(username, password);
            credProvider.setCredentials(new AuthScope(host.toHostString(), host.getPort()), credentials);
            this.context = HttpClientContext.create();
            //AuthCache authCache = new BasicAuthCache();
            context.setCredentialsProvider(credProvider);
            return this;
        }

        public RESTConnector build() {
            return new RESTConnector(this);
        }


    }
}