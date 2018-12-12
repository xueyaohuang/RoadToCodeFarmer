// O(nlgn)
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // 直接sort int[] array 不能用lambda或者comparator，要转换成Integer[]
        Integer[] temp = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            temp[i] = arr[i];
        }
        Arrays.sort(temp, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                if (Math.abs(a - x) == Math.abs(b - x)) {
                    return a - b;
                } else {
                    return Math.abs(a - x) - Math.abs(b - x);
                }
            }
        });
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            res.add(temp[i]);
        }
        Collections.sort(res);
        return res;
    }
}

// O(k) + O(lgn)
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int len = arr.length;
        int idx = Arrays.binarySearch(arr, x);
        List<Integer> res = new ArrayList<>();
        int i = 0, j = 0;
        
        if (idx < 0) {
            idx = -idx - 1;
            i = idx;
            j = idx - 1;
        } else {
            i = idx + 1;
            j = idx;
        }
        
        while (res.size() < k) {
            if ((j >= 0 && i < len && arr[i] - x >= x - arr[j]) || i >= len) {
                res.add(0, arr[j]);
                j--;
            } else if ((j >= 0 && i < len && arr[i] - x <= x - arr[j]) || j < 0) {
                res.add(arr[i]);
                i++;
            }
        }
        return res;
    }
}

// best O(k) + O(lgn)
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int len = arr.length;
        int start = 0, end = len - k;
        List<Integer> res = new ArrayList<>();
        
        while (start < end) {
            int mid = (start + end) / 2;
            if (x - arr[mid] > arr[mid + k] - x) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        for (int i = start; i < start + k; i++) {
            res.add(arr[i]);
        }
        return res;
    }
}
