package com.taulia.hackathon24.epalertsaiassistant.resource

import com.taulia.hackathon24.epalertsaiassistant.model.ChatThread
import com.taulia.hackathon24.epalertsaiassistant.model.Message
import com.taulia.hackathon24.epalertsaiassistant.repository.ChatThreadRepository
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
  ChatThreadRepository threadRepository

  @Autowired
  EpAlertAiAssistantChatService epAlertAiAssistantChatService

  @PostMapping
  String createThread(@RequestBody ChatThread thread) {
    threadRepository.save(thread).id
  }

  @GetMapping('/{threadId}')
  ChatThread getMessagesForThread(@PathVariable('threadId') String threadId) {
    threadRepository.findById(threadId).orElseThrow { new RuntimeException() }
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
