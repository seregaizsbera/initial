package encode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>Title: Encode</p>
 * <p>Description: ������� ������ �� ����� ��������� � ������</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: �������� ��</p>
 * @author ������ ��������
 * @version 1.0
 */
public class Param {
    private static Param instance;
    public static final String DEFAULT_INPUT_CHARSET;
    public static final String DEFAULT_OUTPUT_CHARSET;

    static {
        DEFAULT_OUTPUT_CHARSET = System.getProperty("file.encoding", "KOI8-R");
        if (DEFAULT_OUTPUT_CHARSET.equals("KOI8-R")) {
            DEFAULT_INPUT_CHARSET = "WINDOWS-1251";
        } else {
            DEFAULT_INPUT_CHARSET = "KOI8-R";
        }
    }

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

    Param() {
    }

    private void init() {
        inputFiles = new ArrayList();
        outputFile = null;
        outputMode = CONSOLE_OUTPUT;
        helpRequested = false;
        crlfMode = IGNORE_CRLF;
        inputCharset = DEFAULT_INPUT_CHARSET;
        outputCharset = DEFAULT_OUTPUT_CHARSET;
        inputMode = CONSOLE_INPUT;
    }

    public void parseArgs(String args[]) throws InvalidArgsException {
        init();
        Set argsWithOptions = new HashSet();
        argsWithOptions.add("-f");
        argsWithOptions.add("-o");
        argsWithOptions.add("-t");
        Set argsWithoutOptions = new HashSet();
        argsWithoutOptions.add("-s");
        argsWithoutOptions.add("-c+");
        argsWithoutOptions.add("-c-");
        argsWithoutOptions.add("-c=");
        argsWithoutOptions.add("-h");
        argsWithoutOptions.add("--help");
        argsWithoutOptions.add("--");
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
        loop: while (true) {
            String arg = (i < args.length) ? args[i] : null;
            switch (state) {
                case START:
                    if (arg == null) {
                        state = LAST;
                        break;
                    }
                    if (arg.startsWith("-")) {
                        state = B;
		    } else {
                        state = C;
		    }
                    break;
                case B: // ������ �����
                    if (arg == null) {
                        state = UNKNOWN_ERROR;
                        break;
                    }
                    if (argsWithOptions.contains(arg)) {
                        state = D;
                    } else if(argsWithoutOptions.contains(arg)) {
                        state = E;
                    } else {
                        state = ERROR;
	            }
                    break;
                case C: // ������ ���� ������
                    if (arg == null) {
                        state = LAST;
                        break;
                    }
                    if (arg.equals("--")) {
                        state = F;
                        i++;
                        } else if (arg.startsWith("-")) {
                            state = ERROR;
                        } else {
                            inputFiles.add(arg);
                            i++;
                        }
                        break;
                    case D: // ������ ����� � �����������
                        if (arg == null) {
                            state = UNKNOWN_ERROR;
                            break;
                        }
                        String option = arg;
                        i++;
                        String optionArgument = (i < args.length) ? args[i] : null;
                        if (optionArgument == null) {
                            i--;
                            state = ARGUMENT_REQUIRED_ERROR;
                            break;
                        }
                        if (option.equals("-f")) {
                            inputCharset = optionArgument;
                            i++;
                            state = START;
                        } else if (option.equals("-t")) {
                            outputCharset = optionArgument;
                            i++;
                            state = START;
                        } else if (option.equals("-o")) {
                            if (optionArgument.equals("-")){
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
                    case E: // ������ ����� ��� ����������
                        if (arg == null) {
                            state = UNKNOWN_ERROR;
                            break;
                        }
                        if (arg.equals("--")) {
                            state = F;
                            i++;
                        } else if (arg.equals("-c-")) {
                            crlfMode = TO_DOS_CRLF;
                            i++;
                            state = START;
                        } else if (arg.equals("-c+")) {
                            crlfMode = TO_UNIX_CRLF;
                            i++;
                            state = START;
                        } else if (arg.equals("-c=")) {
                            crlfMode = IGNORE_CRLF;
                            i++;
                            state = START;
                        } else if (arg.equals("-s")) {
                            outputMode = SELF_OUTPUT;
                            i++;
                            state = START;
                        } else if (arg.equals("--help") || arg.equals("-h")) {
                            i++;
                            state = HELP;
                        } else {
                            state = UNKNOWN_ERROR;
			}
                        break;
                    case F: // ���������� ����� - ����� ������
                        if (arg == null) {
                            state = LAST;
                            break;
                        }
                        inputFiles.add(arg);
                        i++;
                        break;
                    case HELP:
                        helpRequested = true;
                        if (args.length != 1) {
                            state = EXTRA_ARGUMENTS_ERROR;
			}
                        state = LAST;
                        break;
                    case LAST:
                        break loop;
                    case ERROR:
                        throw new InvalidArgsException("������������ �������� " + arg);
                    case EXTRA_ARGUMENTS_ERROR:
                        throw new InvalidArgsException("������� ����� ����������");
                    case ARGUMENT_REQUIRED_ERROR:
                        throw new InvalidArgsException("��������� ������������� ��������� ��� ����� " + arg);
                    case UNKNOWN_ERROR:
                    default:
                        throw new InvalidArgsException("�� ���� ��������� ��������� ��������� ������");
            }
        }
        if (inputFiles.isEmpty() || (inputFiles.size() == 1 && inputFiles.iterator().next().equals("-"))) {
            inputFiles.clear();
            inputMode = CONSOLE_INPUT;
            if (outputMode == SELF_OUTPUT) {
                throw new InvalidArgsException("��� ����� -s ��������� ������� ����� ������������� ������");
            }
        } else {
            inputMode = FILE_INPUT;
        }
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
        if (instance == null) {
            instance = new Param();
        }
        return instance;
    }
}
