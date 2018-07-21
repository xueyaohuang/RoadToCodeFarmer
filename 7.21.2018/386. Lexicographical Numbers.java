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
        Integer[] res = new Integer[n]; // 与上个解法一样，只是换用Integer array，Integer array很快，不知道为啥
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

class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();
        lexicalOrderHelper(1, n, res);
        return res;
    }
    private void lexicalOrderHelper(int cur, int n, List<Integer> res) { // preorder traversal
        if (cur > n) {
            return;
        }
        res.add(cur); // add root
        lexicalOrderHelper(cur * 10, n, res); // add left
        if (cur + 1 <= cur /10 * 10 + 9) {
            lexicalOrderHelper(cur + 1, n, res); // add right
        }
    }
}
