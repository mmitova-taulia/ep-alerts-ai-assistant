package com.taulia.hackathon24.epalertsaiassistant.model

import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.FieldType
import org.springframework.data.mongodb.core.mapping.MongoId

import java.time.Instant

@Document(collection = 'threads')
class ChatThread {

  @MongoId(FieldType.OBJECT_ID)
  String id

  @Indexed
  String userId

  Set<Message> messages = []

  Instant dateCreated

  Instant lastUpdated

}
