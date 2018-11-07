class Solution {
    public String addBoldTag(String s, String[] dict) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int len = s.length();
        boolean[] bold = new boolean[len];
        for (int i = 0; i < dict.length; i++) {
            String word = dict[i];
            int idx = s.indexOf(word);
            while (idx != -1) {
                for (int j = idx; j < idx + word.length(); j++) {
                    bold[j] = true;
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
                    sb.append(s.charAt(idx));
                    idx++;
                }
                sb.append("</b>");
            }
            if (idx < len) {
                while (idx < len && !bold[idx]) {
                    sb.append(s.charAt(idx));
                    idx++;
                }
            }
        }
        
        return sb.toString();
    }
}
