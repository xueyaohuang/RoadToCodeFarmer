class Solution {
    public String findContestMatch(int n) {
        String[] init = new String[n / 2];
        for (int i = 1; i <= n / 2; i++) {
            init[i - 1] = "(" + i + "," + (n + 1 - i) + ")";
        }
        int k = n / 2;
        while (k > 1) {
            init = constructStrs(init);
            k /= 2;
        }
        return init[0];
    }
    
    private String[] constructStrs(String[] init) {
        int n = init.length;
        String[] res = new String[n / 2];
        for (int i = 0; i < n / 2; i++) {
            res[i] = "(" + init[i] + "," + init[n - 1 - i] + ")";
        }
        return res;
    }
}
