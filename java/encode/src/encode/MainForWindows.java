package encode;

/**
 * <p>Title: Encode</p>
 * <p>Description: Перевод текста из одной кодировки в другую</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Сбербанк РФ</p>
 * @author unascribed
 * @version 1.0
 */

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;
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

public class MainForWindows {
  private static void printMessage(PrintStream out, String message) {
    try {
      out.write(message.getBytes("cp866"));
    }
    catch(IOException e) {
      out.print(message);
    }
  }

  private static int usage(int exitValue) {
    PrintStream out = (exitValue == 0) ? System.out : System.err;
    String message =
        "Синтаксис: encode [опции] [--] [файл1 файл2...]\n" +
        "           encode --help | -h\n" +
        "Переводит текстовые файлы из одной кодировки в другую.\n" +
        "Используются следующие опции:\n" +
        " -s         - перевести файлы в самих себя,\n" +
        " -o outfile - результат перевода поместить в outfile,\n" +
        " -c(+|-|=)  - окончания строк переводить в формат Unix" +
        " (DOS, как есть),\n" +
        " -f charset - исходный текст представлен в кодировке charset,\n" +
        " -o charset - перевести текст в кодировку charset,\n" +
        " --         - означает, что дальше следуют только имена файлов,\n" +
        " --help, -h - вывести это сообщение\n" +
        "По умолчанию используются следующие значения опций:\n" +
        " encode -f koi8-r -t windows-1251 -o - -c= -- -\n";
    printMessage(out, message);
    return exitValue;
  }

  public static void main(String args[]) {
    try {
      Param param = Param.getInstance();
      param.switchCharsets();
      param.parseArgs(args);
      if(param.isHelpRequested())
        System.exit(usage(0));
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
      printMessage(System.err, "encode: " + e.toString() + ".\n");
      System.exit(usage(1));
    }
    catch(UnsupportedEncodingException e) {
      System.err.println("encode: " + e.toString() + ".");
      System.exit(1);
    }
    catch(FileNotFoundException e) {
      System.err.println("encode: " + e.toString() + ".");
      System.exit(1);
    }
  }
}
