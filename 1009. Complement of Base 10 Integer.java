class Solution {
    public int bitwiseComplement(int N) {
        if (N == 0) {
            return 1;
        }
        int e = 0;
        int res = 0;
        while (N != 0) {
            int digit = N & 1;
            if (digit == 0) {
                res += (int)Math.pow(2, e);
            }
            e++;
            N >>= 1;
        }
        return res;
    }
}
