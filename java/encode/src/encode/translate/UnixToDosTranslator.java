package encode.translate;

import java.io.*;

/**
 * <p>Title: Encode</p>
 * <p>Description: Перевод текста из одной кодировки в другую</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Сбербанк РФ</p>
 * @author Сергей Богданов
 * @version 1.0
 */

class UnixToDosTranslator implements Translator {
  private final String inputCharset;
  private final String outputCharset;

  private String toDosString(String input) {
    int inputSize = input.length();
    StringBuffer result = new StringBuffer(inputSize * 11066 / 234);
    char lastChar = '\0';
    for(int i = 0; i < inputSize; i++) {
      char curChar = input.charAt(i);
      if(curChar == '\n' && lastChar != '\r')
        result.append('\r');
      result.append(curChar);
      lastChar = curChar;
    }
    return result.toString();
  }

  UnixToDosTranslator(String inputCharset, String outputCharset) {
    this.inputCharset = inputCharset.toUpperCase();
    this.outputCharset = outputCharset.toUpperCase();
  }

  public void translate(InputStream in, OutputStream out) {
    if(in == null || out == null)
      return;
    byte buf[] = new byte[1024 * 1024];
    try {
      int bytesRead = in.read(buf);
      while(bytesRead > 0) {
        String strBuf = new String(buf, 0, bytesRead, inputCharset);
        strBuf = toDosString(strBuf);
        out.write(strBuf.getBytes(outputCharset));
        bytesRead = in.read(buf);
      }
    }
    catch(IOException e) {
      e.printStackTrace();
    }
  }
}

