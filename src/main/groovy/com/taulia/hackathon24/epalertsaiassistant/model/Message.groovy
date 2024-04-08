package com.taulia.hackathon24.epalertsaiassistant.model

import java.time.LocalDateTime

class Message {
  String threadId
  MessageType messageType
  String messageContent
  LocalDateTime datetime //sent time
}
