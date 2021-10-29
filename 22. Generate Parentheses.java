class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtracking(res, new StringBuilder(), n, n);
        return res;
    }
    
    private void backtracking(List<String> res, StringBuilder sb, int open, int close) {
        if (open == 0 && close == 0) {
            res.add(sb.toString());
            return;
        }
        if (open > 0) {
            sb.append('(');
            backtracking(res, sb, open - 1, close);
            sb.setLength(sb.length() -  1);
        }
        if (close > 0 && open < close) {
            sb.append(')');
            backtracking(res, sb, open, close - 1);
            sb.setLength(sb.length() -  1);
        }
    }
}

class Solution {
    public List<String> generateParenthesis(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        backtracking(n, res, new StringBuilder(), 0, 0);
        return res;
    }
    
    private void backtracking(int n, List<String> res, StringBuilder sb, int open, int close) {
        if (close == n) {
            res.add(sb.toString());
            return;
        }
        if (open < n) {
            sb.append('(');
            backtracking(n, res, sb, open + 1, close);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (close < open) {
            sb.append(')');
            backtracking(n, res, sb, open, close + 1);
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
