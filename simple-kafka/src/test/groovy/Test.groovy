import groovy.util.logging.Slf4j
import spock.lang.Specification
import spock.lang.Title

@Slf4j
@Title("Do nothing but generate Test")
class Test extends Specification {


   def "do absolutely nothing"() {

      given: "nothing"

      expect:
      log.warn "This is test output."
      1==1
   }
}
