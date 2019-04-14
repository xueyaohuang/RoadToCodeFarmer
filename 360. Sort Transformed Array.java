public class Solution {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int n = nums.length;
        int[] sorted = new int[n];
        int i = 0, j = n - 1;
        int index = a >= 0 ? n - 1 : 0;
        while (i <= j) {
            int fi = quad(nums[i], a, b, c);
            int fj = quad(nums[j], a, b, c);
            if (a >= 0) {
                if (fi >= fj) {
                    sorted[index--] = fi;
                    i++;
                } else {
                    sorted[index--] = fj;
                    j--;
                }
            } else {
                if (fi >= fj) {
                    sorted[index++] = fj;
                    j--;
                } else {
                    sorted[index++] = fi;
                    i++;
                }
            }
        }
        return sorted;
    }
    
    private int quad(int x, int a, int b, int c) {
        return a * x * x + b * x + c;
    }
}

class Solution {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int len = nums.length;
        // case 1, a== 0
        if (a == 0) {
            if (b >= 0) {
                return ascending(nums, a, b, c);
            } else {
                return decending(nums, a, b, c);
            }
        }
        
        double symmetryAxis = -b / 2.0 / a;
        
        // case 2, a > 0
        if (a > 0) {
            if (nums[0] >= symmetryAxis) {
                return ascending(nums, a, b, c);
            } else if (nums[len - 1] <= symmetryAxis) {
                return decending(nums, a, b, c);
            } else {
                return nonMonotonic(nums, a, b, c);
            }
        }
        
        // case 3, a < 0
        if (nums[0] >= symmetryAxis) {
            return decending(nums, a, b, c);
        } else if (nums[len - 1] <= symmetryAxis) {
            return ascending(nums, a, b, c);
        }
        return nonMonotonic(nums, a, b, c);
    }
    
    private int[] ascending(int[] nums, int a, int b, int c) {
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = fx(nums[i], a, b, c);
        }
        return res;
    }
    
    private int[] decending(int[] nums, int a, int b, int c) {
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = fx(nums[nums.length - 1 - i], a, b, c);
        }
        return res;
    }
    
    private int[] nonMonotonic(int[] nums, int a, int b, int c) {
        int len = nums.length;
        int i = 0;
        int j = len - 1;
        int[] res = new int[len];
        if (a < 0) {
            int k = 0;
            while (k < len) {
                int fi = fx(nums[i], a, b, c);
                int fj = fx(nums[j], a, b, c);
                if (fi <= fj) {
                    res[k++] = fi;
                    i++;
                } else {
                    res[k++] = fj;
                    j--;
                }
            }
        } else {
            int k = len - 1;
            while (k >= 0) {
                int fi = fx(nums[i], a, b, c);
                int fj = fx(nums[j], a, b, c);
                if (fi <= fj) {
                    res[k--] = fj;
                    j--;
                } else {
                    res[k--] = fi;
                    i++;
                }
            }
        }
        return res;
    }
    
    private int fx(int x, int a, int b, int c) {
        return a * x * x + b * x + c;
    }
}
