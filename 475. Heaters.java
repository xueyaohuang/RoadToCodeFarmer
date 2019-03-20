// max(O(nlogn), O(mlogn)) - m is the length of houses, n is the length of heaters.
class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        int maxRadius = 0;
        int len = heaters.length;
        Arrays.sort(heaters);
        for (int house : houses) {
            int idx = Arrays.binarySearch(heaters, house);
            if (idx >= 0) {
                continue;
            }
            idx = -idx - 1;
            if (idx == 0) {
                maxRadius = Math.max(maxRadius, heaters[0] - house);
            } else if (idx == len) {
                maxRadius = Math.max(maxRadius, house - heaters[len - 1]);
            } else {
                maxRadius = Math.max(maxRadius, Math.min(house - heaters[idx - 1], heaters[idx] - house));
            }
        }
        return maxRadius;
    }
}

// best
class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int i = 0, res = 0;
        for (int house : houses) {
            while (i < heaters.length - 1 && heaters[i] + heaters[i + 1] <= house * 2) {
                i++;
            }
            res = Math.max(res, Math.abs(heaters[i] - house));
        }
        return res;
    }
}
