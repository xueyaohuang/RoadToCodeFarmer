class StockSpanner {
    
    int day;
    Deque<int[]> stack;

    public StockSpanner() {
        day = 0;
        stack = new ArrayDeque<>();
        stack.push(new int[]{-1, Integer.MAX_VALUE});
    }
    
    public int next(int price) {
        while (!stack.isEmpty() && price >= stack.peek()[1]) {
            stack.pop();
        }
        int prev = stack.isEmpty() ? 0 : stack.peek()[0];
        int res = day - prev;
        stack.push(new int[]{day, price});
        day++;
        return res;
    }
}

class StockSpanner {

    Deque<int[]> stack;
    public StockSpanner() {
        stack = new ArrayDeque<>();
    }
    
    public int next(int price) {
        int res = 1;
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            res += stack.pop()[1];
        }
        stack.push(new int[]{price, res});
        return res;
    }
}

// very slow
class StockSpanner {
    
    List<Integer> list;

    public StockSpanner() {
        list = new ArrayList<>();
    }
    
    public int next(int price) {
        list.add(price);
        int idx = list.size() - 2;
        int count = 1;
        while (idx >= 0) {
            if (list.get(idx) <= price) {
                count++;
                idx--;
            } else {
                return count;
            }
        }
        return count;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */
