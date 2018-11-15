class Solution {
    public boolean hasGroupsSizeX(int[] deck) {
        if (deck == null || deck.length == 0) {
            return false;
        }
        int minFreq = Integer.MAX_VALUE;
        int len = deck.length;
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < len; i++) {
            map.put(deck[i], map.getOrDefault(deck[i], 0) + 1);
        }
        
        for (int num : map.keySet()) {
            minFreq = Math.min(minFreq, map.get(num));
        }
        
        if (minFreq < 2) {
            return false;
        }
        
        for (int num : map.keySet()) {
            if (gcd(map.get(num), minFreq) < 2) {
                return false;
            }
        }
        return true;
    }
    
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
