class Solution {
    
    private static final char[] BEGS = {'0', '1', '6', '8', '9'};
    private static final char[] ENDS = {'0', '1', '9', '8', '6'};
    
    public List<String> findStrobogrammatic(int n) {
        List<String> res = new ArrayList<>();
        char[] buffer = new char[n];
        dfs(buffer, 0, n - 1, res);
        return res;
    }
    
    private static void dfs(char[] buffer, int beg, int end, List<String> res) {
        if (beg > end) {
            res.add(new String(buffer));
            return;
        }
        
        for (int i = 0; i < BEGS.length; i++) {
            char cBeg = BEGS[i];
            char cEnd = ENDS[i];
            if (beg == 0 && cBeg == '0' && end != 0) {
                continue;
            }
            if (beg == end && (cBeg == '6' || cBeg == '9')) { //当ｎ为基数时，中间位这个数pair不能为6，9 or 9, 6
                continue;
            }
            buffer[beg] = cBeg;
            buffer[end] = cEnd;
            dfs(buffer, beg + 1, end - 1, res);
        }
    }
}

class Solution {
    public List<String> findStrobogrammatic(int n) {
        return findStrobogrammaticHelper(n, n);
    }
    
    private List<String> findStrobogrammaticHelper(int m, int n) {
        if (m == 0) {
            return Arrays.asList("");
        }
        if (m == 1) {
            return Arrays.asList("0", "1", "8");
        }
        List<String> list = findStrobogrammaticHelper(m - 2, n);
        List<String> res = new ArrayList<>();
        for (String cur : list) {
            if (m != n) {
                res.add("0" + cur + "0");
            }
            res.add("1" + cur + "1");
            res.add("8" + cur + "8");
            res.add("6" + cur + "9");
            res.add("9" + cur + "6");
        }
        return res;
    }
}
