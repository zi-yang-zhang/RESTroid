package org.restroid.restfulAPI;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.impl.SimpleLog;
import org.apache.http.HttpResponse;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by robertzzy on 10/16/14.
 */
public class Test {
    static{
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");

        System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");

       System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http", "debug");


        System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.impl.conn", "debug");


    }
    public final static SimpleLog LOGGER = (SimpleLog)LogFactory.getLog(Test.class.getName());
    public static void main(String... args){
        LOGGER.setLevel(SimpleLog.LOG_LEVEL_ALL);
        LOGGER.trace("test");
        RESTConnector connector = new RESTConnector("admin","admin");
        connector.connect("http://127.0.0.1:5000/users/1");
            try {
                HttpResponse response = connector.client.execute(connector.getReq);
                System.out.println(response.getEntity().getContent());
            } catch (IOException e) {
                e.printStackTrace();
            }


    }
}
