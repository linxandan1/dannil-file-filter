import org.junit.jupiter.api.Test;
import org.yeehy.statistic.StringStats;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringStatsTest {

    @Test
    void testEmptyStats() {
        StringStats stats = new StringStats();
        assertEquals(0, stats.getCount());
        assertEquals(0, stats.getMinLength());
        assertEquals(0, stats.getMaxLength());
    }

    @Test
    void testStringLengths() {
        StringStats stats = new StringStats();
        stats.addString("hi");
        stats.addString("world");
        stats.addString("java");

        assertEquals(3, stats.getCount());
        assertEquals(2, stats.getMinLength()); // "hi"
        assertEquals(5, stats.getMaxLength()); // "world"
    }
}
