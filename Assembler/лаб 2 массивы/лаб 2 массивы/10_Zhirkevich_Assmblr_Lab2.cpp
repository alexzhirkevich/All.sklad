#include <iostream>
#include <vector>
using namespace std;
int main()
{
	setlocale(LC_ALL, "Rus");
	vector<int> vec;
	cout << "������� ��-�� �������. Ctrl+Z ��� ����������\n";
	int k, n, maxcount = 0, pos = 0;
	while (cin >> k)
		vec.push_back(k);
	n = vec.size();
	int *arr = new int[n];
	for (int i=0;i<n;i++)
		arr[i] = vec[i];
	vec.clear();
	if (!n)
	{
		cout << "��� ���������";
		system("pause");
		return 0;
	}
	cout << "����� � ������� �������� ������� �������������:\n";
	_asm
	{
		MOV esi, 0
		MOV edx, 0
		MOV ebx, 0
		MOV edi, 0
		MOV eax, arr
		
	cikl:
			INC esi
			CMP esi, n						// ���� ���������� ��������
			JGE sort						// ������������� � ����������;
			DEC esi
			MOV edx, [eax +esi* 4]			// ������� ������� � eax;
			MOV ecx, n						// ����� � ecx
		
		cnt:
			DEC ecx
			CMP edx, [eax +ecx * 4]			// �������� ������� �� ����� �� �������;
			JE nubm							// ���� ����� ������������� � numb,
			JMP tst							// ���� ��� �� � tst;
		
	nubm:
		INC ebx								// ��������� ������� ���������� ���������� ���������;
		JMP tst								// ������������� � tst;
	
	tst:
		cmp ecx, 0 							// ���� ������ �������� ����� ��������������
		JG cnt
		CMP ebx, maxcount					// ���� �����, �� �������� ���������� ������������� �������� � ������������;
		JG setmax							// ���� ������, �� ������������� � setmax;

		continuee:
			MOV ebx, 0						// �������� ������� ����������;
			INC esi							// ��������� ����� ������������� ��������;
			JMP cikl						// ������������� � cikl;

	setmax :
			MOV maxcount, ebx				// ��������� ����������� ����������
			MOV edi, [eax +esi * 4]			// ��������� �������, �������� ������ �����;
			JMP continuee					// ������������� � continue;
		
	sort:
		MOV maxcount, 0						// �������� ��������;
		MOV ebx, pos						// � edx ��������� ������� �������, � ������� ����� ��������� ��������;
		MOV ecx, pos						// �������, ������� � �������
		INC ebx
		CMP ebx, n							// ���� ������� ����� ����������
		JGE stop							// ������ ������������ - ������������� � stop;
		DEC ebx
	check:
		CMP ecx, n							// �������� ������� ����� � �������;
		JGE repeat							// ���� ������, ������������� � repeat;
		MOV edx, [eax +ecx * 4]				// � eax �������, ������� � �������;
		CMP edx,edi							// �������� ����� ������������������ � �������
		JE change							// ���� �����, �� ������������� � change,
		INC ecx								// ���� ���, ��������� �������;
		JMP check							// ��������� � check;
	change:
		MOV edx, [eax +ecx * 4]
		PUSH edx
		MOV edx, [eax +ebx * 4]				// �������� ������� �������� � ��������� ecx � edx
		MOV [eax +ecx * 4], edx				
		POP edx
		MOV [eax +ebx * 4], edx
		INC ebx								// ��������� ����� �������;
		INC ecx								// ��������� �������
		JMP check							// ��������� � check;

	repeat:
		MOV pos, ebx						// ��������� ��������� �������
		MOV esi, pos						// ������ ������ �������� ����� ������������� � ������ �������
		MOV ebx, 0							// �������� ebx
		JMP cikl							// ��������� � ������ cikl
	stop:
	}

	for (int i = 0; i <n;i++)
	{
		cout << arr[i] << ' ';
	}
	cout << endl;
	system("pause");
	return 0;
}