package org.restroid.restfulAPI;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.impl.SimpleLog;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 * Created by Robert Zhang on 10/18/14.
 */
public class HttpClientSingletonGenerator {

    private static final SimpleLog LOGGER = (SimpleLog) LogFactory.getLog(Test.class.getName());

    public enum HTTP_CLIENT {
        SINGLETON;

        private final CloseableHttpClient httpClient;

        private HTTP_CLIENT() {
            PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
            this.httpClient = HttpClientBuilder.create().setConnectionManager(connectionManager).build();
        }

        public CloseableHttpClient getHttpClient() {
            return this.httpClient;
        }

    }
}
