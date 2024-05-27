package com.mdemydovych.nadiya.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mdemydovych.nadiya.backend.broker.BrokerPublisherService;
import com.mdemydovych.nadiya.model.broker.BrokerMessage;
import io.github.centrifugal.centrifuge.Client;
import io.github.centrifugal.centrifuge.CompletionCallback;
import io.github.centrifugal.centrifuge.ConnectedEvent;
import io.github.centrifugal.centrifuge.ConnectingEvent;
import io.github.centrifugal.centrifuge.DisconnectedEvent;
import io.github.centrifugal.centrifuge.ErrorEvent;
import io.github.centrifugal.centrifuge.EventListener;
import io.github.centrifugal.centrifuge.MessageEvent;
import io.github.centrifugal.centrifuge.Options;
import io.github.centrifugal.centrifuge.PublishResult;
import io.github.centrifugal.centrifuge.RPCResult;
import io.github.centrifugal.centrifuge.ResultCallback;
import io.github.centrifugal.centrifuge.ServerJoinEvent;
import io.github.centrifugal.centrifuge.ServerLeaveEvent;
import io.github.centrifugal.centrifuge.ServerPublicationEvent;
import io.github.centrifugal.centrifuge.ServerSubscribedEvent;
import io.github.centrifugal.centrifuge.ServerSubscribingEvent;
import io.github.centrifugal.centrifuge.ServerUnsubscribedEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
public class PublisherTest {

//  @Autowired
//  private BrokerPublisherService service;

  @Test
  @SneakyThrows
  void shouldPub() {
    Options opts = new Options();

    Client client = new Client(
        "ws://localhost:8000/connection/websocket",
        opts,
        new EventListener() {
          @Override
          public void onConnecting(Client client, ConnectingEvent event) {
            super.onConnecting(client, event);
          }

          @Override
          public void onConnected(Client client, ConnectedEvent event) {
            super.onConnected(client, event);
          }

          @Override
          public void onDisconnected(Client client, DisconnectedEvent event) {
            super.onDisconnected(client, event);
          }

          @Override
          public void onError(Client client, ErrorEvent event) {
            super.onError(client, event);
          }

          @Override
          public void onMessage(Client client, MessageEvent event) {
            super.onMessage(client, event);
          }

          @Override
          public void onSubscribed(Client client, ServerSubscribedEvent event) {
            super.onSubscribed(client, event);
          }

          @Override
          public void onSubscribing(Client client, ServerSubscribingEvent event) {
            super.onSubscribing(client, event);
          }

          @Override
          public void onUnsubscribed(Client client, ServerUnsubscribedEvent event) {
            super.onUnsubscribed(client, event);
          }

          @Override
          public void onPublication(Client client, ServerPublicationEvent event) {
            super.onPublication(client, event);
          }

          @Override
          public void onJoin(Client client, ServerJoinEvent event) {
            super.onJoin(client, event);
          }

          @Override
          public void onLeave(Client client, ServerLeaveEvent event) {
            super.onLeave(client, event);
          }
        }
    );
    client.connect();
    String requestBodyString = new ObjectMapper().writeValueAsString(
        buildMessage("mes", List.of("396d5ccc-a331-4ada-b04b-076ef17297c5")));
    MyClassHe myClassHe = new ObjectMapper().readValue(requestBodyString, MyClassHe.class);
    client.send(requestBodyString.getBytes(),
        new CompletionCallback() {
          @Override
          public void onDone(Throwable throwable) {
System.out.println();
          }
        });
//    client.publish("396d5ccc-a331-4ada-b04b-076ef17297c5", "{hghghghg}".getBytes(), (err, res) -> {
//      if (err != null) {
//        System.out.println("error publish: " + err);
//        return;
//      }
//      System.out.println("successfully published");
//    });
//    service.sendMessage("{mes}", List.of("396d5ccc-a331-4ada-b04b-076ef17297c5"));
    Thread.sleep(10000000);
//    service.sendMessage("{message}", List.of("f988a1f3-19cb-437d-b7c7-3a9b8bef8b6d"));
  }

  private Map<String, Object> buildMessage(String message, List<String> topics) {
    Map<String, Object> body = new HashMap<>();
    body.put("method", "broadcast");
    body.put("params", Map.of("channels", topics, "data", new BrokerMessage(message)));
    return body;
  }

  @Data
  public static class MyClassHe {

    private String method;

    private ParamsClass params;
  }

  @Data
  public static class ParamsClass {

    private List<String> channels;

    private Object data;
  }
}
