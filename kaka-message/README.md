# SpringBoot application

Build:
`mvn clean  package`

Run
`mvn spring-boot:run`

Run the application and use curl to send some data to input topic `rubik-hack.input`:

`$curl -X POST -d '{"id": "id", "name": "name", "title": "title", "price": 695783, "date": "2015-01-01" }'  -H 'Content-Type: application/json' http://localhost:8080/send/opportunity/`

The consumer will read the message from `rubik-hack.input`,
then ETL engine will transform above message by the [script](src/main/resources/transform_script.js)
After transforming, message will be pushed to topic `rubik-hack.output` 
TODO: implement scheduler to load data from topic `rubik-hack.output` to ADS table via JDBC with the command:
`COPY [TABLE_NAME] SOURCE KafkaSource ...`
