// way better, 先构建一个lookup table，isPalindrome[i][j]
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        int len = s.length();
        boolean[][] isPalindrome = new boolean[len][len];
        for (int j = 0; j < len; j++) {
            for (int i = 0; i <= j; i++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || isPalindrome[i + 1][j - 1])) {
                    isPalindrome[i][j] = true;
                }
            }
        }
        backtracking(s, res, isPalindrome, new ArrayList<>(), 0);
        return res;
    }
    
    private void backtracking(String s, List<List<String>> res, boolean[][] isPalindrome, List<String> temp, int start) {
        if (start == s.length()) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (isPalindrome[start][i]) {
                temp.add(s.substring(start, i + 1));
                backtracking(s, res, isPalindrome, temp, i + 1);
                temp.remove(temp.size() - 1);
            }
        }
    }
}

class Solution {
    public List<List<String>> partition(String s) {
        int n = s.length();
        boolean[][] isPalindrome = new boolean[n][n];
        for (int j = 0; j < n; j++) {
            for (int i = j; i >= 0; i--) {
                if (i == j) {
                    isPalindrome[i][j] = true;
                } else if (i + 1 == j) {
                    isPalindrome[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    if (isPalindrome[i + 1][j - 1] && s.charAt(i) == s.charAt(j)) {
                        isPalindrome[i][j] = true;
                    }
                }
            }
        }
        List<List<String>> res = new ArrayList<>();
        backtracking(s, res, isPalindrome, 0, new ArrayList<>());
        return res;
    }
    
    private void backtracking(String s, List<List<String>> res, boolean[][] isPalindrome, int start, List<String> temp) {
        if (start == s.length()) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (!isPalindrome[start][i]) {
                continue;
            }
            temp.add(s.substring(start, i + 1));
            backtracking(s, res, isPalindrome, i + 1, temp);
            temp.remove(temp.size() - 1);
        }
    }
}

class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return res;
        }
        backtrack(s, res, new ArrayList<String>(), 0);
        return res;
    }
    private void backtrack(String s, List<List<String>> res, ArrayList<String> temp, int start) {
        if (start == s.length()) {
            res.add(new ArrayList<String>(temp));
        }
        for (int i = start; i < s.length(); i++) {
            if (isPalindrome(s, start, i)) {
                temp.add(s.substring(start, i + 1));
                backtrack(s, res, temp, i + 1);
                temp.remove(temp.size() - 1);
            }
        }
    }
    private boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
