class Solution {
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        String s = "1";
        for (int i = 2; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < s.length();) {
                char say = s.charAt(j);
                int count = 1;
                j++;
                while (j < s.length() && s.charAt(j - 1) == s.charAt(j)) {
                    j++;
                    count++;
                }
                sb.append(count).append(say);
            }
            s = sb.toString();
        }
        return s;
    }
}

class Solution {
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        String cur = "1";
        StringBuilder sb = new StringBuilder();
        while (n > 1) {
            for (int i = 0; i < cur.length(); i++) {
                char c = cur.charAt(i);
                int count = 1;
                while (i < cur.length() - 1 && cur.charAt(i + 1) == c) {
                    i++;
                    count++;
                }
                sb.append(count).append(c);
            }
            cur = sb.toString();
            sb = new StringBuilder();
            n--;
        }
        return cur;
    }
}
