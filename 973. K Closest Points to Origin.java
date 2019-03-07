// O(nlgn)
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        if (points == null || points.length == 0) {
            return new int[0][0];
        }
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return (a[0] * a[0] + a[1] * a[1]) - (b[0] * b[0] + b[1] * b[1]);
            }
        });
        return Arrays.copyOfRange(points, 0, K);
    }
}

// O(n+klgk)
// the Time complexity for Building a Binary Heap is O(n).
// https://www.geeksforgeeks.org/time-complexity-of-building-a-heap/
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        if (points == null || points.length == 0) {
            return new int[0][0];
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return (a[0] * a[0] + a[1] * a[1]) - (b[0] * b[0] + b[1] * b[1]);
            }
        });
        for (int[] point : points) {
            pq.offer(point);
        }
        int[][] res = new int[K][2];
        for (int i = 0; i < K; i++) {
            res[i] = pq.poll();
        }
        return res;
    }
}
/*
Thank you for your comments.
The running time of heapify function is indeed O(N).
But your building heap method is adding points one by one, it is not the standard heapify function. So your method to build a heap is not O(N) but O(NlogN). Because you add the element one by one, the running time is O(log1+log2+log3+...logN)=O(logN!); O(N) < O(logN!) <O(NlogN).
The standard heapify function is not in your way. Assuming we use an array to implement the heap, we will copy all elements to the array, then we do adjustment bottom-up for every subtrees. So it cost O(N).
In your method, we add element one by one to the tail of the array and do adjustment bottom-up for the subtrees on a certain path not for all subtress. So ecah time you add one element to your heap, it takes us O(logN)) to do adjustment. And the total time compelxity would be O(log1+log2+log3+...logN)=O(logN!).
The heapify function has been implemented by Java, you can use it by calling the constructor of PriorityQueue and passing your elements as the input parameter.
*/

// O(nlgk)
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        if (points == null || points.length == 0) {
            return new int[0][0];
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1]);
            }
        });
        for (int[] point : points) {
            pq.offer(point);
            if (pq.size() > K) {
                pq.poll();
            }
        }
        int[][] res = new int[K][2];
        for (int i = 0; i < K; i++) {
            res[i] = pq.poll();
        }
        return res;
    }
}

// O(n)
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        if (points == null || points.length == 0) {
            return new int[0][0];
        }
        int len = points.length;
        int[] distances = new int[len];
        int[][] res = new int[K][2];
        int i = 0;
        for (int[] point : points) {
            distances[i++] = point[0] * point[0] + point[1] * point[1];
        }
        // 注意这里是K - 1，从0开始
        int kth = randomQuickSelect(distances, 0, len - 1, K - 1);
        i = 0;
        for (int[] point : points) {
            if (point[0] * point[0] + point[1] * point[1] <= kth) {
                res[i++] = point;
            }
        }
        return res;
    }
    
    private int randomQuickSelect(int[] nums, int start, int end, int k) {
        Random rand = new Random();
        int pivotIdx = rand.nextInt(end - start + 1) + start;
        int pivot = nums[pivotIdx];
        swap(nums, pivotIdx, end);
        int left = start;
        
        for (int i = start; i < end; i++) {
            if (nums[i] <= pivot) {
                swap(nums, left, i);
                left++;
            }
        }
        swap(nums, left, end);
        if (left == k) {
            return nums[left];
        } else if (left > k) {
            return randomQuickSelect(nums, start, left - 1, k);
        } else {
            return randomQuickSelect(nums, left + 1, end, k);
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

