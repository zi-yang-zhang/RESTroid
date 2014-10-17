package org.restroid.restfulAPI;


import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.net.URI;
import java.security.Principal;

/**
 * Created by robertzzy on 10/16/14.
 */
public class RESTConnector {
    CredentialsProvider credProvider = new BasicCredentialsProvider();
    Credentials credentials;
    HttpClient client;
    HttpGet getReq;

    public RESTConnector(String username, String password) {
        credentials = new UsernamePasswordCredentials(username,password);
        credProvider.setCredentials(AuthScope.ANY,credentials);
    }

    public void connect(String uri){
        client = HttpClientBuilder.create().setDefaultCredentialsProvider(credProvider).build();
        getReq = new HttpGet(uri);
    }



}