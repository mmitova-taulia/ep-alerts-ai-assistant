package com.taulia.hackathon24.epalertsaiassistant.listener

import com.taulia.hackathon24.epalertsaiassistant.model.ChatThread
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent
import org.springframework.stereotype.Component

import java.time.Instant

@Component
class ChatThreadAuditListener extends AbstractMongoEventListener<ChatThread> {

  @Override
  void onBeforeConvert(BeforeConvertEvent<ChatThread> event) {
    super.onBeforeConvert(event)
    if (!event.source.dateCreated) {
      event.source.dateCreated = Instant.now()
    }

    event.source.lastUpdated = Instant.now()
  }

}
