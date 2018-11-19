class Solution {
    public String reverseOnlyLetters(String S) {
        if (S == null || S.length() == 0) {
            return S;
        }
        char[] chs = S.toCharArray();
        int i = 0, j = S.length() - 1;
        while (i < j) {
            if (!Character.isLetter(chs[i])) {
                i++;
                continue;
            }
            if (!Character.isLetter(chs[j])) {
                j--;
                continue;
            }
            swap(chs, i, j);
            i++;
            j--;
        }
        return new String(chs);
    }
    
    private void swap(char[] chs, int i, int j) {
        char temp = chs[i];
        chs[i] = chs[j];
        chs[j] = temp;
    }
}
