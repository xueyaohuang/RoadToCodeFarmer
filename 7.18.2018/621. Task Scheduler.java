class Solution {
    public int leastInterval(char[] tasks, int n) {
        int len = tasks.length;
        int[] freq = new int[26];
        for (char c : tasks) {
            freq[c - 'A']++;
        }
        int maxFreq = 0;
        int numMaxFreq = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] > maxFreq) {
                maxFreq = freq[i];
                numMaxFreq = 1;
            }
            else if (freq[i] == maxFreq) {
                numMaxFreq++;
            }
        }
        return Math.max(len, (maxFreq - 1) * (n + 1) + numMaxFreq);
    }
}
