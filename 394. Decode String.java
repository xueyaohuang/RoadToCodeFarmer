// 存数字和存字母的stack分开
// res经常变
class Solution {
    public String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<String> strStack = new Stack<>();
        int idx = 0;
        String res = "";
        while (idx < s.length()) {
            if (Character.isDigit(s.charAt(idx))) {
                int count = s.charAt(idx++) - '0';
                while (Character.isDigit(s.charAt(idx))) {
                    count = count * 10 + s.charAt(idx++) - '0';
                }
                countStack.push(count);
            }
            else if (s.charAt(idx) == '[') {
                strStack.push(res);
                res = "";
                idx++;
            }
            else if (s.charAt(idx) == ']') {
                StringBuilder temp = new StringBuilder(strStack.pop());
                int repeat = countStack.pop();
                for (int i = 0; i < repeat; i++) {
                    temp.append(res);
                }
                res = temp.toString();
                idx++;
            }
            else {
                res += s.charAt(idx++);
            }
        }
        return res;
    }
}
