class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        // stack 里是[function_id, start_time]
        Deque<int[]> stack = new ArrayDeque<>();
        for (String s : logs) {
            String[] log = s.split(":");
            int func = Integer.parseInt(log[0]);
            int time = Integer.parseInt(log[2]);
            if (log[1].equals("start")) {
                if (!stack.isEmpty()) {
                    int[] prevFunc = stack.peek();
                    res[prevFunc[0]] += time - prevFunc[1];
                }
                stack.push(new int[]{func, time});
            } else {
                int[] cur = stack.pop();
                res[cur[0]] += time - cur[1] + 1;
                if (!stack.isEmpty()) {
                    // reset the start time of previous function
                    stack.peek()[1] = time + 1;
                }
            }
        }
        return res;
    }
}

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
