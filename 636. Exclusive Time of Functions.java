class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        if (n == 0 || logs == null || logs.size() == 0) {
            return new int[0];
        }
        Deque<Integer> stack = new ArrayDeque<>();
        int[] res = new int[n];
        int prevTime = 0;

        for (String log : logs) {
            String[] info = log.split(":");
            int id = Integer.valueOf(info[0]);
            int time = Integer.valueOf(info[2]);
            
            if (info[1].equals("start")) {
                if (!stack.isEmpty()) {
                    res[stack.peek()] += time - prevTime;
                }
                stack.push(id);
                prevTime = time;
            } else {
                res[stack.pop()] += time - prevTime + 1;
                prevTime = time + 1; // 巧妙之处
            }
        }
        return res;
    }
}
