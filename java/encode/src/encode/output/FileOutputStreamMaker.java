package encode.output;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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

class FileOutputStreamMaker implements OutputStreamMaker {
  private final Iterator inputFiles;
  private final OutputStream output;

  FileOutputStreamMaker() throws FileNotFoundException {
    Param param = Param.getInstance();
    inputFiles = param.getInputFiles().iterator();
    output = new FileOutputStream(param.getOutputFile());
  }

  public boolean hasNext() {
    return true;
  }

  public OutputStream getNext() {
    return output;
  }

  public void translated() {
    if(!inputFiles.hasNext() && output != null)
      try {
        output.close();
      }
      catch(IOException e) {
        e.printStackTrace();
      }
  }
}

