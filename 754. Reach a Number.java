// 1. target是正还是负无所谓，先把target取绝对值
// 2. 最好情况是1+2+3+...+k刚好等于target，如果是这样直接return k
// 3. 否则至少需要1到k+1(甚至k+2). 现在需要1,2,3...k,k+1,..这些数中有的为正，有的为负。假设正数的和是x，负数的和是y，则有x+y=sum, x-y=target
// 4. 上面的方程要有解，需要sum+target是偶数。并且如果是偶数，必定有解。y=(sum-target)/2, 1到k+1，k+2是连续的，一定可以找出一个或几个数的和是(sum-target)/2。
class Solution {
    public int reachNumber(int target) {
        int k = 0;
        target = Math.abs(target);
        int n = target;
        while (n > 0) {
            k++;
            n -= k;
        }
        if (n == 0) {
            return k;
        }
        int sum = (1 + k) * k / 2;
        while ((sum + target) % 2 == 1) {
            k++;
            sum += k;
        }
        return k;
    }
}

// 1,2,3 ... n

// target


// 1,2,3...k
// 1,2,3...k,k+1
    
//     15
//     21
//     18
    
//     1,2,3,4,5,6,7
    
//     x+y = 28
//     x- y = 18
    
//     23
