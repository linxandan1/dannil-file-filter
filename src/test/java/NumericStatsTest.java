import org.junit.jupiter.api.Test;
import org.yeehy.statistic.NumericStats;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumericStatsTest {
    @Test
    void testEmptyStats() {
        NumericStats stats = new NumericStats();
        assertEquals(0, stats.getCount());
        assertTrue(Double.isNaN(stats.getMin()));
        assertTrue(Double.isNaN(stats.getMax()));
        assertTrue(Double.isNaN(stats.getAverage()));
    }

    @Test
    void testBasicStats() {
        NumericStats stats = new NumericStats();
        stats.addNumber(10);
        stats.addNumber(20);
        stats.addNumber(30);

        assertEquals(3, stats.getCount());
        assertEquals(60.0, stats.getSum());
        assertEquals(10.0, stats.getMin());
        assertEquals(30.0, stats.getMax());
        assertEquals(20.0, stats.getAverage());
    }
}
