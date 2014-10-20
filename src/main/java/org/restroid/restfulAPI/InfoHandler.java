package org.restroid.restfulAPI;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Robert Zhang on 10/18/14.
 */
public class InfoHandler implements BasicInfoHandler {
    private RESTConnector connector;
    private URI hostURI;
    private String infoType;

    public InfoHandler(String infoType) throws URISyntaxException {
        hostURI = new URI("http://127.0.0.1:5000");
        connector = new RESTConnector.RESTConnectorBuilder(hostURI).build();
        this.infoType = infoType;
    }

    public InfoHandler(String infoType, String username, String password) throws URISyntaxException {
        hostURI = new URI("http://127.0.0.1:5000");
        connector = new RESTConnector.RESTConnectorBuilder(hostURI).setCredentials(username, password).build();
        this.infoType = infoType;
    }

    @Override
    public HttpResponse get(String ID) throws AuthenticationException, IOException {
        return connector.setHTTPMethod(HttpGet.METHOD_NAME).authenticatedConnect(URIHelper.requestUriFormatter(infoType, ID));
    }

    @Override
    public HttpResponse getAll() throws IOException, AuthenticationException {
        return connector.setHTTPMethod(HttpGet.METHOD_NAME).authenticatedConnect(URIHelper.requestUriFormatter(infoType, "all"));
    }

    @Override
    public HttpResponse create(Object model) throws IOException {
        HttpPost postRequest = (HttpPost) connector.setHTTPMethod(HttpPost.METHOD_NAME).getRequest();
        Gson postJson = new Gson();
        postRequest.setEntity(new StringEntity(postJson.toJson(model), ContentType.APPLICATION_JSON));
        return connector.unauthenticatedConnect(URIHelper.requestUriFormatter(infoType, "new"));
    }

    @Override
    public HttpResponse update() {
        return null;
    }

    @Override
    public HttpResponse delete(String ID) throws IOException, AuthenticationException {
        return connector.setHTTPMethod(HttpDelete.METHOD_NAME).authenticatedConnect(URIHelper.requestUriFormatter(infoType, ID));
    }

}
