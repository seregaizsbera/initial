package encode.translate;

import java.io.UnsupportedEncodingException;
import encode.Param;

/**
 * <p>Title: Encode</p>
 * <p>Description: Перевод текста из одной кодировки в другую</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Сбербанк РФ</p>
 * @author Сергей Богданов
 * @version 1.0
 */

public class TranslatorFactory {
  private final String inputCharset;
  private final String outputCharset;
  private final int mode;

  public Translator getTranslator() {
    switch(mode) {
      case Param.TO_DOS_CRLF:
        return new UnixToDosTranslator(inputCharset, outputCharset);
      case Param.TO_UNIX_CRLF:
        return new DosToUnixTranslator(inputCharset, outputCharset);
      case Param.IGNORE_CRLF:
      default:
        return new SimpleTranslator(inputCharset, outputCharset);
    }
  }

  public TranslatorFactory() throws UnsupportedEncodingException {
    Param param = Param.getInstance();
    inputCharset = param.getInputCharset();
    outputCharset = param.getOutputCharset();
    mode = param.getCrlfMode();
    "Test".getBytes(inputCharset);
    "Test".getBytes(outputCharset);
  }
}
