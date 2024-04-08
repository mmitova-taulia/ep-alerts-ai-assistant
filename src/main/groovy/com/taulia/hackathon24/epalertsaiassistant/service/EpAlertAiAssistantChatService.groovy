package com.taulia.hackathon24.epalertsaiassistant.service

import com.taulia.hackathon24.epalertsaiassistant.model.ChatThread
import com.taulia.hackathon24.epalertsaiassistant.model.Message
import com.taulia.hackathon24.epalertsaiassistant.repository.ChatThreadRepository
import org.springframework.ai.chat.ChatResponse
import org.springframework.ai.chat.messages.AssistantMessage
import org.springframework.ai.chat.messages.ChatMessage
import org.springframework.ai.chat.messages.UserMessage
import org.springframework.ai.chat.prompt.Prompt
import org.springframework.ai.chat.prompt.SystemPromptTemplate
import org.springframework.ai.openai.OpenAiChatClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service

import java.time.Instant

import static com.taulia.hackathon24.epalertsaiassistant.model.MessageType.ASSISTANT

@Service
class EpAlertAiAssistantChatService {

  @Value('classpath:/prompts/system-prompt.st')
  Resource systemPromptResource

  @Autowired
  ChatThreadRepository threadRepository

  @Autowired
  OpenAiChatClient chatClient

  String testSystemPromptForMessage(String message) {
    UserMessage userMessage = new UserMessage(message)
    SystemPromptTemplate promptTemplate = new SystemPromptTemplate(systemPromptResource)
    Prompt prompt = new Prompt([promptTemplate.createMessage(), userMessage])
    chatClient.call(prompt).result.output.content
  }

  Message sendMessage(String threadId, Message message) {
    message.sentAt = Instant.now()

    ChatThread thread = threadRepository.findById(threadId).orElseThrow { new RuntimeException() }
    ChatResponse response = chatClient.call(createPrompt(thread, message))

    persistMessage(thread, message)
    persistMessage(thread, response.result.output)
  }

  private Prompt createPrompt(ChatThread thread, Message userMessage) {
    List<org.springframework.ai.chat.messages.Message> messages = []

    SystemPromptTemplate promptTemplate = new SystemPromptTemplate(systemPromptResource)
    messages << promptTemplate.createMessage()
    messages.addAll(thread.messages.collect { new ChatMessage(it.type.name(), it.content) })
    messages.add(new UserMessage(userMessage.content))
    new Prompt(messages)
  }

  private Message persistMessage(ChatThread thread, Message message) {
    message.sentAt = Instant.now()

    thread.messages << message
    threadRepository.save(thread)

    message
  }

  private Message persistMessage(ChatThread thread, AssistantMessage assistantMessage) {
    Message message = new Message()
    message.type = ASSISTANT
    message.content = assistantMessage.content
    message.sentAt = Instant.now()

    thread.messages << message
    threadRepository.save(thread)

    message
  }

}
