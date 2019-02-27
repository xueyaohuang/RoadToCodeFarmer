class Solution {
    public int consecutiveNumbersSum(int N) {
        int count = 0;
        int k = 0;
        while (k * k + k - 2 * N < 0 ) {
            if ((k * k + k - 2 * N) % (2 * k + 2)  == 0) {
                count++;
            }
            k++;
        }
        return count;
    }
}
