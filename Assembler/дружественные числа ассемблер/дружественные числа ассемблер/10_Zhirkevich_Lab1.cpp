#include <iostream>
using namespace std;
int main()
{
	setlocale(0, "rus");
	int n, k, j = 1, sum1 = 0, sum2, mid, temp;
	bool found = false;
	cout << "�������  ������ � ����� ��������� ��� ������ ������������� �����: ";
	cin >> n >> k;
	_asm
	{
		mov ecx, n // ecx - ������� �� n �� �
		MOV edi, 2 // ������ ����� 2 ��� ������ ��������
		first:
				INC ecx			// ��������� �������;
				MOV ebx, 0		// �������� ����� ���������;
				MOV eax, ecx	// ������� � eax;
				MOV edx, 0		// �������� edx ��� �������;
				IDIV edi		// ����� �������� �� ��������;
				MOV mid, eax	// �������� � mid;
				MOV esi, 1		// ���������� �������;
			frstsum:
				CMP esi, mid	// ���� ���������� ������� ������ �������� ��������
				JG second		// ������������� � second;
				MOV eax, ecx	// ������� � eax;
				MOV edx, 0		// �������� edx ��� �������;
				IDIV esi		// ���� ��� ������� �������� �������� �� ����������
				CMP edx, 0		// ������� ����� ���� (����������)
				JE firstadd		// ������������� � firstadd;
				INC esi			// ���� ���, ��������� �������;
				JMP frstsum		// ������������� � frstsum
			firstadd:
				ADD ebx, esi	// �������� ���������� ������� � ����� ��������� �������;
				INC esi			// ��������� ���������� �������
				JMP frstsum		// ������������� � frstsum
		second:
				MOV sum1, ebx	// ��������� ����� ��������� ������� ����� � sum1;
				MOV ebx, 0		// �������� ����� ���������;
				MOV eax, sum1	// ����� ��������� ������� � eax;
				MOV edx, 0		// �������� edx ��� �������;
				IDIV edi		// �������� ����� ��������� ������� �� 2;
				MOV mid, eax	// ��������� �������� � mid;
				MOV esi, 1		// ��������� ���������� ������� �� 1;
			scndsum:
				CMP esi, mid	// ���� ���������� ������� ������ mid
				JG comp1		// �� ������������� � comp1;
				MOV eax, sum1	// ���� ���, �� ����� ��������� ������� � eax;
				MOV edx, 0		// �������� edx ��� �������;
				IDIV esi		// �������� ����� ��������� ������� �� �������;
				CMP edx, 0		// ���� ���������� ������
				JE secondadd	// ������������� � secondadd;
				INC esi			// ��������� ���������� �������;
				JMP scndsum		// ������������� � scndsum;
			secondadd:
				ADD ebx, esi	// �������� � ����� ��������� ������� ���������� �������;
				INC esi			// ��������� ���������� �������;
				JMP scndsum		// ������������� � scndsum;
		comp1:
			MOV sum2, ebx	// ��������� ����� ��������� ������� � sum2;
			CMP ecx, sum2	// ���� ������ ����� ����� ����� ��������� �������
			JE comp2		// ������������� � comp2;
			JMP stop		// ���� ���, �� ������������� � stop;
		comp2:
			MOV j, ecx		// ������ ����� � j;
			CMP ecx, sum1	// ���� ��� ����� �� �����
			JNE print		// ����� ������� -> �����������
		repeat:
			MOV ecx, sum1	// ���������� ����� � ���������� ���������� �����
			MOV edi, 2		//	�������������� ����������� ������;
			JMP stop		//	������������� � stop;
		stop:
			CMP ecx, k		// ���� ������� ������ ��� ����� ��������� ���������
			JLE first		// ������������� � first;
			// ���� ������� ����� �� ��������, ���������� ���������;
	}


	if (!found)
		cout << "������������� ����� �� �������\n";
	system("pause");
	return 0;

	_asm
	{
		print:
	}

	found = true;
	cout << j << " � " << sum1 << endl;

	_asm
	{
		JMP repeat
	}
}