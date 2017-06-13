package com.github.sonenko.hellojavacamelwebsockets;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class Main2 extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("HTML");
        stage.setWidth(500);
        stage.setHeight(500);
        Scene scene = new Scene(new Group());
        VBox root = new VBox();
        final WebView browser = new WebView();
        final WebEngine webEngine = browser.getEngine();
        Hyperlink hpl = new Hyperlink("www.rbc.ru");
        hpl.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                webEngine.load("http://www.rbc.ru");
            }
        });

        root.getChildren().addAll(hpl, browser);
        scene.setRoot(root);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

/*    public static void main(String args[]) throws Exception {


*//*    CamelContext context = new DefaultCamelContext();
    WebsocketComponent wc = context.getComponent("websocket", WebsocketComponent.class);
   // wc.setPort(8082);
    // serve static resources
   // wc.setStaticResources("classpath:.");

    RouteBuilder routes = new RouteBuilder() {
      public void configure() {
       //   from("file:FROM").to("websocket:my-socket?sendToAll=true");
        from("websocket://localhost:8082/my-socket").log("Receive: ${body}").to("file:TO").transform().simple("Response from Main2: ${body}")
                // send back to the client, by sending the message to the same endpoint
                // this is needed as by default messages is InOnly
                // and we will by default send back to the current client using the provided connection key
                .log("Response: ${body}").to("websocket://localhost:8082/my-socket");;
      }
    };

    context.addRoutes(routes);
    context.start();*//*
    }*/
}