class Solution {
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        List<Integer> res = new ArrayList<>();
        int i = 0, j = 0, k = 0;
        while (i < arr1.length && j < arr2.length && k < arr3.length) {
            if (arr1[i] == arr2[j] && arr2[j] == arr3[k]) {
                res.add(arr1[i]);
                i++;
                j++;
                k++;
            } else if (arr1[i] < arr2[j]) {
                i++;
            } else if (arr2[j] < arr3[k]) {
                j++;
            } else {
                k++;
            }
        }
        return res;
    }
}

// divide and conquer
class Solution {
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        List<Integer> res = new ArrayList<>();
        int[] temp1 = twoArraysIntersection(arr1, arr2);
        int[] temp2 = twoArraysIntersection(temp1, arr3);
        for (int i : temp2) {
            res.add(i);
        }
        return res;
    }
    
    private int[] twoArraysIntersection(int[] arr1, int[] arr2) {
        List<Integer> temp = new ArrayList<>();
        int i = 0, j = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] == arr2[j]) {
                temp.add(arr1[i]);
                i++;
                j++;
            } else if (arr1[i] > arr2[j]) {
                j++;
            } else {
                i++;
            }
        }
        int[] res = new int[temp.size()];
        for (int k = 0; k < res.length; k++) {
            res[k] = temp.get(k);
        }
        return res;
    }
}
