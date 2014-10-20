package org.restroid.restfulAPI;

/**
 * Created by Robert Zhang on 10/18/14.
 */
public class URIHelper {

    public static String requestUriFormatter(String type, String ID) {
        StringBuilder builder = new StringBuilder();
        return builder.append("/").append(type).append("/").append(ID).toString();
    }
}
