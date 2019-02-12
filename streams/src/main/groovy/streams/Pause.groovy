import groovy.util.logging.Slf4j
import io.confluent.ksql.function.udf.Udf
import io.confluent.ksql.function.udf.UdfDescription

@Slf4j
@UdfDescription(name = "pause",
        description = """Parameter: Integer - Number of seconds to pause before processing the return value.
                         Parameter: Boolean - If 'true', return the date/time of the pause end (the default). If 'false', return the date/time of the pause start.
                         Return:    BigInteger  - Epoch Date/Time of either the start or end date.""")
class Pause {

   @Udf(description = """Parameter: Integer - Number of seconds to pause before processing the return value.
                         Parameter: Boolean - If 'true', return the date/time of the pause end (the default). If 'false', return the date/time of the pause start.
                         Return:    String  - Epoch Date/Time of either the start or end date.""")
   Integer pause(Integer duration, Boolean endDate = true) {

      BigInteger start = System.currentTimeSeconds().toBigInteger()
      sleep(duration * 1000)
      BigInteger end = System.currentTimeSeconds().toBigInteger()

      log.debug "Duraton: ${end - start}"
      return (endDate ? end : start)
   }
}
