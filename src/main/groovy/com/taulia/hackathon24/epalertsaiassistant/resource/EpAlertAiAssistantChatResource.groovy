package com.taulia.hackathon24.epalertsaiassistant.resource

import com.taulia.hackathon24.epalertsaiassistant.model.Message
import com.taulia.hackathon24.epalertsaiassistant.service.EpAlertAiAssistantChatService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping('/thread')
class EpAlertAiAssistantChatResource {

  @Autowired
  EpAlertAiAssistantChatService epAlertAiAssistantChatService

  @GetMapping('/{threadId}')
  List<Message> getMessagesForThread(@PathVariable('threadId') String threadId) {
    List<Message> messages = []
    messages
  }

  @PostMapping('/{threadId}/message')
  Message sendMessagesToThread(@PathVariable('threadId') String threadId, @RequestBody Message message) {
    Message response = new Message(messageContent: "OK")
    response
  }

  @GetMapping('/test/prompt')
  String testSystemPromptForMessage(@RequestParam('message') String message) {
    epAlertAiAssistantChatService.testSystemPromptForMessage(message)
  }

}
