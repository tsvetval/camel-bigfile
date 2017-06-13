package com.github.sonenko.hellojavacamelwebsockets;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Created by tsval on 18.04.2017.
 */
public class BigFileDownloadTest {

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            Long i1 = new Long(1L);
            Integer i2 = new Integer(1);
            if (i1.equals(i2)){
                i1 = 100l;
            }

            final long length = 1000*1000*10000L;
            HttpGet httpget = new HttpGet("http://localhost:8081");
            System.out.println("Executing request: " + httpget.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httpget);
            Integer len = 10000;
            byte[] array = new byte[len];
            Integer i = 1;
            while ( response.getEntity().getContent().read(array) > 0) {
                System.out.println("read bytes " + (i.longValue() * len.longValue()));
                i++;
            }
            try {
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                System.out.println(EntityUtils.toString(response.getEntity()));
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }
}
