// no return inside while loop
class Solution {
    public int search(ArrayReader reader, int target) {
        int start = 0;
        int end = 1;
        while (reader.get(end) < target) {
            start = end;
            end *= 2;
        }
        return binarySearch(reader, target, start, end);
    }
    
    private int binarySearch(ArrayReader reader, int target, int left, int right) {
        int start = left;
        int end = right;
        while (start < end) {
            int mid = (start + end) / 2;
            if (reader.get(mid) < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return reader.get(start) == target ? start : -1;
    }
}

// has return inside while loop
class Solution {
    public int search(ArrayReader reader, int target) {
        int start = 0;
        int end = 1;
        while (reader.get(end) < target) {
            start = end;
            end *= 2;
        }
        return binarySearch(reader, target, start, end);
    }
    
    private int binarySearch(ArrayReader reader, int target, int left, int right) {
        int start = left;
        int end = right;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (reader.get(mid) == target) {
                return mid;
            } else if (reader.get(mid) < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }
}
