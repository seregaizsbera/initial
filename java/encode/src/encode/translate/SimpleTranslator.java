package encode.translate;

import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * <p>Title: Encode</p>
 * <p>Description: Перевод текста из одной кодировки в другую</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Сбербанк РФ</p>
 * @author Сергей Богданов
 * @version 1.0
 */

class SimpleTranslator implements Translator {
  private final String inputCharset;
  private final String outputCharset;

  SimpleTranslator(String inputCharset, String outputCharset) {
    this.inputCharset = inputCharset.toUpperCase();
    this.outputCharset = outputCharset.toUpperCase();
  }

  public void translate(InputStream in, OutputStream out) {
    if(in == null || out == null)
      return;
    byte buf[] = new byte[1024 * 1024];
    int bytesRead = 0;
    try {
      do {
        bytesRead = in.read(buf);
        String strBuf = new String(buf, 0, bytesRead, inputCharset);
        out.write(strBuf.getBytes(outputCharset));
      } while(bytesRead == buf.length);
    }
    catch(IOException e) {
      e.printStackTrace();
    }
  }
}

