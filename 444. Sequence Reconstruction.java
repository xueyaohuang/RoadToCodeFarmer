// sequences是有序的数列，所以联想到topological sort，得到每个vertex的order，然后和nums里的order对比
// Whenever you see a question with partial ordering (a > b, c >d), you should immediately wonder if topological sort can find a mapping to satisfy that ordering.
// We want a single possible topological sort. So let's check if we have more than one option at every step.
// For a BFS Tpological sort, this means the queue should never have more than one option since more than one option ==> more than one ordering.
// Check if the topological sort you are generating matches the one expected in org.
// Do a bunch of crappy validation in the middle because the input test cases can be entirely nonsensical :(
class Solution {
    public boolean sequenceReconstruction(int[] nums, List<List<Integer>> sequences) {
        int n = nums.length;
        // get adj list. 这里需要用一个set避免计算重复的vertex，比如sequences = [[1,2],[1,3],[1,2,3]]
        // 如果用list，计算[1,2]时会把2放入1的adj list，计算[1,2,3]时又会把2放入1的adj list.
        Set<Integer>[] adj = new HashSet[n + 1];
        for (int i = 0; i <= n; i++) {
            adj[i] = new HashSet<>();
        }
        for (List<Integer> s : sequences) {
            for (int i = 0; i < s.size() - 1; i++) {
                adj[s.get(i)].add(s.get(i + 1));
            }
        }
        // get in degree
        int[] inDegree = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j : adj[i]) {
                inDegree[j]++;
            }
        }
        // get the source vertex
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        // order is the vertex's order in the topological sorted order, starts from 0.
        Map<Integer, Integer> order = new HashMap<>();
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            if (size != 1) {
              return false;
            }
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                order.put(cur, count);
                for (int j : adj[cur]) {
                    inDegree[j]--;
                    if (inDegree[j] == 0) {
                        queue.offer(j);
                    }
                }
            }
            count++;
        }
        // 如果order.get(nums[i]) > order.get(nums[i + 1])说明order不对，如果相等说明num不唯一
        for (int i = 0; i < n - 1; i++) {
            if (order.get(nums[i]) >= order.get(nums[i + 1])) {
                return false;
            }
        }
        return true;
    }
}
