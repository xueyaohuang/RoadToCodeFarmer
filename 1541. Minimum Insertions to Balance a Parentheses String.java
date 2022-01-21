/*
Use a stack to keep opening brackets. If you face single closing ')' add 1 to the answer and consider it as '))'.
If you have '))' with empty stack, add 1 to the answer, If after finishing you have x opening remaining in the stack, add 2x to the answer.
*/
class Solution {
    public int minInsertions(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push('(');
            } else {
                // has '))'
                if (i < s.length() - 1 && s.charAt(i + 1) == ')') {
                    if (!stack.isEmpty()) { // can pair with a '('
                        stack.pop();
                    } else {
                        count++; // can not pair with a '(', so count++
                    }
                    i++;
                } else {  // single ')'
                    count++;  // single ')', make it '))' first and see if there is a '(' in the stack to pair with it
                    if (!stack.isEmpty()) {
                        stack.pop();
                    } else {
                        count++;
                    }
                }
            }
        }
        return count + stack.size() *  2;
    }
}

// Stack can be replaced with a counter (count num of '(') as we are guaranteed to have only '(' and ')'
// almost the same as solution 1
class Solution {
    public int minInsertions(String s) {
        int open = 0;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                open++;
            } else {
                if (i < s.length() - 1 && s.charAt(i + 1) == ')') {
                    if (open > 0) {
                        open--;
                    } else {
                        count++;
                    }
                    i++;
                } else {
                    count++;
                    if (open > 0) {
                        open--;
                    } else {
                        count++;
                    }
                }
            }
        }
        return count + open *  2;
    }
}
