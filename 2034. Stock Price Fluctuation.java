class StockPrice {
    
    TreeMap<Integer, Integer> times;
    TreeMap<Integer, Map<Integer, Integer>> prices;
    int max;
    int min;

    public StockPrice() {
        times = new TreeMap<>();
        prices = new TreeMap<>();
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
    }
    
    public void update(int timestamp, int price) {
        if (!times.containsKey(timestamp)) {
            max = Math.max(max, price);
            min = Math.min(min, price);
        } else {
            int prevPrice = times.get(timestamp);
            if (prices.get(prevPrice).size() == 1) {
                prices.remove(prevPrice);
                if (prices.size() == 0) {
                    max = price;
                    min = price;
                } else {
                    max = Math.max(price, prices.lastKey());
                    min = Math.min(price, prices.firstKey());
                }
            } else {
                prices.get(prevPrice).remove(timestamp);
                max = Math.max(max, price);
                min = Math.min(min, price);
            }
        }
        times.put(timestamp, price);
        prices.putIfAbsent(price, new HashMap<>());
        prices.get(price).put(timestamp, price);
    }
    
    public int current() {
        Integer lastKey = times.lastKey();
        return times.get(lastKey);
    }
    
    public int maximum() {
        return max;
    }
    
    public int minimum() {
        return min;
    }
}
