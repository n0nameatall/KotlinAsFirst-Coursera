@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
/*fun main(args: Array<String>) {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}*/


/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
fun dateStrToDigit(str: String): String {
    val parts = str.split(" ")

    if (parts.size != 3) {
        return ""
    }

    var isDateProper = true
    val day = parts[0].toInt()
    val year = parts[2].toInt()
    val month: Int = when (parts[1]) {
        "января" -> 1
        "февраля" -> 2
        "марта" -> 3
        "апреля" -> 4
        "мая" -> 5
        "июня" -> 6
        "июля" -> 7
        "августа" -> 8
        "сентября" -> 9
        "октября" -> 10
        "ноября" -> 11
        "декабря" -> 12
        else -> -1
    }

    when (month) {
        2 -> {
            if (year % 400 == 0 || year % 4 == 0 && year % 100 != 0) {
                if (day < 1 || day > 29) {
                    isDateProper = false
                }
            } else {
                if (day < 1 || day > 28) {
                    isDateProper = false
                }
            }
        }
        4, 6, 9, 11 -> if (day < 1 || day > 30) isDateProper = false
        1, 3, 5, 7, 8, 10, 12 -> if (day < 1 || day > 31) isDateProper = false
        else -> isDateProper = false
    }
    if (year < 1 || year > 9999) isDateProper = false
    return if (isDateProper) {
        String.format("%02d.%02d.%d", day, month, year)
    } else {
        ""
    }
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String {
    val parts = digital.split(".")
    if (parts.size != 3) return ""

    var isDateProper = true
    val day: Int
    val year: Int
    try {
        day = parts[0].toInt()
        year = parts[2].toInt()
    } catch (e: Exception) {
        return ""
    }

    val month = when (parts[1]) {
        "01" -> "января"
        "02" -> "февраля"
        "03" -> "марта"
        "04" -> "апреля"
        "05" -> "мая"
        "06" -> "июня"
        "07" -> "июля"
        "08" -> "августа"
        "09" -> "сентября"
        "10" -> "октября"
        "11" -> "ноября"
        "12" -> "декабря"
        else -> "-1"
    }
    when (month) {
        "февраля" -> {
            if (year % 400 == 0 || year % 4 == 0 && year % 100 != 0) {
                if (day < 1 || day > 29) {
                    isDateProper = false
                }
            } else {
                if (day < 1 || day > 28) {
                    isDateProper = false
                }
            }
        }
        "апреля", "июня", "сентября", "ноября" -> if (day < 1 || day > 30) isDateProper = false
        "января", "марта", "мая", "июля", "августа", "октября", "декабря" -> if (day < 1 || day > 31) isDateProper = false
        else -> isDateProper = false
    }
    if (year < 1 || year > 9999) isDateProper = false
    return if (isDateProper) {
        String.format("%d %s %d", day, month, year)
    } else {
        ""
    }
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -89 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку
 */
fun flattenPhoneNumber(phone: String): String {
    var str = phone
    var isStringValid = true

    str = str.replace(Regex("[-()\\s]")) {
        when (it.value) {
            "(" -> ""
            ")" -> ""
            "-" -> ""
            " " -> ""
            else -> it.value
        }
    }
    if (str.contains(Regex("[^\\d\\+]"))) isStringValid = false
    //if (str.contains(Regex("\\+")) && !str.contains(Regex("^\\+"))) isStringValid = false
    if (str.contains(Regex("\\d\\++"))) isStringValid = false
    if (str.contains(Regex("^\\+{2,}"))) isStringValid = false
    return if (isStringValid) str else ""
}

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    var isStringValid = true
    if (jumps.contains(Regex("[^\\d\\-\\%\\s]"))) return -1
    var jumpsList = jumps.split(Regex(" "))
//    var resultsList = mutableListOf<Int>()
    var result: Int = -1

    for (jump in jumpsList ) {
        try {
            if (jump.toInt() > result) {
                result = jump.toInt()
            }
        } catch (e: Exception) {
        }
    }
//    println(isStringValid)
//    println(jumpsList)
//    println(result)

    return result
}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки вернуть -1.
 */
fun bestHighJump(jumps: String): Int = TODO()

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    if (expression.contains(Regex("[^\\d\\-\\+\\s]|" +
                                            "\\d\\s\\d|" +
                                            "\\D\\s\\D|" +
                                            "^\\D"))) throw IllegalArgumentException()
    var numbers = expression.split(" ")
    var result = numbers[0].toInt()
    for (i in 1 until numbers.size - 1 step 2) {
        if (numbers[i].contains("+")) {
            result += numbers[i + 1].toInt()
        } else {
            result -= numbers[i + 1].toInt()
        }
    }
    return result
}

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    var list = str.split(" ")
    var hasDuplicate = false
    var index = 0

    for (i in 0 until list.size - 1) {
        index += list[i].length + 1
        if (list[i].toLowerCase() == list[i + 1].toLowerCase()) {
            index = index - list[i].length - 1
            hasDuplicate = true
            break
        }
    }
    return if (hasDuplicate) index else -1
}

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше либо равны нуля.
 */
fun mostExpensive(description: String): String {
    if (!description.contains(Regex("\\D\\s\\d"))) return ""
    val list = description.split("; ")
    val map = mutableMapOf<String, Double>()
    for (entry in list) {
        val listFromPair = entry.split(" ")
        val pair: Pair<String, Double> = listFromPair[0] to listFromPair[1].toDouble()
        map[pair.first] = pair.second
    }
    var highestPrice = 0.0
    var highestName = ""
    for ((key, value) in map) {
        if (value > highestPrice) {
            highestPrice = value
            highestName = key
        }
    }
    return highestName
}
fun main(args: Array<String>) {
    println(mostExpensive("Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9"))
}
/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int = TODO()

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> = TODO()
