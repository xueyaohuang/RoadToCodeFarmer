func twoSum(nums []int, target int) []int {
    num_idx := make(map[int]int)
    for i, num := range nums {
        if idx, contains := num_idx[target - num]; contains {
            return []int{i, idx}
        }
        num_idx[num] = i
    }
    return nil
}
