package encode;

import java.util.ListResourceBundle;

public class UsageResourceBundle_ru extends ListResourceBundle {
    static final Object[][] contents = new String[][]{
        { "String_1", "���������: encode [�����] [--] [����1 ����2...]" },
        { "String_2", "           encode --help | -h" },
        { "String_3", "��������� ��������� ����� �� ����� ��������� � ������." },
        { "String_4", "������������ ��������� �����:" },
        { "String_5", " -s           - ��������� ����� � ����� ����," },
        { "String_6", " -o <outfile> - ��������� �������� ��������� � outfile," },
        { "String_7", " -c(+|-|=)    - �������� ������ ��������� ����� (\\r, \\n, ��� ����)," },
        { "String_9", " -f <charset> - �������� ����� ����������� � ��������� charset,\n" },
        { "String_10", " -o <charset> - ��������� ����� � ��������� charset," },
        { "String_11", " --           - ��������, ��� ������ ������� ������ ����� ������," },
        { "String_12", " --help, -h   - ������� ��� ���������" },
        { "String_13", "�� ��������� ������������ ��������� �������� �����:" },
        { "String_14", " encode -f {0} -t {1} -o - -c= -- -" },
        { "ILLEGAL_ARGUMENT", "������������ �������� {0}" },
        { "EXTRA_PARAMETERS", "������� ����� ����������" },
        { "ARGUMENT_REQUIRED", "�� ������� ������������� ��������� ��� ����� {0}" },
        { "PARSE_ARGUMENTS_ERROR", "�� ���� ��������� ��������� ��������� ������" },
        { "FILE_NAMES_REQUIRED", "��� ����� -s ��������� ������� ����� ������������� ������" }
    };

    public Object[][] getContents() {
        return contents;
    }
}
