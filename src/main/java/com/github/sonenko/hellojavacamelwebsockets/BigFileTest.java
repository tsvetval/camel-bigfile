package com.github.sonenko.hellojavacamelwebsockets;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;

/**
 * Created by tsval on 17.04.2017.
 */
public class BigFileTest {

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            final long length = 1000*1000*10000L;

            HttpPut httpput = new HttpPut("http://localhost:8081");
           // File file = new File("c:\\tmp\\tttt.txt");

            final PipedOutputStream outputStream = new PipedOutputStream();
            final PipedInputStream inputStream = new PipedInputStream(outputStream);
            InputStreamEntity reqEntity = new InputStreamEntity(
                    inputStream /*new FileInputStream(file)*/, length/*-1*/, ContentType.APPLICATION_OCTET_STREAM);
            reqEntity.setChunked(false);
            // It may be more appropriate to use FileEntity class in this particular
            // instance but we are using a more generic InputStreamEntity to demonstrate
            // the capability to stream out data from any arbitrary source
            //
            // FileEntity entity = new FileEntity(file, "binary/octet-stream");

            httpput.setEntity(reqEntity);
           //httpput.setHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()));

            System.out.println("Executing request: " + httpput.getRequestLine());

            InputStreamWriterThread thread = new InputStreamWriterThread();
            thread.setOut(outputStream);
            thread.setLength(length);
            new Thread(thread).start();

            CloseableHttpResponse response = httpclient.execute(httpput);

/*            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            try {
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
               // System.out.println(EntityUtils.toString(response.getEntity()));
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }
}
