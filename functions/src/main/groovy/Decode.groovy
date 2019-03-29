import groovy.util.logging.Slf4j
import io.confluent.ksql.function.udf.Udf
import io.confluent.ksql.function.udf.UdfDescription

@Slf4j
@UdfDescription(
        name = "decode",
        description = """Given up to 3 pairs of 'search' and 'text', return the first 'text' value where 'search' matches 'expression'. If no match, return 'defaultvalue'. 'ignorecase' defaults to 'false'.""")
class Decode {

   @Udf(description = """Given 1 pair of 'search' and 'text', return the first 'text' value where 'search' matches 'expression'. If no match, return 'defaultvalue'. 'ignorecase' defaults to 'false'.""")
   String decode(String expression, String search1, String text1, String defaultvalue, Boolean ignorecase = false) {

      // If any of the expected values are null, then just return null
      if (expression == null || search1 == null || text1 == null) return null

      return Utils.textMatch(expression, search1, ignorecase) ? text1 : defaultvalue
   }
   @Udf(description = """Given 2 pairs of 'search' and 'text', return the first 'text' value where 'search' matches 'expression'. If no match, return 'defaultvalue'. 'ignorecase' defaults to 'false'.""")
   String decode(String expression, String search1, String text1, String search2, String text2, String defaultvalue, Boolean ignorecase = false) {

      // If any of the expected values are null, then just return null
      if (expression == null || search1 == null || text1 == null || search2 == null || text2 == null) return null

      if (Utils.textMatch(expression, search1, ignorecase)) return text1

      else if (Utils.textMatch(expression, search2, ignorecase)) return text2

      else return defaultvalue
   }
   @Udf(description = """Given 3 pairs of 'search' and 'text', return the first 'text' value where 'search' matches 'expression'. If no match, return 'defaultvalue'. 'ignorecase' defaults to 'false'.""")
   String decode(String expression, String search1, String text1, String search2, String text2, String search3, String text3, String defaultvalue, Boolean ignorecase = false) {

      // If any of the expected values are null, then just return null
      if (expression == null || search1 == null || text1 == null || search2 == null || text2 == null || search3 == null || text3 == null) return null

      if (Utils.textMatch(expression, search1, ignorecase)) return text1

      else if (Utils.textMatch(expression, search2, ignorecase)) return text2

      else if (Utils.textMatch(expression, search3, ignorecase)) return text3

      else return defaultvalue
   }
}
