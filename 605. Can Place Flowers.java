// class Solution {
//     public boolean canPlaceFlowers(int[] flowerbed, int n) {
//         if (n == 0) {
//             return true;
//         }
//         if (flowerbed.length == 1 && n <= 1) {
//             return flowerbed[0] == 0;
//         }
//         int count = 0;
//         for (int i = 0; i < flowerbed.length; i++) {
//             if (i > 0 && i < flowerbed.length - 1 && flowerbed[i] == 0 && flowerbed[i + 1] == 0 && flowerbed[i - 1] == 0) {
//                 flowerbed[i] = 1;
//                 count++;
//                 continue;
//             }
//             if (i == 0 && flowerbed[i] == 0 && flowerbed[i + 1] == 0) {
//                 flowerbed[i] = 1;
//                 count++;
//                 continue;
//             }
//             if (i == flowerbed.length - 1 && flowerbed[i] == 0 && flowerbed[i - 1] == 0) {
//                 flowerbed[i] = 1;
//                 count++;
//             }
//             if (count == n) {
//                 break;
//             }
//         }
//         return count >= n;
//     }
// }

// greedy
class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            if (canPlaceHere(flowerbed, i)) {
                count++;
                flowerbed[i] = 1;
            }
            if (count >= n) {
                return true;
            }
        }
        return false;
    }
    
    private boolean canPlaceHere(int[] flowerbed, int i) {
        if (flowerbed[i] == 1) {
            return false;
        }
        // 左边有花
        if (i > 0 && flowerbed[i - 1] == 1) {
            return false;
        }
        // 右边有花
        if (i < flowerbed.length - 1 && flowerbed[i + 1] == 1) {
            return false;
        }
        return true;
    }
}

class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0) && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
                flowerbed[i] = 1;
                count++;
            }
            if (count == n) {
                return true;
            }
        }
        return count >= n;
    }
}
