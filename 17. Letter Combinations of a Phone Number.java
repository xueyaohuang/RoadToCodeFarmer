class Solution {
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        String[] map = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        backtracking(res, digits, map, new StringBuilder(), 0);
        return res;
    }
    
    private void backtracking(List<String> res, String digits, String[] map, StringBuilder sb, int cur) {
        if (cur == digits.length()) {
            res.add(sb.toString());
            return;
        }
        int idx = digits.charAt(cur) - '0';
        for (char c : map[idx].toCharArray()) {
            sb.append(c);
            backtracking(res, digits, map, sb, cur + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}

class Solution {
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new LinkedList<String>();
        }
        LinkedList<String> result = new LinkedList<>();
        String[] map = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        result.add("");
        for (int i = 0; i < digits.length(); i++) {
            int num = digits.charAt(i) - '0';
            while (result.peek().length() == i) {
                String str = result.remove();
                for (char c : map[num].toCharArray()) {
                    result.add(str + c);
                }
            }
        }
        return result;
    }
}
