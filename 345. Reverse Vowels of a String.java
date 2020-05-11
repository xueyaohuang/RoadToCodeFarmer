class Solution {
    public String reverseVowels(String s) {
        char[] charr = s.toCharArray();
        Set<Character> set = new HashSet<>(Arrays.asList('A', 'E', 'I', 'O', 'U', 'a', 'e', 'i', 'o', 'u'));
        int i = 0;
        int j = charr.length -1;
        while (i < j) {
            if (!set.contains(charr[i])) {
                i++;
                continue;
            }
            if (!set.contains(charr[j])) {
                j--;
                continue;
            }
            char temp = charr[i];
            charr[i] = charr[j];
            charr[j] = temp;
            i++;
            j--;
        }
        return String.valueOf(charr);
    }
}

class Solution {
    public String reverseVowels(String s) {
        char[] ca = s.toCharArray();
        int i = 0;
        int j = ca.length - 1;
        while (i < j) {
            if (isVowel(ca[i]) && isVowel(ca[j])) {
                char temp = ca[i];
                ca[i] = ca[j];
                ca[j] = temp;
                i++;
                j--;
            } else if (isVowel(ca[i]) && !isVowel(ca[j])) {
                j--;
            } else if (!isVowel(ca[i]) && isVowel(ca[j])) {
                i++;
            } else {
                i++;
                j--;
            }
        }
        return String.valueOf(ca);
    }
    
    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' ||
            c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }
}
