package encode.output;

import java.io.File;
import java.io.FileInputStream;
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

class SelfOutputStreamMaker implements OutputStreamMaker {
  private final Iterator inputFiles;
  private OutputStream currentStream;
  private File currentFile;
  private File currentTempFile;

  private void closeCurrentFile() {
    try {
      if(currentStream != null)
        try {
          currentStream.close();
        }
        catch(IOException e) {
          e.printStackTrace();
        }
      if(currentFile == null || currentTempFile == null)
        return;
      try {
        FileOutputStream finalOut = new FileOutputStream(currentFile);
        FileInputStream finalIn = new FileInputStream(currentTempFile);
        byte buf[] = new byte[1024 * 1024];
        int bytesRead = 0;
        do {
          bytesRead = finalIn.read(buf);
          finalOut.write(buf, 0, bytesRead);
        } while(bytesRead == buf.length);
        currentTempFile.delete();
      }
      catch(FileNotFoundException e) {
        System.err.println("encode: " + e.toString() + ".");
      }
      catch(IOException e) {
        e.printStackTrace();
      }
    }
    finally {
      currentStream = null;
      currentFile = null;
      currentTempFile = null;
    }
  }

  SelfOutputStreamMaker() {
    Param param = Param.getInstance();
    inputFiles = param.getInputFiles().iterator();
    currentStream = null;
    currentFile = null;
    currentTempFile = null;
  }

  public boolean hasNext() {
    return inputFiles.hasNext();
  }

  public OutputStream getNext() {
    closeCurrentFile();
    if(!hasNext())
      return null;
    String fileName = (String)inputFiles.next();
    currentFile = new File(fileName);
    currentStream = null;
    try {
      currentTempFile = File.createTempFile("encode", currentFile.getName());
      currentStream = new FileOutputStream(currentTempFile);
      currentTempFile.deleteOnExit();
    }
    catch(FileNotFoundException e) {
      e.printStackTrace();
    }
    catch(IOException e) {
      e.printStackTrace();
    }
    return currentStream;
  }

  public void translated() {
    closeCurrentFile();
  }
}
