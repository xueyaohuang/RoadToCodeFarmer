// 1. Limit max removal rmL and rmR for backtracking boundary. Otherwise it will exhaust all possible valid substrings, not shortest ones.
//     rmL，rmR是需要remove的open，close括号个数，必须都是大于等于0的，才能保证移除的括号数最少。
// 2. Scan from left to right, avoiding invalid strs (on the fly) by checking num of open parens.
//    open表示open与close括号之差，在任何时候，')'不能比'('多，所以open<0就是invalid，直接return
// 3. If it's '(', either use it, or remove it.
// 4. If it's ')', either use it, or remove it.
// 5. Otherwise just append it.

// In each step, make sure:
// 1. i does not exceed s.length().
// 2. Max removal rmL rmR and num of open parens are non negative.
// 3. De-duplicate by adding to a HashSet.

// 标准的backtrack有for loop，这里没有，因为这个题需要Remove the minimum number of invalid parentheses。也就是说结果需要时最长的，如果有for loop，从start接着开始，那么结果可能不是最长的。
// backtrack中for loop的作用，是从给定序列中间取一个，作为开头。

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        if (s == null) {
            return new ArrayList<>();
        }
        // 需要remove的open/close 括号个数
        int rmOpen = 0, rmClose = 0;
        Set<String> res = new HashSet<>();
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                rmOpen++;
            } else if (c == ')') {
                if (rmOpen > 0) {
                    rmOpen--;
                } else {
                    rmClose++;
                }
            }
        }
        backtrack(s, res, rmOpen, rmClose, 0, 0, new StringBuilder());
        return new ArrayList<String>(res);
    }
    
    private void backtrack(String s, Set<String> res, int rmOpen, int rmClose, int open, int index, StringBuilder sb) {
        if (rmOpen < 0 || rmClose < 0 || open < 0) {
            return;
        }
        if (index == s.length()) {
            if (rmOpen == 0 && rmClose == 0 && open == 0) {
                res.add(sb.toString());
            }
            return;
        }
        char c = s.charAt(index);
        int len = sb.length();
        if (c == '(') {
            backtrack(s, res, rmOpen - 1, rmClose, open, index + 1, sb); // not use '('
            backtrack(s, res, rmOpen, rmClose, open + 1, index + 1, sb.append(c)); // use '('
            // sb.setLength(sb.length() - 1); // 返回上层recursion，remove当前加入的元素
        } else if (c == ')') {
            backtrack(s, res, rmOpen, rmClose - 1, open, index + 1, sb); // not use ')'
            backtrack(s, res, rmOpen, rmClose, open - 1, index + 1, sb.append(c)); // use ')'
            // sb.setLength(sb.length() - 1);
        } else {
            backtrack(s, res, rmOpen, rmClose, open, index + 1, sb.append(c));
            // sb.setLength(sb.length() - 1);
        }
        sb.setLength(len); // 返回上层recursion，remove当前加入的元素
    }
}
