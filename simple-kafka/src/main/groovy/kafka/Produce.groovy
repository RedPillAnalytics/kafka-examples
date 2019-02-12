package kafka

import com.google.gson.GsonBuilder
import groovy.util.logging.Slf4j
import org.apache.kafka.clients.producer.*
import twitter4j.Query
import twitter4j.QueryResult
import twitter4j.TwitterFactory

@Slf4j
class Produce {

   def sendTwitter(String topic, String searchTerm) {

      def producer = new KafkaProducer([
              "bootstrap.servers": 'streaming:9092',
              // serializers
              "value.serializer" : "org.apache.kafka.common.serialization.StringSerializer",
              "key.serializer"   : "org.apache.kafka.common.serialization.StringSerializer",
              // acknowledgement control
              "acks"             : "all"
      ])

      // Google JSON deserializer
      def gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create()

      // Twitter4j, the definitive Java API for Twitter
      def twitter = TwitterFactory.getSingleton();

      // Using Twitters "Search Query" language
      def query = new Query(searchTerm).count(100);
      QueryResult result = twitter.search(query);

      result.getTweets().each { tweet ->

         // deserialize and send
         def json = gson.toJson(tweet)
         println "[New Record]: ${json}\n"
         def message = producer.send(new ProducerRecord(topic, json))
      }
      producer.close()
   }
}
