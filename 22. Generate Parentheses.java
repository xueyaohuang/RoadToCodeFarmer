class Solution {
    public List<String> generateParenthesis(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        backtracking(n, sb, res, 0, 0);
        return res;
    }
    
    private void backtracking(int n, StringBuilder sb, List<String> res, int open, int close) {
        if (sb.length() == 2 * n) {
            res.add(sb.toString());
            return;
        }
        if (open < n) {
            sb.append("(");
            backtracking(n, sb, res, open + 1, close);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (open > close) {
            sb.append(")");
            backtracking(n, sb, res, open, close + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        backtracking(list, "", 0, 0, n);
        return list;
    }
    private void backtracking(List list, String str, int open, int close, int max) {
        if (close == max) {
            list.add(str);
            return;
        }
        if (open < max) {
            backtracking(list, str + "(", open + 1, close, max);
        }
        if (close < open) {
            backtracking(list, str + ")", open, close + 1, max);
        }
    }
}
