class Solution {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> res = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (valid(i)) {
                res.add(i);
            }
        }
        return res;
    }
    
    private boolean valid(int num) {
        int n = num;
        while (n != 0) {
            int digit = n % 10;
            if (digit == 0 || num % digit != 0) {
                 return false;
            }
            n /= 10;
        }
        return true;
    }    
}
