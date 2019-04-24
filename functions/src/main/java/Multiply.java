import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;
import io.confluent.ksql.function.udf.UdfParameter;

@UdfDescription(name = "multiply", description = "multiplies 2 numbers")
public class Multiply {

    @Udf(description = "multiply two non-nullable INTs.")
    public long multiply(
            @UdfParameter(value = "V1", description = "the first value") final int v1,
            @UdfParameter(value = "V2", description = "the second value") final int v2) {
        return v1 * v2;
    }

    @Udf(description = "multiply two non-nullable BIGINTs.")
    public long multiply(
            @UdfParameter("V1") final long v1,
            @UdfParameter("V2") final long v2) {
        return v1 * v2;
    }

    @Udf(description = "multiply two nullable BIGINTs. If either param is null, null is returned.")
    public Long multiply(final Long v1, final Long v2) {
        return v1 == null || v2 == null ? null : v1 * v2;
    }

    @Udf(description = "multiply two non-nullable DOUBLEs.")
    public double multiply(final double v1, double v2) {
        return v1 * v2;
    }
}
