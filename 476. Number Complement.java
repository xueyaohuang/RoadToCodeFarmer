class Solution {
    public int findComplement(int num) {
        if (num == 0) {
            return 1;
        }
        int e = 0;
        int res = 0;
        while (num != 0) {
            int digit = num & 1;
            if (digit == 0) {
                res += (int)Math.pow(2, e);
            }
            e++;
            num >>= 1;
        }
        return res;
    }
}
