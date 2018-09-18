class Solution {
    public boolean backspaceCompare(String S, String T) {
        Deque<Character> stackS = new ArrayDeque<>();
        Deque<Character> stackT = new ArrayDeque<>();
        for (char s : S.toCharArray()) {
            if (s == '#') {
                if (!stackS.isEmpty()) {
                    stackS.pop();
                }
            }
            else {
                stackS.push(s);
            }
        }
        for (char t : T.toCharArray()) {
            if (t == '#') {
                if (!stackT.isEmpty()) {
                    stackT.pop();
                }
            }
            else {
                stackT.push(t);
            }
        }
        if (stackS.size() != stackT.size()) {
            return false;
        }
        while (!stackS.isEmpty()) {
            if (stackS.pop() != stackT.pop()) {
                return false;
            }
        }
        return true;
    }
}

class Solution {
    public boolean backspaceCompare(String S, String T) {
        if (S == null || T == null) {
            return false;
        }
        String s = modifyString(S);
        String t = modifyString(T);
        return s.equals(t);
    }
    private String modifyString(String str) {
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) { // 倒序遍历，可以处理一开始就是#的情况
            char c = str.charAt(i);
            if (c == '#') {
                count++;
            }
            else {
                if (count > 0) {
                    count--;
                }
                else {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
}
class Solution {
    public boolean backspaceCompare(String S, String T) {
        int i = S.length() - 1;
        int j = T.length() - 1;
        int countA = 0;
        int countB = 0;
        while (i >= 0 || j >= 0) {
            while (i >= 0 && (countA > 0 || S.charAt(i) == '#')) {
                countA += S.charAt(i--) == '#' ? 1 : -1;
            }
            while (j >= 0 && (countB > 0 || T.charAt(j) == '#')) {
                countB += T.charAt(j--) == '#' ? 1 : -1;
            }
            if (i < 0 || j < 0) {
                return i == j;
            }
            if (S.charAt(i--) != T.charAt(j--)) {
                return false;
            }
        }
        return true;
    }
}
