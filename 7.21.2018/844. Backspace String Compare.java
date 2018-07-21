class Solution {
    public boolean backspaceCompare(String S, String T) {
        Stack<Character> stackS = new Stack<>();
        Stack<Character> stackT = new Stack<>();
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
