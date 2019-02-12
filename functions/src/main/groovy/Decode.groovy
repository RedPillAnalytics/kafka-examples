import groovy.util.logging.Slf4j
import io.confluent.ksql.function.udf.Udf
import io.confluent.ksql.function.udf.UdfDescription

@Slf4j
@UdfDescription(name = "decode",
        description = """Accepts: expression, search1, text1, search2, text2, defaultvalue, ignorecase
                If 'expression' matches 'search1', then return 'text1' first.
                If 'expression' matches 'search2', then return 'text2' second.
                Otherwise, return 'defaultvalue'. 
                'ignorecase' defaults to FALSE.
                'search2' and 'text2' are optional.""")
class Decode {

   @Udf(description = """Accepts: expression, search, text, defaultvalue, ignorecase.
                      If 'expression' matches 'search', then return 'text'. Otherwise, return 'defaultvalue'. 
                      'ignorecase' defaults to FALSE.""")
   String decode(String expression, String search1, String text1, String defaultvalue, Boolean ignorecase = false) {

      // If any of the expected values are null, then just return null
      // This simplifies functional wrapping around the function call
      if (expression == null || search1 == null || text1 == null) {

         return null
      }

      return Utils.textMatch(expression, search1, ignorecase) ? text1 : defaultvalue

   }

   @Udf(description = """Accepts: expression, search1, text1, search2, text2, defaultvalue, ignorecase.
                      If 'expression' matches 'search1', then return 'text1' first.
                      If 'expression' matches 'search2', then return 'text2' second.
                      Otherwise, return 'defaultvalue'. 
                      'ignorecase' defaults to FALSE.""")
   String decode(String expression, String search1, String text1, String search2, String text2, String defaultvalue, Boolean ignorecase = false) {

      // If any of the expected values are null, then just return null
      // This simplifies functional wrapping around the function call
      if (expression == null || search1 == null || text1 == null || search2 == null || text2 == null) {

         return null
      }

      if (Utils.textMatch(expression, search1, ignorecase)) {

         return text1
      } else if (Utils.textMatch(expression, search2, ignorecase)) {

         return text2
      } else return defaultvalue

   }

   @Udf(description = """Accepts: expression, search1, text1, search2, text2, search3, text3 defaultvalue, ignorecase.
                      If 'expression' matches 'search1', then return 'text1' first.
                      If 'expression' matches 'search2', then return 'text2' second.
                      If 'expression' matches 'search3', then return 'text3' third.
                      Otherwise, return 'defaultvalue'. 
                      'ignorecase' defaults to FALSE.""")
   String decode(String expression, String search1, String text1, String search2, String text2, String search3, String text3, String defaultvalue, Boolean ignorecase = false) {

      // If any of the expected values are null, then just return null
      // This simplifies functional wrapping around the function call
      if (expression == null || search1 == null || text1 == null || search2 == null || text2 == null || search3 == null || text3 == null) {

         return null
      }

      if (Utils.textMatch(expression, search1, ignorecase)) return text1

      else if (Utils.textMatch(expression, search2, ignorecase)) return text2

      else if (Utils.textMatch(expression, search3, ignorecase)) return text3

      else return defaultvalue

   }
}
