/* �������� ���������� ������������ �������� ������ � ��������
 * ��������� ������ ���������� ����� ���������� � �������� ������ ��
 * �� ��������. � �������� ������ ���������� ������������ � ��������� ����:
 *      %name%
 */
import java.util.*;
import java.io.*;

class Prg_01x
{
    public static void main(String[] args) throws IOException
    {
        String str = "", envname = "";
        
        boolean inword = false;
	// �������������� ����� �����:
	BufferedReader in = 
		new BufferedReader(new InputStreamReader(System.in));
//������� ��� break:
//        int c;
//        while (( c = in.read() ) != -1 ) {
//     ���������������� if � break    
//������� c break:
         while ( true ) {
            int c = in.read();
            if ( c == -1 ) { //eof
                break;
            }
            if (c == '\r') {
                continue;
            }
            if (c == '\n') {
                System.out.println(str);
                str = "";
                continue;
            }
            if (c == '%') {
                if (!inword) {
                    inword = true;
                    envname = "";
                }
                else {
                    String s = System.getenv(envname);
                    if (s != null) {
                        str += s;
                    }
                    inword = false;
                }
            }
            else {
                if (inword)
                    envname += (char)c;
                else
                    str += (char)c;
            }
        }
        if (inword) {
            str += '%' + envname;
        }
        if (str.length() != 0) {
            System.out.print(str);
        }
        in.close();
    }
}

