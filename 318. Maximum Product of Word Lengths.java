class Solution {
    public int maxProduct(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }
        int len = words.length;
        int[] bitMap = new int[len];
        int max = 0;
        
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                bitMap[i] |= 1 << (words[i].charAt(j) - 'a');
            }
        }
        
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if ((bitMap[i] & bitMap[j]) == 0) {
                    max = Math.max(max, words[i].length() * words[j].length());
                }
            }
        }
        
        return max;
    }
}
