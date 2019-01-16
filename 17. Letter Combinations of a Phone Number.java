// 注意这个题与78. Subsets，77. Combinations的区别
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

// 这个方法多了一层for loop，作用是选择结果里面有几个element。但是结果确定的，即digits的长度，所以退出条件加上了sb.length() == digits.length()
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
        if (sb.length() == digits.length()) {
            res.add(sb.toString());
            return;
        }
        for (int i = cur; i < digits.length(); i++) { // 这一层for loop相当于subsets里面选几个element。这个题是确定有两个element，所以前面退出条件加上了sb.length() == digits.length()
            String str = map[digits.charAt(i) - '0'];
            for (char c : str.toCharArray()) {
                sb.append(c);
                backtracking(res, digits, map, sb, i + 1);
                sb.deleteCharAt(sb.length() -  1);
            }
        }
    }
}

// 这个代码跟上面的相比删除了退出条件。输入是“23”的时候，结果对比如下：
// ["ad","ae","af","bd","be","bf","cd","ce","cf"]
// ["","a","ad","ae","af","b","bd","be","bf","c","cd","ce","cf","d","e","f"]
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
        res.add(sb.toString());
        for (int i = cur; i < digits.length(); i++) { // 这一层for loop相当于subsets里面选几个element。这个题是确定有两个element，所以前面退出条件加上了sb.length() == digits.length()
            String str = map[digits.charAt(i) - '0'];
            for (char c : str.toCharArray()) {
                sb.append(c);
                backtracking(res, digits, map, sb, i + 1);
                sb.deleteCharAt(sb.length() -  1);
            }
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
