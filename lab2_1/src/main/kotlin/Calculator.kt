class Calculator {

    fun operation(
        operands: Pair<Double, Double>,
        operation: Char,
    ): Double? {
        return when (operation){
            '+' -> operands.first+operands.second
            '-' -> operands.first-operands.second
            '*' -> operands.first*operands.second
            '/' -> operands.first/operands.second
            else -> null
        }
    }

}