class Solution {
    public String addBoldTag(String s, String[] dict) {
        int len = s.length();
        boolean[] bold = new boolean[len];
        for (String word : dict) {
            int idx = s.indexOf(word);
            while (idx != -1) {
                for (int i = idx; i < idx + word.length(); i++) {
                    bold[i] = true;
                }
                idx++;
                idx = s.indexOf(word, idx);
            }
        }
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        while (idx < len) {
            if (bold[idx]) {
                sb.append("<b>");
                while (idx < len && bold[idx]) {
                    sb.append(s.charAt(idx++));
                }
                sb.append("</b>");
            } else {
                sb.append(s.charAt(idx++));
            }
        }
        return sb.toString();
    }
}
