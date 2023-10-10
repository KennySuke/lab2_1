import javax.swing.text.StyledEditorKit.BoldAction

class ConsoleManager (val calculator: Calculator) {

    val operations = listOf(
        '+',
        '-',
        '*',
        '/',
    )

    fun greeting(){
        println("Калькулятор приветствует тебя!\n" +
                "Напиши выражение, которое тебе необходимо вычислить.\n" +
                "Доступные функции:\n" +
                "+      Сложение\n" +
                "-      Вычитание\n" +
                "*      Умножение\n" +
                "/      Деление\n" +
                "Используй именно указанные выше символы!\n" +
                "Пример: 1 + 2\n\n" +
                "Доступные команды:\n" +
                "/quit      выход\n" +
                "/help      справка")
    }

    fun readConsole(): Boolean{
        println("Введите операцию или команду")
        val input = readln()
        return when (input){
            "/quit" -> {
                println("До новых встреч!")
                false
            }
            "/help" -> {
                greeting()
                true
            }
            else -> {
                operationFromConsole(input)
                true
            }
        }
    }

    fun operationFromConsole(input: String){
        val operation = _extractOperation(input)
        if (operation != null){
            val operands = _extractOPerands(input, operation)
            val result : Double?
            if (operands != null) {
                result = calculator.operation(operands, operation)
                if (result != null && result.isInfinite()){
                    println("Слишком большое значение.")
                    return
                }
                println("${_cutDouble(operands.first)} " +
                        "$operation " +
                        "${_cutDouble(operands.second)} = " +
                        _cutDouble(result)
                )
                return
            }
        }
        println("Некорректный ввод. Необходимо ввести два операнда и один оператор между ними.")
    }

    fun _cutDouble(double: Double?): String{
        return double.toString().removeSuffix(".0")
    }

    fun _validateDouble(string: String): Boolean{
        return string.toDoubleOrNull() != null
    }

    fun _extractOPerands(string: String, operation: Char): Pair<Double, Double>?{
        val list = string.split(operation).toMutableList()
        list[0] = list[0].trim(' ')
        list[1] = list[1].trim(' ')
        if (_validateDouble(list[0]) && _validateDouble(list[1]))
            return Pair(list[0].toDouble(), list[1].toDouble())
        return null
    }

    fun _extractOperation(string: String): Char?{
        operations.forEach{  operation ->
            if (string.count { it == operation} == 1) {
                return operation
            }
        }
        return null
    }
}