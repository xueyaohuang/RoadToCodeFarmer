class Solution {
    public int sumSubarrayMins(int[] arr) {
        int res = 0;
        int mod = (int)Math.pow(10, 9) + 7;
        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            res += min;
            res %= mod;
            for (int j = i + 1; j < arr.length; j++) {
                min = Math.min(min, arr[j]);
                res += min;
                res %= mod;
            }
        }
        return res;
    }
}
