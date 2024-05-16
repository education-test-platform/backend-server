package com.mdemydovych.nadiya.backend;

import io.github.centrifugal.centrifuge.Client;
import io.github.centrifugal.centrifuge.ConnectedEvent;
import io.github.centrifugal.centrifuge.ConnectingEvent;
import io.github.centrifugal.centrifuge.DisconnectedEvent;
import io.github.centrifugal.centrifuge.ErrorEvent;
import io.github.centrifugal.centrifuge.EventListener;
import io.github.centrifugal.centrifuge.MessageEvent;
import io.github.centrifugal.centrifuge.Options;
import io.github.centrifugal.centrifuge.ServerJoinEvent;
import io.github.centrifugal.centrifuge.ServerLeaveEvent;
import io.github.centrifugal.centrifuge.ServerPublicationEvent;
import io.github.centrifugal.centrifuge.ServerSubscribedEvent;
import io.github.centrifugal.centrifuge.ServerSubscribingEvent;
import io.github.centrifugal.centrifuge.ServerUnsubscribedEvent;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

public class PublisherTest {

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
    client.publish("hehehoho", "{hghghghg}".getBytes(), (err, res) -> {
      if (err != null) {
        System.out.println("error publish: " + err);
        return;
      }
      System.out.println("successfully published");
    });
    Thread.sleep(100000);
  }
}
