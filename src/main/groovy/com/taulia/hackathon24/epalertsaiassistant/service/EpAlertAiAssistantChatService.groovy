package com.taulia.hackathon24.epalertsaiassistant.service

import org.springframework.ai.chat.messages.UserMessage
import org.springframework.ai.chat.prompt.Prompt
import org.springframework.ai.chat.prompt.SystemPromptTemplate
import org.springframework.ai.openai.OpenAiChatClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service

@Service
class EpAlertAiAssistantChatService {

  @Value('classpath:/prompts/system-prompt.st')
  Resource systemPromptResource

  @Autowired
  OpenAiChatClient chatClient

  String testSystemPromptForMessage(String message) {
    UserMessage userMessage = new UserMessage(message)
    SystemPromptTemplate promptTemplate = new SystemPromptTemplate(systemPromptResource)
    Prompt prompt = new Prompt([promptTemplate.createMessage(), userMessage])
    chatClient.call(prompt).result.output.content
  }

}
