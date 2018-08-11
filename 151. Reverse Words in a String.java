public class Solution {
    public String reverseWords(String s) {
        String[] temp = s.trim().split(" +");
        String res = "";
        for (int i = temp.length - 1; i >= 0; i--) {
            res = res + temp[i] + " ";
        }
        return res.trim();
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
