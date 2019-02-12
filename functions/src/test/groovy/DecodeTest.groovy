import groovy.util.logging.Slf4j
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

@Slf4j
class DecodeTest extends Specification {

   @Shared
           decode = new Decode()

   @Unroll
   def "'#result' is returned when '#expression', '#search', '#text' and '#defaultValue' are passed."() {

      expect: "#result is returned with a successful match"
      decode.decode(expression, search, text, defaultValue) == result

      where:

      expression    | search        | text  | defaultValue || result
      'KSQL Rocks!' | 'ksql rocks!' | 'yes' | 'no'         || 'no'
      'KSQL Rocks!' | 'KSQL Rocks!' | 'yes' | 'no'         || 'yes'

   }

   @Unroll
   def "'#result' is returned when '#expression', '#search', '#text', '#defaultValue' and '#ignorecase' are passed."() {

      expect: "#result is returned with a successful match"
      decode.decode(expression, search, text, defaultValue, ignorecase) == result

      where:

      expression    | search        | text  | defaultValue | ignorecase || result
      'KSQL Rocks!' | 'KSQL rocks!' | 'yes' | 'no'         | true       || 'yes'
      'KSQL Rocks!' | 'KSQL Rocks!' | 'yes' | 'no'         | true       || 'yes'
      'KSQL Rocks!' | 'KSQL rocks!' | 'yes' | 'no'         | false      || 'no'

   }

   @Unroll
   def "Passed: #expression, #search1, #text1, #search2, #text2, #defaultValue and #ignorecase; Expect: #result"() {

      expect: "#result is returned with a successful match"
      decode.decode(expression, search1, text1, search2, text2, defaultValue, ignorecase) == result

      where:

      expression    | search1       | text1 | search2       | text2 | defaultValue | ignorecase || result
      'KSQL Rocks!' | 'KSQL rocks!' | 'yes' | 'KSQL Sucks!' | 'no'  | 'no'         | true       || 'yes'
      'KSQL Rocks!' | 'KSQL rocks!' | 'no'  | 'KSQL Rocks!' | 'yes' | 'no'         | false      || 'yes'
      'KSQL Rocks!' | 'KSQL rocks!' | 'no'  | 'KSQL Sucks!' | 'no'  | 'yes'        | false      || 'yes'

   }

   @Unroll
   def "Passed: #expression, #search1, #text1, #search2, #text2, #search3, #text3, #defaultValue and #ignorecase; Expect: #result"() {

      expect: "#result is returned with a successful match"
      decode.decode(expression, search1, text1, search2, text2, search3, text3, defaultValue, ignorecase) == result

      where:

      expression    | search1       | text1 | search2       | text2 | search3       | text3 | defaultValue | ignorecase || result
      'KSQL Rocks!' | 'KSQL rocks!' | 'yes' | 'KSQL Sucks!' | 'no'  | 'KSQL, meh'   | 'no'  | 'no'         | true       || 'yes'
      'KSQL Rocks!' | 'KSQL rocks!' | 'no'  | 'KSQL Rocks!' | 'yes' | 'KSQL, meh'   | 'no'  | 'no'         | false      || 'yes'
      'KSQL, meh'   | 'KSQL rocks!' | 'no'  | 'KSQL Sucks!' | 'no'  | 'KSQL, meh'   | 'yes' | 'no'         | false      || 'yes'
      'KSQL, meh'   | 'KSQL rocks!' | 'no'  | 'KSQL Sucks!' | 'no'  | "What's KSQL" | 'no'  | 'yes'        | false      || 'yes'
      'KSQL, meh'   | 'ksql rocks!' | 'no'  | 'ksql, meh'   | 'yes' | "What's KSQL" | 'no'  | 'no'         | true       || 'yes'

   }
}
