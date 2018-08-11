class Solution {
    public void reverseWords(char[] str) {
        reverseHelper(str, 0, str.length - 1);
        int i = 0;
        int j = 0;
        while (j < str.length - 1) {
            if (str[j] != ' ') {
                j++;
            } else {
                reverseHelper(str, i, j - 1);
                i = j + 1;
                j = i;
            }
        }
        reverseHelper(str, i, j); // 最后一个单词还没有reverse，在while外面多一步reverse
    }
    private void reverseHelper(char[] s, int start, int end) {
        while (start < end) {
            char ch = s[start];
            s[start] = s[end];
            s[end] = ch;
            start++;
            end--;
        }
    }
}
