class Solution {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        String s = String.join(" ", sentence) + " ";
        int len = s.length();
        int effectiveLen = 0;
        for (int i = 0; i < rows; i++) {
            effectiveLen += cols;
            // 说明这一行最后一个是字母，不需要占用cols来append一个空格，所以effectiveLen相当于增加一个
            if (s.charAt(effectiveLen % len) == ' ') {
                effectiveLen++;
            } else {
                while (effectiveLen > 0 && s.charAt((effectiveLen - 1) % len) != ' ') {
                    effectiveLen--;  // 这一行最后有几个空格相当于闲置了
                }
            }
        }
        return effectiveLen / len;
    }
}
