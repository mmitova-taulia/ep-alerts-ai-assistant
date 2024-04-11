# EP Alerts AI Assistant
This project aims to provide an AI assistant who helps users to configure Early Payment Alerts.

## Prerequisites
* Docker
* OpenAI API key

## Set environment variables
Create a `.env` file inside the root directory of the project and populate it with the following variables:
```env
MONGO_INITDB_ROOT_USERNAME=root
MONGO_INITDB_ROOT_PASSWORD=<desired password for the root user; used to connect to the database>
MONGO_INITDB_DATABASE=epAlertsAiAssistantMessageHistory
ME_CONFIG_BASICAUTH_USERNAME=root
ME_CONFIG_BASICAUTH_PASSWORD=<desired password for the root user; used to connect to mongoexpress>
MONGO_DATA_VOLUME=./mongo/mongo-data
JIRA_URL=<jira instance where tickets will be created>
JIRA_USERNAME=<jira user used during the creation of tickets>
JIRA_PASSWORD=<jira user password>
JIRA_PROJECT=<jira project where tickets will be created>
USE_JIRA_MOCK=<true|false weather you want to really create a jira ticket, or just print its contet to the console>
PPM_URL=<intapi-payment-process-manager url>
```

## Run the project
Open a terminal window in the project directory, then set the OpenAI API key as an environment variable:
```bash
export openai_api_key=<your_openai_api_key>
```

If you have [SDKMAN](https://sdkman.io/) installed, run:
```bash
sdk env
```
and then:
```bash
gradle bootRun -i
```

Otherwise, you will need to make sure you have an installation of `Java 17`, and then run:
```bash
./gradlew bootRun -i
```
The application will take few seconds to load, then you will be able to call the endpoints. Example:
```bash
curl 'http://localhost:8080/thread/test/prompt?message=what%20is%20the%20datatype%20of%20the%20workflow_profile.workflow%20column?'
```
This will send a `GET` request to `thread/test/prompt` with `message` equal to `What is the datatype of the workflow_profile.workflow column?`.