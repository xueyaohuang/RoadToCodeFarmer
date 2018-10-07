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

class Solution {
    public boolean canCross(int[] stones) {
        if (stones[1] - stones[0] != 1) {
            return false;
        } else if (stones.length == 2) {
            return true;
        }

        int jump;
        for (int i = stones.length - 2; i > 0; i--) {
            jump = stones[stones.length - 1] - stones[i];
            if (jump > i + 1) {
                return false;
            }
            if (canJump(stones, jump, i)) {
                return true;
            }
        }
        return false;
    }
    public static boolean canJump(int[] stones, int jump, int stone) {
        if (stone == 1) {
            return jump < 3;
        } else if (stone == 0) {
            return false;
        }
        for (int cur = jump - 1; cur <= jump + 1; cur++) {
            if (cur < 1) {
                continue;
            }
            for (int i = stone - 1; i > 0; i--) {
                if (stones[stone] - stones[i] > jump + 1) {
                    break;
                }
                if (stones[stone] - stones[i] == cur && canJump(stones, cur, i)) {
                    return true;
                }
            }
        }
        return false;
    }
}
