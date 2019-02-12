import groovy.util.logging.Slf4j
import spock.lang.Shared
import spock.lang.Specification

@Slf4j
class PauseTest extends Specification {

   @Shared
   def pause = new Pause()

   def "A pause() of 5 seconds works"() {

      when:
      BigInteger start = System.currentTimeSeconds().toBigInteger()
      BigInteger end = pause.pause(5, true)
      BigInteger diff = (end - start)

      then:
      log.warn "Return: $end"
      diff >= 5
   }
}
