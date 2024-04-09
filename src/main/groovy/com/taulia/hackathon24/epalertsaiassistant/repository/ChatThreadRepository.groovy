package com.taulia.hackathon24.epalertsaiassistant.repository

import com.taulia.hackathon24.epalertsaiassistant.model.ChatThread
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ChatThreadRepository extends MongoRepository<ChatThread, String> {

  ChatThread findByIdAndUserId(String id, String userId)

}