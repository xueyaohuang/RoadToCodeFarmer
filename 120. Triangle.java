class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null) {
            return 0;
        }
        List<Integer> list = new ArrayList<>();
        list.add(triangle.get(0).get(0));
        for (int i  = 1; i < triangle.size(); i++) {
            list.add(0, list.get(0) + triangle.get(i).get(0));
            for (int j = 1; j < i; j++) {
                list.set(j, triangle.get(i).get(j) + Math.min(list.get(j), list.get(j + 1)));
            }
            list.set(i, list.get(i) + triangle.get(i).get(i));
        }
        int res = Integer.MAX_VALUE;
        for (int i : list) {
            res = Math.min(res, i);
        }
        return res;
    }
}

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int len = triangle.size();
        int[] res = new int[len + 1];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                res[j] = Math.min(res[j], res[j + 1]) + triangle.get(i).get(j);
            }
        }
        return res[0];
    }
}

/*
print the path

public static List<Integer> minimumTotal(List<List<Integer>> triangle) {

        List<Integer> list = new ArrayList<>();
        list.add(triangle.get(0).get(0));
        int height = triangle.size();
        int[][] path = new int[height][height];
        List<Integer> ans = new ArrayList<>();
        
        for (int i  = 1; i < height; i++) {
            list.add(0, list.get(0) + triangle.get(i).get(0));
            path[i][0] = 0;
            for (int j = 1; j < i; j++) {
                if (list.get(j) <= list.get(j + 1)) {
                    path[i][j] = j - 1;
                    list.set(j, triangle.get(i).get(j) + list.get(j));
                } else {
                    path[i][j] = j;
                    list.set(j, triangle.get(i).get(j) + list.get(j + 1));
                }
            }
            list.set(i, list.get(i) + triangle.get(i).get(i));
            path[i][i] = i - 1;
        }
        int res = Integer.MAX_VALUE;
        int last = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) <= res) {
                res = list.get(i);
                last = i;
            }
        }
        
        ans.add(last);
        int level = height - 1;
        while (level > 0) {
            ans.add(0, path[level][last]);
            last = path[level][last];
            level--;
        }
        return ans;
    }
*/
