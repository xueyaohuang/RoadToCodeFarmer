// time complexity of Java Stringbuilder setLength (用来删除sb里最后一个char)
// https://stackoverflow.com/questions/35110696/java-stringbuilder-setlength-is-time-complexity-o1
// Time complexity O(1)
class Solution {
    public String removeDuplicates(String s, int k) {
        // 用stringbuilder模拟stack
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (char c : s.toCharArray()) {
            if (sb.length() > 0 && sb.charAt(sb.length() - 1) == c) {
                count++;
            } else {
                count = 1;
            }
            if (count < k) {
                sb.append(c);
            } else {
                // delete k-1 个 当前的char
                for (int i = 0; i < k - 1; i++) {
                    sb.setLength(sb.length() - 1);
                }
                // 重新数现在有多少个连续重复的char
                count = sb.length() > 0 ? 1 : 0;
                if (count > 0) {
                    char cur = sb.charAt(sb.length() - 1);
                    int j = sb.length() - 2;
                    while (j >= 0 && sb.charAt(j) == cur) {
                        count++;
                        j--;
                    }
                }
            }
        }
        return sb.toString();
    }
}
