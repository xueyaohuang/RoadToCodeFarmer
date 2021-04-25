// 1. 由于只有可能有唯一解，也可能无解。若有唯一解，那么我最后一次失败的station后面的station就是唯一解。
// 2. 所以更新tank为tank += gas[i] - cost[i]，每次在stationi检查到tank<0, station i+ 1就可能是那个唯一解，所以更新start到i + 1。
// 如果总的gas比总的cost大，一定有解。反之一定无解。检查 rest + tank 是否比0大，大于零一定有解，就是最后更新的那个start。小于0一定无解。

//If car starts at A and can not reach B. Any station between A and B
// can not reach B.(B is the first station that A can not reach.)
// If the total number of gas is bigger than the total number of cost. There must be a solution.

class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int tank = 0;
        int total = 0;
        int start = 0;
        for (int i = 0; i < gas.length; i++) {
            tank += gas[i] - cost[i];
            if (tank < 0) {
                start = i + 1;
                total += tank;
                tank = 0;
            }
        }
        return (tank + total) >= 0 ? start : -1;
    }
}
