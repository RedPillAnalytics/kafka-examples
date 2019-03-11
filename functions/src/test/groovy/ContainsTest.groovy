import groovy.util.logging.Slf4j
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

@Slf4j
class ContainsTest extends Specification {

   @Shared
           contains = new Contains()

   @Unroll
   def "Passed: #expression, #search, #ignorecase; Returns: #result"() {

      expect: "ignorecase parameter to return the proper result string"
      contains.contains(expression, search, 'yes', 'no', ignorecase) == result

      where:
      expression    | search | ignorecase || result
      'KSQL rocks!' | 'ksql' | true       || 'yes'
      'KSQL rocks!' | 'ksql' | false      || 'no'
      'SQL rocks!'  | 'ksql' | true       || 'no'
      'KSQL rocks!' | 'sql'  | true       || 'yes'
      'SQL rocks!'  | '!'    | true       || 'yes'
      'SQL rocks!'  | null   | true       || 'no'

   }
}
