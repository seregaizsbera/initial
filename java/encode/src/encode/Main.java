package encode;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
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
    out.println(
        "Usage: encode [-f input-charset] [-t output-charset] [-c(+|-|=)]" +
        " [-o outfile|-s] [--] [file...]\n" +
        "       encode --help\n\n" +
        " Переводит текст из одной кодировки в другую\n" +
        "работает, почти как cat, только -s означает перевести файлы\n" +
        "в самих себя, -o - результат перевода поместить в outfile.\n" +
        " -c+ (-, =) - окончания строк переводить в формат Unix" +
        " (DOS, как есть)\n" +
        " --         - означает, что дальше следуют только имена файлов\n" +
        " --help     - выводит это сообщение\n\n");
    return exitValue;
  }

  public static void main(String args[]) {
    try {
      Param param = Param.getInstance();
      param.parseArgs(args);
      Translator translator = new TranslatorFactory().getTranslator();
      InputStreamMaker inputStreamMaker =
          new InputStreamMakerFactory().getInputStreamMaker();
      OutputStreamMaker outputStreamMaker =
          new OutputStreamMakerFactory().getOutputStreamMaker();
      while(inputStreamMaker.hasNext()) {
        InputStream in = inputStreamMaker.getNext();
        OutputStream out = outputStreamMaker.getNext();
        translator.translate(in, out);
        inputStreamMaker.translated();
        outputStreamMaker.translated();
      }
    }
    catch(InvalidArgsException e) {
      System.err.println("encode: " + e.getMessage() + ".");
      System.exit(usage(1));
    }
    catch(UnsupportedEncodingException e) {
      System.err.println("encode: " + e.getMessage() + ".");
      System.exit(1);
    }
    catch(FileNotFoundException e) {
      System.err.println("encode: " + e.getMessage() + ".");
      System.exit(1);
    }
  }
}
