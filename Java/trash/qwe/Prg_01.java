// ������� ���������� ����������
import java.util.*;

// ���������� ������
class Prg_01 {

    public static void main( String[] args )
    {
        // ������ ������ Scanner ��� ����� ������
        Scanner in = new Scanner( System.in );

        // ������������ ����-�����
        // ���������� ������ ������:
        System.out.print("Enter your name: ");
        String name = in.nextLine();
        System.out.println("Hello " +  name);

        // �����
        System.out.print("Enter integer value: ");
        int i = in.nextInt();

        // ��������������
        System.out.print("Enter floating point number (error with wrong delimiter!) : ");
        double d = in.nextDouble();  // ���������� ��� �������� ����������� ������� �����
        
	// ����� �������� ������:
        System.out.println("You has enter: " + i + " and " + d );
        in.close();
    }
}

