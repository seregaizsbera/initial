package encode;

import java.util.ListResourceBundle;

public class UsageResourceBundle_ru extends ListResourceBundle {
    static final Object[][] contents = new String[][]{
        { "String_1", "���������: encode [�����] [--] [����1 ����2...]\n" },
        { "String_2", "           encode --help | -h\n" },
        { "String_3", "��������� ��������� ����� �� ����� ��������� � ������.\n" },
        { "String_4", "������������ ��������� �����:\n" },
        { "String_5", " -s           - ��������� ����� � ����� ����,\n" },
        { "String_6", " -o <outfile> - ��������� �������� ��������� � outfile,\n" },
        { "String_7", " -c(+|-|=)    - �������� ������ ��������� �����" },
        { "String_8", " (\\r, \\n, ��� ����),\n" },
        { "String_9", " -f <charset> - �������� ����� ����������� � ��������� charset,\n" },
        { "String_10", " -o <charset> - ��������� ����� � ��������� charset,\n" },
        { "String_11", " --           - ��������, ��� ������ ������� ������ ����� ������,\n" },
        { "String_12", " --help, -h   - ������� ��� ���������\n" },
        { "String_13", "�� ��������� ������������ ��������� �������� �����:\n" },
        { "String_14", " encode -f {0} -t {1} -o - -c= -- -\n" },
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
