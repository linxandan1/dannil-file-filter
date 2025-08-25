package org.yeehy;

public class TypeDetector {

    public enum DataType { INTEGER, FLOAT, STRING }

    /**
     * Определяет тип данных строки.
     * - integer: целое число (long, включая отрицательные)
     * - float: вещественное (double, включая экспоненциальную форму)
     * - string: всё остальное
     */
    public static DataType determineType(String line) {
        if (isInteger(line)) return DataType.INTEGER;
        if (isFloat(line)) return DataType.FLOAT;
        return DataType.STRING;
    }

    private static boolean isInteger(String s) {
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isFloat(String s) {
        try {
            Double.parseDouble(s);
            return s.contains(".") || s.toLowerCase().contains("e");
        } catch (NumberFormatException e) {
            return false;
        }
    }
}