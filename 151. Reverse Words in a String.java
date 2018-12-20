public class Solution {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        String[] strs = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = strs.length - 1; i >= 0; i--) {
            sb.append(strs[i]).append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}


public class Solution {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        s = s.trim();
        int end = s.length() - 1;
        StringBuilder sb = new StringBuilder();
        while (end >= 0) {
            while (s.charAt(end) == ' ') {
                end--;
            }
            int index = s.lastIndexOf(' ', end);
            sb.append(s.substring(index + 1, end + 1)).append(' ');
            end = index - 1;
        }
        return sb.toString().trim();
    }
}
