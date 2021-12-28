class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int a : asteroids) {
            if (a > 0) {
                stack.push(a);
            } else {
                while (!stack.isEmpty() && stack.peek() > 0 && Math.abs(a) > stack.peek()) {
                    stack.pop();
                }
                // while loop 后有4种情况
                // 1. stack.isEmpty()
                // 2. stack.peek() < 0
                // 3. Math.abs(a) == stack.peek()
                // 4. Math.abs(a) < stack.peek()
                if (stack.isEmpty() || stack.peek() < 0) { // case 1 and 2
                    stack.push(a);
                } else if (a +  stack.peek() == 0) { // case 3
                    stack.pop();
                }
                // case 4 do nothing
            }
        }
        int[] res = new int[stack.size()];
        int idx = res.length - 1;
        while (idx >= 0) {
            res[idx] = stack.pop();
            idx--;
        }
        return res;
    }
}
// remove the last element of the array list is O(1) since that can be performed with zero copying
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        List<Integer> list = new ArrayList<>();
        for (int a : asteroids) {
            if (a > 0) {
                list.add(a);
            } else {
                while (list.size() > 0 && list.get(list.size() - 1) > 0 && Math.abs(a) > list.get(list.size() - 1)) {
                    list.remove(list.size() - 1);
                }
                if (list.size() == 0 || list.get(list.size() - 1) < 0) {
                    list.add(a);
                } else if (list.get(list.size() - 1) + a == 0) {
                    list.remove(list.size() - 1);
                }
            }
        }
        int[] res = new int[list.size()];
        int idx = 0;
        while (idx < res.length) {
            res[idx] = list.get(idx);
            idx++;
        }
        return res;
    }
}

class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        List<Integer> list = new ArrayList<>();
        for (int a : asteroids) {
            list.add(a);
            while (list.size() > 1 && list.get(list.size() - 1) < 0 && list.get(list.size() - 2) > 0) {
                int last = list.get(list.size() - 1);
                int secondLast = list.get(list.size() - 2);
                if (Math.abs(last) > Math.abs(secondLast)) {
                    list.remove(list.size() - 1);
                    list.remove(list.size() - 1);
                    list.add(last);
                } else if (Math.abs(last) < Math.abs(secondLast)) {
                    list.remove(list.size() - 1);
                } else {
                    list.remove(list.size() - 1);
                    list.remove(list.size() - 1);
                }
            }
        }
        int[] res = new int[list.size()];
        int idx = 0;
        while (idx < res.length) {
            res[idx] = list.get(idx);
            idx++;
        }
        return res;
    }
}
