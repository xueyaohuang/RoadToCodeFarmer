class Solution {
    public int countPrimes(int n) {
        if (n < 3) {
            return 0;
        }
        boolean[] notPrime = new boolean[n];
        int count = 0;
        for (int i = 3; i < n; i += 2) {
            if (! notPrime[i]) {
                count++;
            }
            for (int j = 3; i * j < n; j += 2) {
                notPrime[i * j] = true;
            }
        }
        return count + 1; //i start from 3, missing 2, so plus 1.
    }
}
