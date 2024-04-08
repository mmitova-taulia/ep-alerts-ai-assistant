package com.taulia.hackathon24.epalertsaiassistant

import com.taulia.hackathon24.epalertsaiassistant.model.Message
import jakarta.websocket.server.PathParam
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController("/thread")
class EpAlertAiAssistantChatResource {

  @GetMapping("/{threadId}")
  List<Message> getMessagesForThread(@PathParam String threadId)  {
    List<Message> messages = []
    messages
  }

  @PostMapping("/{threadId}/message")
  Message sendMessagesToThread(@PathParam String threadId, @RequestBody Message message)  {
    Message response = new Message(messageContent: "OK")
    response
  }
}
