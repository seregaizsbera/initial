package encode.input;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import encode.Param;

/**
 * <p>Title: Encode</p>
 * <p>Description: Перевод текста из одной кодировки в другую</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Сбербанк РФ</p>
 * @author Сергей Богданов
 * @version 1.0
 */

class FileInputStreamMaker implements InputStreamMaker {
  private final Iterator inputFiles;
  private InputStream currentStream;

  private void closeCurrentStream() {
    if(currentStream != null)
    try {
      currentStream.close();
    }
    catch(IOException e) {
      e.printStackTrace();
    }
    currentStream = null;
  }

  FileInputStreamMaker() {
    Param param = Param.getInstance();
    inputFiles = param.getInputFiles().iterator();
    currentStream = null;
  }

  public InputStream getNext() {
    closeCurrentStream();
    if(!hasNext())
      return null;
    String inputFile = (String)inputFiles.next();
    try {
      currentStream = new FileInputStream(inputFile);
    }
    catch(FileNotFoundException e) {
      System.err.println("encode: " + e.getMessage() + ".");
      return null;
    }
    return currentStream;
  }

  public void translated() {
    closeCurrentStream();
  }

  public boolean hasNext() {
    return inputFiles.hasNext();
  }
}
