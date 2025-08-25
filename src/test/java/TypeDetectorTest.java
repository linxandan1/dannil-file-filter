import org.junit.jupiter.api.Test;
import org.yeehy.TypeDetector;
import org.yeehy.TypeDetector.DataType;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TypeDetectorTest {

    @Test
    void testIntegerDetection() {
        assertEquals(DataType.INTEGER, TypeDetector.determineType("42"));
        assertEquals(DataType.INTEGER, TypeDetector.determineType("-123"));
    }

    @Test
    void testFloatDetection() {
        assertEquals(DataType.FLOAT, TypeDetector.determineType("3.14"));
        assertEquals(DataType.FLOAT, TypeDetector.determineType("-0.001"));
        assertEquals(DataType.FLOAT, TypeDetector.determineType("1.5e10"));
    }

    @Test
    void testStringDetection() {
        assertEquals(DataType.STRING, TypeDetector.determineType("hello"));
        assertEquals(DataType.STRING, TypeDetector.determineType("2.2.2"));
    }
}
