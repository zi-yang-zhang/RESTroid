package org.restroid.restfulAPI;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthenticationException;

import java.io.IOException;

/**
 * Created by Robert Zhang on 10/18/14.
 */
public interface BasicInfoHandler {

    HttpResponse get(String ID) throws AuthenticationException, IOException;

    HttpResponse getAll() throws IOException, AuthenticationException;

    HttpResponse create(Object model) throws IOException;

    HttpResponse update();

    HttpResponse delete(String ID) throws IOException, AuthenticationException;
}
