package encode;

import java.util.ListResourceBundle;

public class UsageResourceBundle_ru extends ListResourceBundle {
    static final Object[][] contents = new String[][]{
        { "String_1", "Синтаксис: encode [опции] [--] [файл1 файл2...]" },
        { "String_2", "           encode --help | -h" },
        { "String_3", "Переводит текстовые файлы из одной кодировки в другую." },
        { "String_4", "Используются следующие опции:" },
        { "String_5", " -s           - перевести файлы в самих себя," },
        { "String_6", " -o <outfile> - результат перевода поместить в outfile," },
        { "String_7", " -c(+|-|=)    - изменить формат окончания строк (\\r, \\n, как есть)," },
        { "String_9", " -f <charset> - исходный текст представлен в кодировке charset,\n" },
        { "String_10", " -o <charset> - перевести текст в кодировку charset," },
        { "String_11", " --           - означает, что дальше следуют только имена файлов," },
        { "String_12", " --help, -h   - вывести это сообщение" },
        { "String_13", "По умолчанию используются следующие значения опций:" },
        { "String_14", " encode -f {0} -t {1} -o - -c= -- -" },
        { "ILLEGAL_ARGUMENT", "Неправильный аргумент {0}" },
        { "EXTRA_PARAMETERS", "Слишком много параметров" },
        { "ARGUMENT_REQUIRED", "Не хватает обязательного аргумента для опции {0}" },
        { "PARSE_ARGUMENTS_ERROR", "Не могу разобрать аргументы командной строки" },
        { "FILE_NAMES_REQUIRED", "Для опции -s требуется указать имена редактируемых файлов" }
    };

    public Object[][] getContents() {
        return contents;
    }
}
