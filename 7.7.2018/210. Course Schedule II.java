// DFS


// BFS
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] adj = new ArrayList[numCourses];
        int[] inDegree = new int[numCourses];
        Queue<Integer> finished = new LinkedList<>();
        int canFinish = 0;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < prerequisites.length; i++) {
            adj[prerequisites[i][1]].add(prerequisites[i][0]);
            inDegree[prerequisites[i][0]]++;
        }
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                finished.offer(i);
            }
        }
        while (!finished.isEmpty()) {
            int curCourse = finished.poll();
            res.add(curCourse);
            canFinish++;
            for (int i = 0; i < adj[curCourse].size(); i++) {
                int nextCourse = adj[curCourse].get(i);
                inDegree[nextCourse]--;
                if (inDegree[nextCourse] == 0) {
                    finished.offer(nextCourse);
                }
            }
        }
        if (canFinish == numCourses) {
            int[] ans = new int[res.size()];
            int i = 0;
            for (int num : res) {
                ans[i++] = num;
            }
            return ans;
        }   
        else {
            return new int[0];
        }
    }
}
