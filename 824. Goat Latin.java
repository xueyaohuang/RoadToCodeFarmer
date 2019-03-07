class Solution {
    public String toGoatLatin(String S) {
        StringBuilder builder = new StringBuilder(S.length());
        boolean start = true;
        boolean vowelStart = false;
        char vowel = 0;
        int index = 0;
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (start) {
                start = false;
                vowelStart = isVowel(c);
                vowel = c;
                if (vowelStart) {
                    builder.append(vowel);
                }
            } else if (c == ' ') {
                buildTail(index, vowelStart, vowel, builder);
                builder.append(' ');
                index++;
                start = true;
            } else {
                builder.append(c);
            }
        }
        buildTail(index, vowelStart, vowel, builder);
        return builder.toString();
    }
    
    private void buildTail(int index, boolean vowelStart, char vowel, StringBuilder builder) {
        if (!vowelStart) {
            builder.append(vowel);
        }
        builder.append("ma");
        for (int j = 0; j <= index; j++) {
            builder.append('a');
        }
    }
    
    private boolean isVowel(char c) {
        return c == 'A' || c == 'a' || c == 'E' || c == 'e' || 
               c == 'I' || c == 'i' || c == 'O' || c == 'o' ||
               c == 'U' || c == 'u';
    }
}

class Solution {
    public String toGoatLatin(String S) {
        StringBuilder sb = new StringBuilder();
        StringBuilder surfix = new StringBuilder("a");
        String[] strs = S.split("\\s+");
        for (int i = 0; i < strs.length; i++) {
            sb.append(convertString(strs[i])).append(surfix).append(" ");
            surfix.append("a");
        }
        return sb.toString().trim();
    }
    
    private String convertString(String s) {
        char c = s.charAt(0);
        StringBuilder sb = new StringBuilder(s);
        if (isVowel(c)) {
            sb.append("ma");
        } else {
            sb.deleteCharAt(0);
            sb.append(c).append("ma");
        }
        return sb.toString();
    }
    
    private boolean isVowel(char c) {
        return c == 'A' || c == 'a' || c == 'E' || c == 'e' || 
               c == 'I' || c == 'i' || c == 'O' || c == 'o' ||
               c == 'U' || c == 'u';
    }
}
