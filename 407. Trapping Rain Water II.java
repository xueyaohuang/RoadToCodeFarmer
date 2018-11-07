class Solution {
    int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        int n = (m == 0 ? 0 : heightMap[0].length);
        int res = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            pq.offer(new int[] {i, 0, heightMap[i][0]});
            pq.offer(new int[] {i, n - 1, heightMap[i][n - 1]});
            visited[i][0] = visited[i][n - 1] = true;
        }

        for (int j = 1; j < n - 1; j++) {
            pq.offer(new int[] {0, j, heightMap[0][j]});
            pq.offer(new int[] {m - 1, j, heightMap[m - 1][j]});
            visited[0][j] = visited[m - 1][j] = true;
        }

        while (!pq.isEmpty()) {
            int[] cell = pq.poll();

            for (int[] d : dirs) {
                int i = cell[0] + d[0], j = cell[1] + d[1];
                if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j]) continue;
                res += Math.max(0, cell[2] - heightMap[i][j]);
                pq.offer(new int[] {i, j, Math.max(heightMap[i][j], cell[2])});
                visited[i][j] = true;
            }
        }

        return res;
    }
}

class Solution {
public class Container implements Comparable<Container>{
        int height;
        int x;
        int y;
        public Container(int h, int i, int j) {
            height=h;
            x=i;
            y=j;
        }
        @Override
        public int compareTo(Container c) {
            return this.height-c.height;
        }
    }
    PriorityQueue<Container> pq;
    boolean[][] visited;
    int num_visited=0;
    int max_water=0;
    int[][] dir=new int[][] {{0,1}, {0, -1}, {1, 0}, {-1, 0}};
    
    public int trapRainWater(int[][] heightMap) {
        int m=heightMap.length;
        if (m==0)
            return 0;
        int n=heightMap[0].length;
        if (n==0)
            return 0;
        
        visited=new boolean[m][n];
        pq=new PriorityQueue<Container>();
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++)
                if (i==0 || i==m-1 || j==0 || j==n-1) {
                    num_visited++;
                    visited[i][j]=true;
                    pq.offer(new Container(heightMap[i][j], i, j));
                }
        }
        
        while (num_visited!=m*n && !pq.isEmpty()) {
            Container min = pq.poll();
            traversal(min, heightMap);
        }
        return max_water;
    }
    public void traversal(Container c, int[][] heightMap) {
        for (int k=0; k<4; k++) {
            fillContainer(c, c.x+dir[k][0], c.y+dir[k][1], heightMap);
        }
    }
    
    public void fillContainer(Container c, int i, int j, int[][] heightMap) {
        if (i<0 || j<0 || i>=heightMap.length || j>=heightMap[0].length || visited[i][j])
            return;
        
        visited[i][j]=true;
        num_visited++;
        if (heightMap[i][j]>=c.height) {
            pq.offer(new Container(heightMap[i][j], i, j));
        } else {
            max_water+=c.height-heightMap[i][j];
            for (int k=0; k<4; k++) {
                fillContainer(c, i+dir[k][0], j+dir[k][1], heightMap);
            }
        }
    }
}
