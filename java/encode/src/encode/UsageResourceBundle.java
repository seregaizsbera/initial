package encode;

import java.util.ListResourceBundle;

public class UsageResourceBundle extends ListResourceBundle {
    static final Object[][] contents = new String[][]{
        { "String_1", "Usage: encode [options] [--] [file1 file2...]\n" },
        { "String_2", "   encode --help | -h\n" },
        { "String_3", "Converts text files from one charset to another.\n" },
        { "String_4", "Options:\n" },
        { "String_5", " -s           - convert every file into itself,\n" },
        { "String_6", " -o <outfile> - redirect the result output to outfile,\n" },
        { "String_7", " -c(+|-|=)    - change line breaking format" },
        { "String_8", " (\\r, \\n, as is),\n" },
        { "String_9", " -f <charset> - source text is in specified charset,\n" },
        { "String_10", " -o <charset> - target text will be convert into specified charset,\n" },
        { "String_11", " --           - treat following arguments as input file names,\n" },
        { "String_12", " --help, -h   - print this help message\n" },
        { "String_13", "The default options are:\n" },
        { "String_14", " encode -f {0} -t {1} -o - -c= -- -\n" },
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

