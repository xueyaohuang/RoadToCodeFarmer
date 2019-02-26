class Solution {
    public boolean isNumber(String s) {
        s = s.trim();
        boolean hasNumber = false;
        boolean hasE = false;
        boolean hasPoint = false;
        boolean numberAfterE = true;
        
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                hasNumber = true;
                numberAfterE = true;
            } else if (s.charAt(i) == '.') {
                if (hasE || hasPoint) {
                    return false;
                }
                hasPoint = true;
            } else if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                if (i != 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') {
                    return false;
                }
            } else if (s.charAt(i) == 'e' || s.charAt(i) == 'E') {
                if (hasE || !hasNumber) {
                    return false;
                }
                hasE = true;
                numberAfterE = false;
            } else {
                return false;
            }
        }
        return hasNumber && numberAfterE;
    }
}

// Deterministic finite automaton
/*
class Solution:
    def isNumber(self, s: str) -> bool:
        state = [{}, 
                {'blank': 1, 'sign': 2, 'digit': 3, '.': 4}, 
                {'digit': 3, '.': 4},
                {'digit': 3, '.': 5, 'e': 6, 'blank': 9},
                {'digit': 5},
                {'digit': 5, 'e': 6, 'blank': 9},
                {'sign': 7, 'digit': 8},
                {'digit': 8},
                {'digit': 8, 'blank': 9},
                {'blank': 9}]
        currentState = 1
        for c in s:
            if c >= '0' and c <= '9':
                c = 'digit'
            if c == ' ':
                c = 'blank'
            if c == '+' or c == '-':
                c = 'sign'
            if c not in state[currentState].keys():
                return False
            currentState = state[currentState][c]
        if currentState != 3 and currentState != 5 and currentState != 8 and currentState != 9:
            return False
        return True
*/
