fun main(args: Array<String>) {
    val consoleManager = ConsoleManager(Calculator())
    consoleManager.greeting()
    var quit = false
    while (!quit){
        quit = !consoleManager.readConsole()
    }
}