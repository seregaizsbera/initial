package encode.input;

import java.io.InputStream;

/**
 * <p>Title: Encode</p>
 * <p>Description: Перевод текста из одной кодировки в другую</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Сбербанк РФ</p>
 * @author Сергей Богданов
 * @version 1.0
 */

class ConsoleInputStreamMaker implements InputStreamMaker {
  private InputStream console;

  ConsoleInputStreamMaker() {
    console = System.in;
  }

  public boolean hasNext() {
    return console != null;
  }

  public InputStream getNext() {
    return console;
  }

  public void translated() {
    console = null;
  }
}