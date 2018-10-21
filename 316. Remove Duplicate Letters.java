class Solution {
    public String removeDuplicateLetters(String s) {
        if (s == null) {
            return s;
        }
        int[] freq = new int[26];
        // stack里是否由当前的char
        boolean[] used = new boolean[26];
        char[] sCharArray = s.toCharArray();
        Deque<Character> stack = new ArrayDeque<>();
        
        for (char c : sCharArray) {
            freq[c - 'a']++;
        }
        
        for (char c : sCharArray) {
            freq[c - 'a']--; // 这一步一定要在最前面
            if (used[c - 'a']) { // 如果stack里有这个char，继续
                continue;
            }
            //if current character is smaller than last character in stack which occurs later in the string again
            //it can be removed and  added later e.g stack = bc remaining string abc then a can pop b and then c
            while (!stack.isEmpty() && c < stack.peek() && freq[stack.peek() - 'a'] > 0) {
                used[stack.pop()- 'a'] = false;
            }
            stack.push(c);
            used[c - 'a'] = true;
        }
        
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        
        return result.reverse().toString();
    }
}
