package ch.hslu.ad.sw01ex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Euklid {
    private static final Logger LOG = LoggerFactory.getLogger(Euklid.class);

    public static int ggtIterativ1(int a, int b) {
        while (a != b) {
            if (a > b) {
                LOG.info(String.format("vergleich: a [%s] ist grösser als b [%s]", a, b));
                LOG.info(String.format("rechne: a = a - b [%s - %s => %s]", a, b, a - b));
                a = a - b;
            } else {
                LOG.info(String.format("vergleich: a [%s] ist nicht grösser als b [%s]", a, b));
                LOG.info(String.format("rechne: b = b - a [%s - %s => %s]", b, a, b - a));
                b = b - a;
            }
        }
        LOG.info(String.format("a == b => return a [%s]", a));
        return a;
    }

    public static int ggtIterativ2(int a, int b) {
        while ((a != 0) && (b != 0)) {
            if (a > b) {
                LOG.info(String.format("vergleich: a [%s] > b [%s]", a, b));
                LOG.info(String.format("rechne: a = a %% b [%s %% %s => %s]", a, b, a % b));
                a = a % b;
            } else {
                LOG.info(String.format("vergleich: b [%s] > a [%s]", b, a));
                LOG.info(String.format("rechne: b = b %% a [%s %% %s => %s]", b, a, b % a));
                b = b % a;
            }
        }
        LOG.info(String.format("(a == 0) || (b == 0) => return a + b [%s]", a + b));
        return (a + b); // Entweder a oder b ist 0!
    }

    public static int ggtRekursiv(final int a, final int b) {
        LOG.info(String.format("ggtRekursiv(%s, %s)", a, b));
        if (a > b) {
            LOG.info(String.format("vergleich: a [%s] > b [%s]", a, b));
            LOG.info("ggtRekursiv(a - b, b)");
            return ggtRekursiv(a - b, b);
        } else {
            if (a < b) {
                LOG.info(String.format("vergleich: a [%s] < b [%s]", a, b));
                LOG.info("ggtRekursiv(a, b - a)");
                return ggtRekursiv(a, b - a);
            } else {
                LOG.info(String.format("!(a > b) && !(a < b) => return a [%s]", a));
                return a;
            }
        }
    }

}