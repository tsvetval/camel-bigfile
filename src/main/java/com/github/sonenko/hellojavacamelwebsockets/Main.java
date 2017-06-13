package com.github.sonenko.hellojavacamelwebsockets;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
/*import org.apache.camel.component.netty4.NettyConfiguration;
import org.apache.camel.component.netty4.http.NettyHttpComponent;
import org.apache.camel.component.netty4.http.NettyHttpConfiguration;*/
import org.apache.camel.impl.DefaultCamelContext;

import java.util.HashMap;


/*
*
*
ServletInputStream inputStream =  (ServletInputStream)exchange.getIn().getBody();
FileOutputStream out = new FileOutputStream("c:/tmp/tttt.txt");


byte[] b = new byte[16000];
int total = 0;
int count = -1;
while ((count = inputStream.read(b)) != -1) {
    out.write(b, 0, count);
    total +=count;
}
total;

* */

public class Main {
    public static void main(String args[]) throws Exception {
        CamelContext context = new DefaultCamelContext();
       // context.setStreamCaching(false);
/*
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("AUTO_READ", false);
        NettyConfiguration configuration = ((NettyHttpComponent)context.getComponent("netty4-http")).getConfiguration();
        configuration.setOptions(options);
*/
/*&disableStreamCache=true*/
        RouteBuilder routes = new RouteBuilder() {
            public void configure() {
                from(String.format("jetty:http://0.0.0.0:%d/?matchOnUriPrefix=true&disableStreamCache=true", 8081))
                        .id("nettyInputRoute")
                        .process(new Processor() {
                            public void process(Exchange exchange) throws Exception {
                                int i = 1;
                            }
                        })
                        .to("http4:localhost:8080/storage-service/v1/1/files/1?bridgeEndpoint=true&throwExceptionOnFailure=false&ignoreResponseBody=true&disableStreamCache=true").end();
            }
        };


/*
        RouteBuilder routes = new RouteBuilder() {
            public void configure() {
                from(String.format("netty4-http:http://0.0.0.0:%d?httpMethodRestrict=GET,HEAD,POST,PUT,DELETE,TRACE,OPTIONS,CONNECT,PATCH&chunkedMaxContentLength=100000000&disableStreamCache=true&option.AUTO_READ=false", 8081))
                        .id("nettyInputRoute")
                        .process(new Processor() {
                            public void process(Exchange exchange) throws Exception {
                                int i = 1;
                            }
                        })
                        .toD("netty4-http:http://localhost:8080/storage-service/v1/1/files/1?bridgeEndpoint=true&throwExceptionOnFailure=false&useRelativePath=true").end();
            }
        };
*/

        context.addRoutes(routes);
        context.start();
    }
}