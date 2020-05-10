class Solution {
    public int findJudge(int N, int[][] trust) {
        if (N == 1) {
            return 1;
        }
        Set<Integer> nonJudge = new HashSet<>();
        Set<Integer> candidate = new HashSet<>();
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> numTrust = new HashMap<>();
        for (int[] t : trust) {
            nonJudge.add(t[0]);
            numTrust.put(t[1], numTrust.getOrDefault(t[1], 0) + 1);
            if (numTrust.get(t[1]) == N - 1) {
                candidate.add(t[1]);
            }
        }
        for (int i : candidate) {
            if (!nonJudge.contains(i)) {
                res.add(i);
            }
        }
        return res.size() == 1 ? res.get(0) : -1;
    }
}

class Solution {
    public int findJudge(int N, int[][] trust) {
        if (N == 1) {
            return 1;
        }
        int[] truster = new int[N + 1];
        int[] trustee = new int[N + 1];
        int candidate = -1;
        for (int i = 0; i < trust.length; i++) {
            truster[trust[i][0]]++;
            trustee[trust[i][1]]++;
        }
        for (int i = 1; i <= N; i++) {
            if (truster[i] == 0 && trustee[i] == N - 1) {
                if (candidate != -1 && candidate != i) {
                    return -1;
                }
                candidate = i;
            }
        }
        return candidate;
    }
}
