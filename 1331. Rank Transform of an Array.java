class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int[] sorted = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sorted);
        Map<Integer, Integer> order = new HashMap<>();
        int rank = 1;
        for (int i = 0; i < sorted.length; i++) {
            if (!order.containsKey(sorted[i])) {
                order.put(sorted[i], rank++);
            }
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = order.get(arr[i]);
        }
        return res;
    }
}


class Solution {
    public int[] arrayRankTransform(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int a : arr) {
            set.add(a);
        }
        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        Map<Integer, Integer> order = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            order.put(list.get(i), i + 1);
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = order.get(arr[i]);
        }
        return res;
    }
}
