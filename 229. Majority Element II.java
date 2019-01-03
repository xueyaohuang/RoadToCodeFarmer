// use extra space
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int n = nums.length / 3;
        for (int key : map.keySet()) {
            if (map.get(key) > n) {
                res.add(key);
            }
        }
        return res;
    }
}

/*
思路：摩尔投票升级版，超过n/3的数最多只能有两个；
先选出两个候选人A,B,遍历数组，如果投A（等于A），则A的票数++;如果投B，B的票数++；
如果A,B都不投（即与A，B都不相等）,那么检查此时是否AB中候选人的票数是否为0，如果为0,则成为新的候选人；
如果A,B两个人的票数都不为0，那么A,B两个候选人的票数均--；
遍历结束后选出两个候选人，但是这两个候选人是否满足>n/3，还需要再遍历一遍数组，找出两个候选人的具体票数
 */
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<Integer>();
        }
        List<Integer> list = new ArrayList<Integer>();
        int num1 = 0, num2 = 1;
        int count1 = 0, count2 = 0;
        for (int num : nums) {
            if (num == num1) {
                count1++;
            } else if (num == num2) {
                count2++;
            } else if (count1 == 0) {
                num1 = num;
                count1 = 1;
            } else if (count2 == 0) {
                num2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (num == num1) {
                count1++;
            }
            if (num == num2) {
                count2++;
            }
        }
        if (count1 > nums.length / 3) {
            list.add(num1);
        }
        if (count2 > nums.length / 3) {
            list.add(num2);
        }
        return list;
    }
}
