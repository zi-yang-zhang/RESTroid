package org.restroid.restfulAPI;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.impl.SimpleLog;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthenticationException;
import org.restroid.restfulAPI.org.restroid.models.UserModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

/**
 * Created by Robert Zhang on 10/16/14.
 */
public class Test {
    static {
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");

        System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");

        System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http", "debug");


        System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.impl.conn", "debug");


    }

    public final static SimpleLog LOGGER = (SimpleLog) LogFactory.getLog(Test.class.getName());

    public static void main(String... args) throws URISyntaxException {
        LOGGER.setLevel(SimpleLog.LOG_LEVEL_ALL);
        LOGGER.trace("test");
        InputStreamReader inputReader = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(inputReader);
        while (true) {
            System.out.println("Enter Http Method:");
            try {
                switch (reader.readLine()) {
                    case "GET":
                        InfoHandler getHandler = new InfoHandler("user", "admin", "admin"); //This is use for GET and DELETE request

                        try {
                            System.out.println("UserID:");
                            HttpResponse response = getHandler.get(reader.readLine());//This is for getting user with specific UserID
                            readFromResponce(response);
                        } catch (AuthenticationException e) {
                            e.printStackTrace();
                        } finally {
                            LOGGER.debug("Closing connection...");
                        }
                        break;
                    case "GETALL":
                        InfoHandler getAllHandler = new InfoHandler("user", "admin", "admin"); //This is use for GET and DELETE request
                        try {
                            HttpResponse response = getAllHandler.getAll();//This is for getting all users
                            readFromResponce(response);
                        } catch (AuthenticationException e) {
                            e.printStackTrace();
                        } finally {
                            LOGGER.debug("Closing connection...");
                        }
                        break;
                    case "POST":
                        InfoHandler postHandler = new InfoHandler("user"); //This is use for POST request
                        try {
                            UserModel newUser = new UserModel();
                            System.out.println("UserName:");
                            newUser.setUsername(reader.readLine());
                            System.out.println("Email:");
                            newUser.setEmail(reader.readLine());
                            System.out.println("Password:");
                            newUser.setPassword(reader.readLine());
                            HttpResponse response = postHandler.create(newUser); //This is for create a new user
                            readFromResponce(response);
                        } finally {
                            LOGGER.debug("Closing connection...");
                        }
                        break;
                    case "DELETE":
                        InfoHandler deleteHandler = new InfoHandler("user", "admin", "admin"); //This is use for GET and DELETE request
                        try {
                            System.out.println("UserID:");
                            HttpResponse response = deleteHandler.delete(reader.readLine());//This is for delete user with specific UserID
                            readFromResponce(response);
                        } catch (AuthenticationException e) {
                            e.printStackTrace();
                        } finally {
                            LOGGER.debug("Closing connection...");
                        }
                        break;
                    default:
                        System.out.println("Error, enter valid Http Methods");
                        break;

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }


    private static void readFromResponce(HttpResponse response) throws IOException {
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatusLine().getStatusCode());
        }

        BufferedReader br = new BufferedReader(
                new InputStreamReader((response.getEntity().getContent())));

        String output;
        System.out.println("Output from Server .... \n");
        while ((output = br.readLine()) != null) {
            System.out.println(output);
        }
    }
}
