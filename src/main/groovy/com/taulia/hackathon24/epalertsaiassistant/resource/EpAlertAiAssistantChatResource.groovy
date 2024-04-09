package com.taulia.hackathon24.epalertsaiassistant.resource

import com.taulia.hackathon24.epalertsaiassistant.model.ChatThread
import com.taulia.hackathon24.epalertsaiassistant.model.Message
import com.taulia.hackathon24.epalertsaiassistant.service.ChatThreadService
import com.taulia.hackathon24.epalertsaiassistant.service.EpAlertAiAssistantChatService
import groovy.util.logging.Slf4j
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
@Slf4j
class EpAlertAiAssistantChatResource {

  @Autowired
  ChatThreadService chatThreadService

  @Autowired
  EpAlertAiAssistantChatService epAlertAiAssistantChatService

  @PostMapping
  String createThread() {
    chatThreadService.createThread()
  }

  @GetMapping('/{threadId}')
  ChatThread getMessagesForThread(@PathVariable('threadId') String threadId) {
    chatThreadService.resumeThread(threadId)
  }

  @PostMapping('/{threadId}/message')
  Message sendMessageToThread(@PathVariable('threadId') String threadId, @RequestBody Message message) {
    epAlertAiAssistantChatService.sendMessage(threadId, message)
  }

  @GetMapping('/test/prompt')
  String testSystemPromptForMessage(@RequestParam('message') String message) {
    epAlertAiAssistantChatService.testSystemPromptForMessage(message)
  }

}
