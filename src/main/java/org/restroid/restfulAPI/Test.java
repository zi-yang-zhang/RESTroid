package org.restroid.restfulAPI;

import java.io.IOException;

/**
 * Created by robertzzy on 10/16/14.
 */
public class Test {

    public static void main(String... args){
        RESTConnector connector = new RESTConnector("admin","admin");
        connector.connect("http://127.0.0.1:5000/users/1");

        try {
            connector.client.execute(connector.getReq);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
