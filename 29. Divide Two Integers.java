class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        int sign = (dividend > 0) ^ (divisor > 0) ? -1 : 1;
        long dvd =  Math.abs((long)dividend);
        long dvs =  Math.abs((long)divisor);
        int res = 0;
        while (dvd >= dvs) {
            long temp = dvs;
            long multiplier = 1;
            while ((temp << 1) <= dvd) {
                temp <<= 1;
                multiplier <<= 1;  
            }
            dvd -= temp;
            res += multiplier;
        }
        return sign == 1 ? res : -res;
    }
}
