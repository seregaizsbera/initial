package encode;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import encode.input.InputStreamMaker;
import encode.input.InputStreamMakerFactory;
import encode.output.OutputStreamMaker;
import encode.output.OutputStreamMakerFactory;
import encode.translate.Translator;
import encode.translate.TranslatorFactory;

/**
 * <p>Title: Encode</p>
 * <p>Description: Перевод текста из одной кодировки в другую</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Сбербанк РФ</p>
 * @author Сергей Богданов
 * @version 1.0
 */
public class Main {
    static ResourceBundle bundle = ResourceBundle.getBundle("encode.UsageResourceBundle");

    private static int usage(int exitValue) {
        PrintStream out = (exitValue == 0) ? System.out : System.err;
        out.println(bundle.getString("String_1"));
        out.println(bundle.getString("String_2"));
        out.println(bundle.getString("String_3"));
        out.println(bundle.getString("String_4"));
        out.println(bundle.getString("String_5"));
        out.println(bundle.getString("String_6"));
        out.println(bundle.getString("String_7"));
        out.println(bundle.getString("String_9"));
        out.println(bundle.getString("String_10"));
        out.println(bundle.getString("String_11"));
        out.println(bundle.getString("String_12"));
        out.println(bundle.getString("String_13"));
        out.println(MessageFormat.format(bundle.getString("String_14"), new Object[] { Param.DEFAULT_INPUT_CHARSET, Param.DEFAULT_OUTPUT_CHARSET}));
        return exitValue;
    }

    public static void main(String args[]) {
        try {
            Param param = Param.getInstance();
            param.parseArgs(args);
            if (param.isHelpRequested()) {
                System.exit(usage(0));
            }
            Translator translator = new TranslatorFactory().getTranslator();
            InputStreamMaker inputStreamMaker = new InputStreamMakerFactory().getInputStreamMaker();
            OutputStreamMaker outputStreamMaker = new OutputStreamMakerFactory().getOutputStreamMaker();
            while (inputStreamMaker.hasNext()) {
                try {
                    InputStream in = inputStreamMaker.getNext();
                    OutputStream out = outputStreamMaker.getNext();
                    translator.translate(in, out);
                    inputStreamMaker.translated();
                    outputStreamMaker.translated();
                } catch (FileNotFoundException e) {
                    System.err.println(MessageFormat.format("encode: {0}", new Object[]{e.getMessage()}));
                }
            }
        } catch (InvalidArgsException e) {
            System.err.println("encode: " + e.toString() + ".");
            System.exit(usage(1));
        } catch (UnsupportedEncodingException e) {
            System.err.println("encode: " + e.toString() + ".");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("encode: " + e.toString() + ".");
            System.exit(1);
        }
    }
}
