// DFS
// 这就是topological sort，当完成对一个点的访问后，加入list中，dfs是逆序，所以要用stack。
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) {
            return new int[0];
        }
        List<Integer>[] adj = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] prerequisite : prerequisites) {
            adj[prerequisite[1]].add(prerequisite[0]);
        }
        Deque<Integer> stack = new ArrayDeque<>();
        int[] visited = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) {
                if (hasCycle(adj, i, visited, stack)) {
                    return new int[0];
                }
            }
        }
        int[] res = new int[numCourses];
        int idx = 0;
        while (!stack.isEmpty()) {
            res[idx++] = stack.pop();
        }
        return res;
    }
    
    private boolean hasCycle(List<Integer>[] adj, int i, int[] visited, Deque<Integer> stack) {
        if (visited[i] == 2) {
            return false;
        }
        if (visited[i] == 1) {
            return true;
        }
        visited[i] = 1;
        for (int j : adj[i]) {
            if (hasCycle(adj, j, visited, stack)) {
                return true;
            }
        }
        visited[i] = 2;
        stack.push(i);
        return false;
    }
}

// BFS
// 这就是topological sort，当完成对一个点的访问后，加入list中，bfs是正序，所以直接加。
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
            int[] ans = new int[numCourses];
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
