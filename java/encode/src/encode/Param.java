package encode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>Title: Encode</p>
 * <p>Description: Перевод текста из одной кодировки в другую</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Сбербанк РФ</p>
 * @author Сергей Богданов
 * @version 1.0
 */

public class Param {
  private static Param instance = null;

  public static final int CONSOLE_OUTPUT = 1;
  public static final int SELF_OUTPUT    = 2;
  public static final int FILE_OUTPUT    = 3;
  public static final int IGNORE_CRLF    = 4;
  public static final int TO_UNIX_CRLF   = 5;
  public static final int TO_DOS_CRLF    = 6;
  public static final int CONSOLE_INPUT  = 7;
  public static final int FILE_INPUT     = 8;

  private Collection inputFiles;
  private String outputFile;
  private int outputMode;
  private boolean helpRequested;
  private int crlfMode;
  private String inputCharset;
  private String outputCharset;
  private int inputMode;

  private Param() {
    inputFiles = new ArrayList();
    outputFile = null;
    outputMode = CONSOLE_OUTPUT;
    helpRequested = false;
    crlfMode = IGNORE_CRLF;
    inputCharset = "WINDOWS-1251";
    outputCharset = "KOI8-R";
    inputMode = CONSOLE_INPUT;
  }

  void parseArgs(String args[]) throws InvalidArgsException {
    Set argsWithOptions = new HashSet();
    argsWithOptions.add("-f");
    argsWithOptions.add("-o");
    argsWithOptions.add("-t");
    Set argsWithoutOptions = new HashSet();
    argsWithoutOptions.add("-s");
    argsWithoutOptions.add("-c+");
    argsWithoutOptions.add("-c-");
    argsWithoutOptions.add("-c=");
    argsWithoutOptions.add("--");
    argsWithoutOptions.add("--help");
    final int START = 0;
    final int B = 1;
    final int C = 2;
    final int D = 3;
    final int E = 4;
    final int F = 5;
    final int ERROR = 9;
    final int LAST = 10;
    final int UNKNOWN_ERROR = 11;
    final int HELP = 12;
    final int ARGUMENT_REQUIRED_ERROR = 13;
    final int EXTRA_ARGUMENTS_ERROR = 14;
    int state = START;
    int i = 0;
    loop: while(true) {
      String arg = (i < args.length) ? args[i] : null;
      switch(state) {
        case START:
          if(arg == null) {
            state = LAST;
            break;
          }
          if(arg.startsWith("-"))
            state = B;
          else
            state = C;
          break;
        case B: // разбор опций
          if(arg == null) {
            state = UNKNOWN_ERROR;
            break;
          }
          if(argsWithOptions.contains(arg))
            state = D;
          else if(argsWithoutOptions.contains(arg))
            state = E;
          else
            state = ERROR;
          break;
        case C: // разбор имен файлов
          if(arg == null) {
            state = LAST;
            break;
          }
          if(arg.equals("--")) {
            state = F;
            i++;
          } else if(arg.startsWith("-"))
            state = ERROR;
          else {
            inputFiles.add(arg);
            i++;
          }
          break;
        case D: // разбор опций с аргументами
          if(arg == null) {
            state = UNKNOWN_ERROR;
            break;
          }
          String option = arg;
          i++;
          String optionArgument = (i < args.length) ? args[i] : null;
          if(optionArgument == null) {
            i--;
            state = ARGUMENT_REQUIRED_ERROR;
            break;
          }
          if(option.equals("-f")) {
            inputCharset = optionArgument;
            i++;
            state = START;
          } else if(option.equals("-t")) {
            outputCharset = optionArgument;
            i++;
            state = START;
          } else if(option.equals("-o")) {
            if(optionArgument.equals("-")){
              outputMode = CONSOLE_OUTPUT;
              outputFile = null;
            } else {
              outputFile = optionArgument;
              outputMode = FILE_OUTPUT;
            }
            i++;
            state = START;
          }
          break;
        case E: // разбор опций без аргументов
          if(arg == null) {
            state = UNKNOWN_ERROR;
            break;
          }
          if(arg.equals("--")) {
            state = F;
            i++;
          }
          else if(arg.equals("-c-")) {
            crlfMode = TO_DOS_CRLF;
            i++;
            state = START;
          } else if(arg.equals("-c+")) {
            crlfMode = TO_UNIX_CRLF;
            i++;
            state = START;
          } else if(arg.equals("-c=")) {
            crlfMode = IGNORE_CRLF;
            i++;
            state = START;
          } else if(arg.equals("-s")) {
            outputMode = SELF_OUTPUT;
            i++;
            state = START;
          } else if(arg.equals("--help")) {
            i++;
            state = HELP;
          } else
            state = UNKNOWN_ERROR;
          break;
        case F: // оставшиеся опции - имена файлов
          if(arg == null) {
            state = LAST;
            break;
          }
          inputFiles.add(arg);
          i++;
          break;
        case HELP:
          helpRequested = true;
          if(args.length != 1)
            state = EXTRA_ARGUMENTS_ERROR;
          state = LAST;
          break;
        case LAST:
          break loop;
        case ERROR:
          throw new InvalidArgsException("Неправильный аргумент " + arg);
        case EXTRA_ARGUMENTS_ERROR:
          throw new InvalidArgsException("Слишком много параметров");
        case ARGUMENT_REQUIRED_ERROR:
          throw new InvalidArgsException("Нехватает обязательного аргумента" +
                                         " для опции " + arg);
        case UNKNOWN_ERROR:
        default:
          throw new InvalidArgsException("Не могу разобрать аргументы" +
                                         " командной строки");
      }
    }
    if(inputFiles.isEmpty() ||
       (inputFiles.size() == 1 && inputFiles.toArray()[0].equals("-"))) {
      inputFiles.clear();
      inputMode = CONSOLE_INPUT;
      if(outputMode == SELF_OUTPUT)
        throw new InvalidArgsException("Для опции -s требуется указать" +
                                       " имена редактируемых файлов");
    } else
      inputMode = FILE_INPUT;
  }

  public Collection getInputFiles() {
    return inputFiles;
  }

  public String getOutputFile() {
    return outputFile;
  }

  public int getOutputMode() {
    return outputMode;
  }

  public int getInputMode() {
    return inputMode;
  }

  public boolean isHelpRequested() {
    return helpRequested;
  }

  public int getCrlfMode() {
    return crlfMode;
  }

  public String getInputCharset() {
    return inputCharset;
  }

  public String getOutputCharset() {
    return outputCharset;
  }

  public static Param getInstance() {
    if(instance == null)
      instance = new Param();
    return instance;
  }
}
