package com.taulia.hackathon24.epalertsaiassistant.service

import org.springframework.ai.chat.messages.UserMessage
import org.springframework.ai.chat.prompt.Prompt
import org.springframework.ai.chat.prompt.SystemPromptTemplate
import org.springframework.ai.model.function.FunctionCallback
import org.springframework.ai.model.function.FunctionCallbackWrapper
import org.springframework.ai.openai.OpenAiChatClient
import org.springframework.ai.openai.OpenAiChatOptions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service

@Service
class EpAlertAiAssistantChatService {

  @Value('classpath:/prompts/system-prompt.st')
  Resource systemPromptResource

  @Autowired
  OpenAiChatClient chatClient

  String testSystemPromptForMessage(String message) {
    OpenAiChatOptions promptOptions = OpenAiChatOptions.builder()
      .withFunction("resolve_funder_id")
      .build()

    UserMessage userMessage = new UserMessage(message)
    SystemPromptTemplate promptTemplate = new SystemPromptTemplate(systemPromptResource)
    Prompt prompt = new Prompt([promptTemplate.createMessage(), userMessage], promptOptions)
    chatClient.call(prompt).result.output.content
  }

  @Configuration
  static class Config {

    @Bean
    FunctionCallback resolveFunderIdFunctionInfo() {

      return FunctionCallbackWrapper.builder(new ResolveFunderIdFunction())
        .withName("resolve_funder_id")
        .withDescription("Resolves funderId by funderName")
        .withResponseConverter((Response response) -> response.funderId())
        .build();
    }

  }

}
