package encode;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.MessageFormat;

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
    private static int usage(int exitValue) {
        PrintStream out = (exitValue == 0) ? System.out : System.err;
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            Writer output = new OutputStreamWriter(bytes, Param.DEFAULT_OUTPUT_CHARSET);
            output.write(
                    "Синтаксис: encode [опции] [--] [файл1 файл2...]\n" +
                    "           encode --help | -h\n" +
                    "Переводит текстовые файлы из одной кодировки в другую.\n" +
                    "Используются следующие опции:\n" +
                    " -s         - перевести файлы в самих себя,\n" +
                    " -o outfile - результат перевода поместить в outfile,\n" +
                    " -c(+|-|=)  - изменить формат окончания строк" +
                    " (\\r, \\n, как есть),\n" +
                    " -f charset - исходный текст представлен в кодировке charset,\n" +
                    " -o charset - перевести текст в кодировку charset,\n" +
                    " --         - означает, что дальше следуют только имена файлов,\n" +
                    " --help, -h - вывести это сообщение\n" +
                    "По умолчанию используются следующие значения опций:\n" +
                    MessageFormat.format(" encode -f {0} -t {1} -o - -c= -- -\n", new Object[] { Param.DEFAULT_INPUT_CHARSET, Param.DEFAULT_OUTPUT_CHARSET}));
            output.close();
            out.write(bytes.toByteArray());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
