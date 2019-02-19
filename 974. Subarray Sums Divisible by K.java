class Solution {
    public int subarraysDivByK(int[] A, int K) {
        if (A == null || A.length == 0) {
            return 0;
        }
        Map<Integer, Integer> reminder = new HashMap<>();
        reminder.put(0, 1);
        int count = 0;
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            int rmd = (sum % K + K) % K;
            if (reminder.containsKey(rmd)) {
                count += reminder.get(rmd);
            }
            reminder.put(rmd, reminder.getOrDefault(rmd, 0) + 1);
        }
        return count;
    }
}
