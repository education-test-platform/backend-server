package com.mdemydovych.nadiya.backend.listener;

import io.github.centrifugal.centrifuge.Client;
import io.github.centrifugal.centrifuge.ErrorEvent;
import io.github.centrifugal.centrifuge.EventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerBrokerListener extends EventListener {

  private static final Logger logger = LoggerFactory.getLogger(ServerBrokerListener.class);

  @Override
  public void onError(Client client, ErrorEvent event) {
    logger.error("Server broker error {}", event.getError().getMessage());
  }


}
