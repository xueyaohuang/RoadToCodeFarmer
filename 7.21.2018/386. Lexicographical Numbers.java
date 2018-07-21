class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();
        int cur = 1;
        for (int i = 0; i < n; i++) {
            res.add(cur);
            if (cur * 10 <= n) {
                cur *= 10;
            }
            else {
                if (cur == n) {
                    cur /= 10;
                }
                cur++;
                while (cur % 10 == 0) {
                    cur /= 10;
                }
            }
        }
        return res;
    }
}

class Solution {
    public List<Integer> lexicalOrder(int n) {
        Integer[] res = new Integer[n]; // 用Integer array很快，不知道为啥
        int cur = 1;
        for (int i = 0; i < n; i++) {
            res[i] = cur;
            if (cur * 10 <= n) {
                cur *= 10;
            }
            else {
                if (cur == n) {
                    cur /= 10;
                }
                cur++;
                while (cur % 10 == 0) {
                    cur /= 10;
                }
            }
        }
        return Arrays.asList(res);
    }
}
