class Solution {
    public int[] anagramMappings(int[] A, int[] B) {
        Map<Integer, Stack<Integer>> map = new HashMap<>();
        int[] res = new int[A.length];
        for (int i = 0; i < B.length; i++) {
            map.putIfAbsent(B[i], new Stack<Integer>());
            map.get(B[i]).push(i);
        }
        for (int i = 0; i < A.length; i++) {
            res[i] = map.get(A[i]).pop();
        }
        return res;
    }
}
