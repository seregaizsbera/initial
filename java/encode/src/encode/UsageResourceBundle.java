package encode;

import java.util.ListResourceBundle;

public class UsageResourceBundle extends ListResourceBundle {
    static final Object[][] contents = new String[][]{
        { "String_1", "Usage: encode [options] [--] [file1 file2...]" },
        { "String_2", "   encode --help | -h" },
        { "String_3", "Converts text files from one charset to another." },
        { "String_4", "Options:" },
        { "String_5", " -s           - convert every file into itself," },
        { "String_6", " -o <outfile> - redirect the result output to outfile," },
        { "String_7", " -c(+|-|=)    - change line breaking format (\\r, \\n, as is)," },
        { "String_9", " -f <charset> - source text is in specified charset," },
        { "String_10", " -o <charset> - target text will be convert into specified charset," },
        { "String_11", " --           - treat following arguments as input file names," },
        { "String_12", " --help, -h   - print this help message" },
        { "String_13", "The default options are:" },
        { "String_14", " encode -f {0} -t {1} -o - -c= -- -" },
        { "ILLEGAL_ARGUMENT", "Illegal argument {0}" },
        { "EXTRA_PARAMETERS", "Extra parameters in command line" },
        { "ARGUMENT_REQUIRED", "Option {0} requires an argument" },
        { "PARSE_ARGUMENTS_ERROR", "Can not parse command line" },
        { "FILE_NAMES_REQUIRED", "Option -s requires file names to be explicityle specifeid" },
    };

    public Object[][] getContents() {
        return contents;
    }
}

