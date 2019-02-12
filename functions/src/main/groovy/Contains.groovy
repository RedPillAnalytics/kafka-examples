import groovy.util.logging.Slf4j
import io.confluent.ksql.function.udf.Udf
import io.confluent.ksql.function.udf.UdfDescription

@Slf4j
@UdfDescription(name = "contains",
                description = """Accepts: expression, search, iftrue, iffalse, ignorecase
                If 'expression' matches 'search', then return 'iftrue' Otherwise, return 'iffalse'.
                'ignorecase' defaults to FALSE.""")
class Contains {

   @Udf(description = """Accepts: expression, search, iftrue, iffalse, ignorecase
                      If 'expression' matches 'search', then return 'iftrue' Otherwise, return 'iffalse'.
                      'ignorecase' defaults to FALSE.""")
   String contains(String expression, String search, String iftrue, String iffalse, Boolean ignorecase = false) {

      if (expression == null || search == null || iftrue == null || iffalse == null) {

         return iffalse
      }

      return Utils.textContains(expression, search, ignorecase) ? iftrue : iffalse

   }
}
