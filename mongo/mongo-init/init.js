conn = new Mongo();
db = conn.getDB("epAlertsAiAssistantMessageHistory");


db.threads.createIndex({ "userId": 1 }, { unique: false });

db.threads.insert({ "userId": "user1", "message": { "type": "type1", "content": "Please create alert 1", "sentAt":"2024-04-09" }, "dateCreated": "2024-04-09", "lastUpdated": "2024-04-09" });
db.threads.insert({ "userId": "user1", "message": { "type": "type2", "content": "Please create alert 2", "sentAt":"2024-04-09" }, "dateCreated": "2024-04-09", "lastUpdated": "2024-04-09" });