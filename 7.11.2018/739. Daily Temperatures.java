class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) {
            return new int[0];
        }
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int idx = stack.pop();
                res[idx] = i - idx;
            }
            stack.push(i);
        }
        return res;
    }
}

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) {
            return new int[0];
        }
        int top = -1;
        int[] stack = new int[temperatures.length];
        int[] res = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            while(top > -1 && temperatures[i] > temperatures[stack[top]]) {
                int idx = stack[top--];
                res[idx] = i - idx;
            }
            stack[++top] = i;
            
        }
        return res;
    }
}

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) {
            return new int[0];
        }
        int[] res = new int[temperatures.length];
        res[temperatures.length - 1] = 0;
        for (int i = temperatures.length - 2; i >= 0; i--) {
            if (temperatures[i] < temperatures[i + 1]) {
                res[i] = 1;
            }
            else {
                if (res[i + 1] == 0) {
                   res[i] = 0;
                }
                else {
                    int j = i + 1;
                    res[i] = 1;
                    while (temperatures[i] >= temperatures[j]) {
                        if (res[j] == 0) {
                            res[i] = 0;
                            break;
                        }
                        res[i] += res[j];
                        j += res[j];  
                    }
                }    
            } 
        }
        return res;
    }
}
