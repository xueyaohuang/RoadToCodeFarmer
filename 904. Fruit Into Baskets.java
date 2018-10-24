class Solution {
    public int totalFruit(int[] tree) {
        if (tree == null || tree.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0, len = tree.length;
        int left = 0, right = 0;
        int max = 0;
        for (; right < len; right++) {
            int type = tree[right];
            if (!map.containsKey(type) || map.get(type) == 0) {
                count++;
                map.put(type, 1);  
            } else {
                map.put(type, map.get(type) + 1);
            }
            
            while (count > 2) {
                int toBeRemoved = tree[left];
                if (map.get(toBeRemoved) == 1) {
                    count--;
                }
                map.put(toBeRemoved, map.get(toBeRemoved) - 1);
                left++;
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}
