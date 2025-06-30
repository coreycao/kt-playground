package me.exercise.stack

fun main() {
    // true
    val brackets = "((({{{}}})))"
    println(validBrackets(brackets))

    // false
    val brackets2 = "((({{{}}}"
    println(validBrackets(brackets2))

    // false
    println(validBrackets("123"))

    // false
    println(validBrackets(""))
}

/**
 * 利用栈实现括号匹配
 */
fun validBrackets(brackets: String): Boolean {
    if (brackets.isEmpty()) return false
    val stack: CustomStack<Char> = LinkedStack()
    for (ch in brackets) {
        if (ch == '{' || ch == '[' || ch == '(') {
            stack.push(ch)
        } else if (ch == ')') {
            if (stack.isEmpty() || stack.pop() != '(') {
                return false
            }
        } else if (ch == ']') {
            if (stack.isEmpty() || stack.pop() != '[') {
                return false
            }
        } else if (ch == '}') {
            if (stack.isEmpty() || stack.pop() != '{') {
                return false
            }
        } else {
            return false
        }
    }
    return stack.isEmpty()
}