class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for (int i = 0; i < tasks.length; i++) {
            freq[tasks[i] - 'A']++;
        }
        int maxFreq = 0; // 出现频率最高的频率
        int maxCount = 0; // 有几个task的频率是最高频率
        for (int i = 0; i < 26; i++) {
            if (freq[i] > maxFreq) {
                maxFreq = freq[i];
                maxCount = 1;
            } else if (freq[i] == maxFreq) {
                maxCount++;
            }
        }
        return Math.max(tasks.length, (maxFreq - 1) * (n + 1) + maxCount);
    }
}
// 用最高频率的tasks搭骨架：(maxFreq - 1) * (n + 1)，剩下的tasks往骨架空隙里面填，最后加上maxCount
// 如果n不是决定性因素，就要取tasks.length 和 (maxFreq - 1) * (n + 1) + maxCount 中大的那个，比如：
// ["A","A","A","B","B","B"]
// 0
