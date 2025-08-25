package org.yeehy.statistic;

public class NumericStats {
    private long count = 0;
    private double sum = 0.0;
    private double min = Double.MAX_VALUE;
    private double max = -Double.MAX_VALUE;

    public void addNumber(double value) {
        count++;
        sum += value;
        if (value < min) min = value;
        if (value > max) max = value;
    }

    public long getCount() { return count; }
    public double getSum() { return sum; }
    public double getMin() { return count == 0 ? Double.NaN : min; }
    public double getMax() { return count == 0 ? Double.NaN : max; }
    public double getAverage() { return count == 0 ? Double.NaN : sum / count; }
}