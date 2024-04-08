# EP Alerts AI Assistant
This project aims to provide an AI assistant who helps users to configure Early Payment Alerts.

## Prerequisites
* Docker
* OpenAI API key

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
This will send a `GET` request to `thread/test/prompt` with `message` equal to `What is the datatype of the workflow_profile.workflow.workflow column?`.