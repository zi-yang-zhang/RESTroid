package org.restroid.restfulAPI;

/**
 * Created by Robert Zhang on 10/18/14.
 */
public class URIGenerator {
    private StringBuilder uri;

    public URIGenerator() {
        uri = new StringBuilder();
    }

    public URIGenerator setRequestPath(String... types) {

        for (String type : types) {
            uri.append("/").append(type);
        }
        return this;
    }

    public URIGenerator setArguments(String arg) {
        uri.append("/").append(arg);
        return this;
    }

    @Override
    public String toString() {
        return uri.toString();
    }


}
