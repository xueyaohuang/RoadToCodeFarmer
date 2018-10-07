class Solution {
    public boolean canCross(int[] stones) {
        if (stones == null || stones.length == 0) {
            return true;
        }
        
        int len = stones.length;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        
        for (int i = 0; i < len; i++) {
            map.put(stones[i], new HashSet<>());
        }
        map.get(0).add(1);
        
        for (int i = 0; i < len - 1; i++) {
            int stone = stones[i];
            for (int step : map.get(stone)) {
                int nextStone = stones[i] + step;
                if (nextStone == stones[len - 1]) {
                    return true;
                }
                Set<Integer> nextStoneSet = map.get(nextStone);
                if (nextStoneSet != null) {
                    nextStoneSet.add(step);
                    nextStoneSet.add(step + 1);
                    if (step >= 1) {
                        nextStoneSet.add(step - 1);
                    }
                }
            }
        }
        return false;
    }
}
