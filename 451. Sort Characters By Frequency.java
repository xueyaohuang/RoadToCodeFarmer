class Solution {
    public String frequencySort(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int[] freq = new int[256];
        int maxFreq = 0;
        for (char c : s.toCharArray()) {
            freq[c]++;
            maxFreq = Math.max(maxFreq, freq[c]);
        }
        List<Character>[] list = new List[maxFreq + 1];
        // 不能loop s，因为s中的char有重复的，会多算几次
        for (int i = 0; i < 256; i++) {
            int count = freq[i];
            if (count > 0) {
                if (list[count] == null) {
                    list[count] = new ArrayList<>();
                }
                list[count].add((char)i);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = maxFreq; i > 0; i--) {
            if (list[i] != null) {
                for (char c : list[i]) {
                    for (int j = 0; j < i; j++) {
                        sb.append(c);
                    }
                }
                
            }
        }
        return sb.toString();
    }
}
