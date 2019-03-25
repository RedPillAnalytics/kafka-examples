import groovy.util.logging.Slf4j

@Slf4j
class Utils {

   static Boolean textMatch(String expression, String search, Boolean ignorecase) {

      if (ignorecase) {
         return expression.equalsIgnoreCase(search)
      } else return (expression == search)

   }

   static Boolean textContains(String expression, String search, Boolean ignorecase) {
      def expressionx = ignorecase ? expression?.toLowerCase() : expression
      def searchx = ignorecase ? search?.toLowerCase() : search

      return expressionx?.contains(searchx)
   }
}
