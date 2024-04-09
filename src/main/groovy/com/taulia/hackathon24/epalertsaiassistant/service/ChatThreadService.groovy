package com.taulia.hackathon24.epalertsaiassistant.service

import com.taulia.hackathon24.epalertsaiassistant.model.ChatThread
import com.taulia.hackathon24.epalertsaiassistant.model.ChatThreadCreated
import com.taulia.hackathon24.epalertsaiassistant.repository.ChatThreadRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class ChatThreadService {

  @Autowired
  ChatThreadRepository threadRepository

  ChatThreadCreated createThread() {
    ChatThread thread = new ChatThread()
    thread.userId = SecurityContextHolder.context.authentication.principal
    new ChatThreadCreated(threadId: threadRepository.save(thread).id)
  }

  ChatThread resumeThread(String threadId) {
    String userId = SecurityContextHolder.context.authentication.principal
    threadRepository.findByIdAndUserId(threadId, userId)
  }

  List<ChatThread> getThreads() {
    String userId = SecurityContextHolder.context.authentication.principal
    List<ChatThread> threads = threadRepository.findAllByUserId(userId)
    threads.each { it.messages = [] }
  }

}
