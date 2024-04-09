package com.taulia.hackathon24.epalertsaiassistant.configuration

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoRepositories('com.taulia.hackathon24.epalertsaiassistant.repository')
class MongoConfiguration extends AbstractMongoClientConfiguration {

  static final String DATABASE_NAME = 'epAlertsAiAssistantMessageHistory'

  @Value('${MONGO_INITDB_ROOT_USERNAME}')
  private String MONGO_INITDB_ROOT_USERNAME

  @Value('${MONGO_INITDB_ROOT_PASSWORD}')
  private String MONGO_INITDB_ROOT_PASSWORD

  @Override
  protected String getDatabaseName() {
    DATABASE_NAME
  }

  @Override
  MongoClient mongoClient() {
    ConnectionString connectionString = new ConnectionString("mongodb://${MONGO_INITDB_ROOT_USERNAME}:${MONGO_INITDB_ROOT_PASSWORD}@localhost:27017/admin")
    MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
      .applyConnectionString(connectionString)
      .build()

    MongoClients.create(mongoClientSettings)
  }

}
