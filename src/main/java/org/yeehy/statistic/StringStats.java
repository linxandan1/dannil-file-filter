package org.yeehy.statistic;

public class StringStats {
    private long count = 0;
    private int minLength = Integer.MAX_VALUE;
    private int maxLength = 0;

    public void addString(String s) {
        count++;
        int len = s.length();
        if (len < minLength) minLength = len;
        if (len > maxLength) maxLength = len;
    }

    public long getCount() { return count; }
    public int getMinLength() { return count == 0 ? 0 : minLength; }
    public int getMaxLength() { return count == 0 ? 0 : maxLength; }
}
