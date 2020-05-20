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
