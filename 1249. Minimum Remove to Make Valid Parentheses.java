/*
counting the number of invalid ( and removing the invalid ) in the first pass.
If there are open number of invalid ( left, we just need to remove them from the end in the second pass.

*/
class Solution {
    public String minRemoveToMakeValid(String s) {
        int open = 0;
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                open++;
            } else if (c == ')') {
                if (open == 0) {
                    continue;
                }
                open--;
            }
            sb.append(c);
        }
        StringBuilder res = new StringBuilder();
        for (int i = sb.length() - 1; i >= 0; i--) {
            char c = sb.charAt(i);
            if (c == '(' && open > 0) {
                open--;
                continue;
            }
            res.append(c);
        }
        return res.reverse().toString();
    }
}
