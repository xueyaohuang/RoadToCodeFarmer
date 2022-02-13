// 和word ladder一模一样
class Solution {
    public int openLock(String[] deadends, String target) {
        Set<String> deadendsSet = new HashSet<>(Arrays.asList(deadends));
        if (deadendsSet.contains("0000")) {
            return -1;
        }
        int step = 0;
        Set<String> start = new HashSet<>();
        start.add("0000");
        Set<String> used = new HashSet<>();
        used.add("0000");
        while (start.size() > 0) {
            Set<String> temp = new HashSet<>();
            for (String s : start) {
                if (s.equals(target)) {
                    return step;
                }
                char[] cs = s.toCharArray();
                for (int i = 0; i < cs.length; i++) {
                    char c = cs[i];
                    int digit = c - '0';
                    // digit +1
                    char forward = (char)((digit + 1) % 10 + '0');
                    cs[i] = forward;
                    String next = String.valueOf(cs);
                    if (!deadendsSet.contains(next) && !used.contains(next)) {
                        temp.add(next);
                        used.add(next);
                    }
                    // digit -1
                    char backward = (char)((digit - 1 + 10) % 10 + '0');
                    cs[i] = backward;
                    next = String.valueOf(cs);
                    if (!deadendsSet.contains(next) && !used.contains(next)) {
                        temp.add(next);
                        used.add(next);
                    }
                    cs[i] = c;
                }
            }
            start = temp;
            step++;
        }
        return -1;
    }
}
