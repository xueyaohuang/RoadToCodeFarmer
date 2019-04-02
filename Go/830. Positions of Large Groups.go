func largeGroupPositions(S string) [][]int {
    if len(S) < 3 {
        return [][]int{}
    }
    res := [][]int{}
    var end int
    for i := 0; i < len(S); i++ {
        end = i
        for end < len(S) - 1 && S[end] == S[end + 1] {
            end++
        }
        if (end - i + 1 >= 3) {
            res = append(res, []int{i, end})
        }
        i = end
    }
    return res
}
