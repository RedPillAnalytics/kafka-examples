package kafka

import com.google.gson.GsonBuilder
import groovy.util.logging.Slf4j
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.clients.consumer.KafkaConsumer

@Slf4j
class Consume {

   def subscribe(String topic, String group) {

      def consumer = new KafkaConsumer([
              "bootstrap.servers"      : 'streaming:9092',
              // Consumer group
              "group.id"               : group,
              "auto.offset.reset"      : "earliest",
              
              // auto offset management
              "enable.auto.commit"     : "true",
              "auto.commit.interval.ms": "1000",

              // serializers
              "value.deserializer"     : "org.apache.kafka.common.serialization.StringDeserializer",
              "key.deserializer"       : "org.apache.kafka.common.serialization.StringDeserializer"
      ])

      // Google JSON deserializer
      def gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create()

      // subscribe to the topic
      consumer.subscribe([topic])

      // loop using the poll mechanism
      while (true) {
         ConsumerRecords records = consumer.poll(100)
         records.each { tweet ->

            // deserialize JSON into a Map
            def json = gson.fromJson(tweet.value(), Map)

            println "[${json.user.screenName} tweeted]: ${json.text}\n"
         }
      }
      consumer.close()
   }
}
