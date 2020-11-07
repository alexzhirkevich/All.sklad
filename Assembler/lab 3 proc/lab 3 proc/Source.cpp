#include <iostream>
#include <vector>
using namespace std;
extern "C" int __stdcall nbrCntStdCall(int* array,int len);
extern "C" int __fastcall nbrCntFastCall(int* array, int len);
extern "C" int __cdecl nbrCntCdecl(int* array, int len);

int main()
{
	//�������� ������������� �������
	setlocale(LC_ALL, "Rus");
	vector<int> vec;
	cout << "������� ��-�� �������. Ctrl+Z ��� ����������\n";
	int k, n, maxcount = 0, pos = 0;
	int newArrLen=0;
	while (cin >> k)
		vec.push_back(k);
	n = vec.size();
	int* array = new int[n];
	for (int i = 0; i < n; i++)
		array[i] = vec[i];
	int* newArray = new int[100];
	vec.clear();
	if (!n)
	{
		cout << "��� ���������";
		system("pause");
		return 0;
	}

	// stdcall
	cout << "��������, ������ ��������� ������� ��� ���������� (__stdcall):\n";
	memset(newArray, 0, 100);
	newArrLen = nbrCntStdCall(array,n,newArray);
	for (int i = 0; i < newArrLen; i++)
	{
		cout << newArray[i] << ' ';
	}

	cout << endl;

	//fastcall
	cout << "��������, ������ ��������� ������� ��� ���������� (__fastcall):\n";
	memset(newArray, 0, 100);
	newArrLen = nbrCntFastCall(array, n, newArray);
	for (int i = 0; i < newArrLen; i++)
	{
		cout << newArray[i] << ' ';
	}

	cout << endl;

	//cdecl
	cout << "��������, ������ ��������� ������� ��� ���������� (__cdecl):\n";
	memset(newArray, 0, 100);
	newArrLen = nbrCntCdecl(array, n, newArray);
	for (int i = 0; i < newArrLen; i++)
	{
		cout << newArray[i] << ' ';

	}
}

//_asm
//{
//	MOV ebx, arr							// �������� ������ � ebx
//	MOV ecx, n								// ����� � ecx
//	DEC ecx
//	DEC ecx
//
//	cikl :
//	MOV eax, [ebx + ecx * 4 + 4]				//	n+1-�� ������� � eax
//		ADD eax, [ebx + ecx * 4 - 4]		// ��������� n-1-�� �������
//		CDQ
//		SUB eax, edx						// ������� �� 2 ��� ������ ������
//		SAR eax, 1
//		CMP eax, [ebx + ecx * 4]				// �������� � n-1 ���������
//		JE _add
//		LOOP cikl
//		JMP _end
//
//		_add :
//	PUSH ecx
//		MOV ecx, newlen						// ���������� ��������� ������ ������� � ecx
//		CMP ecx, 0							// ���� �� ���, ������������� � write
//		JE write
//		CheckCikl :
//	CMP eax, dword ptr newarr[ecx * 4 - 4]		//	���������� ���������� �� ����� ���������� ������ �������
//		JE nowrite									//  � ���� �����, ������������ � cikl
//		LOOP CheckCikl
//
//		write :
//	MOV ecx, newlen
//		MOV dword ptr newarr[ecx * 4], eax
//		INC ecx
//		MOV newlen, ecx
//		nowrite :
//	POP ecx
//		LOOP cikl
//		_end :
//}
#include <ctime>
#include <windows.h>
#include <iostream>

int main()
{
	SetConsoleCP(1251);
	SetConsoleOutputCP(1251);
	int *arr = new int[5];
	int len = 5;
	std::cout << "������ �� ����� �����, ��������������� �������������:\n";
	for (int i = 0; i < len; i++)
	{
		arr[i] = -10 + rand() % 20;
		std::cout << arr[i] << ' ';
	}

	/*_asm
	{
		LEA ebx, arr
		MOV edx, 4
		MOV esi, len
		_cikle:
			MOV eax, [ebx][edx]
			CMP edx, esi
			JGE _end
			PUSH esi
			MOV ecx, edx
				_loop:
				PUSH eax
				CMP ecx, 0
				JE _cont
				MOV eax, [ebx][ecx-4]
				CMP [ebx][edx], eax
				JGE _loop2
				POP eax
				MOV eax, ecx
				SUB ecx, 4
				JMP _loop
				_loop2:
				POP eax
				SUB ecx, 4
				JMP _loop
			_cont:
			MOV ecx, eax
			MOV eax, [ebx][edx]
			PUSh eax
			MOV eax, [ebx][ecx*4]
			MOV [ebx][edx], eax
			POP eax
			MOV [ebx][eax*4], eax
			ADD edx, 4
			POP esi
			JMP _cikle
		_end:
	}*/
	_asm
	{
		MOV ebx, arr
		MOV edx, 4
		_cikle :
		MOV ecx, len
			IMUL ecx, 4
			CMP edx, ecx
			JGE _end
			MOV eax, [ebx][edx]
			MOV ecx, edx
			_loop :
		CMP ecx, 0
			JLE _cont
			CMP[ebx][ecx - 4], eax
			JGE _loop2
			MOV eax, [ebx][ecx - 4]
			SUB ecx, 4
			JMP _loop
			_loop2 :
		SUB ecx, 4
			JMP _loop
			_cont :
		PUSH eax
			ADD edx, 4
			JMP _cikle
			_end :
		MOV ecx, len
			_back :
		XOR edx, edx
			CMP ecx, 4
			JE _endend
			POP eax
			MOV[ebx][ecx - 4], eax
			SUB ecx, 4
			_endend:

	}

	std::cout << std::endl;
	for (int i = 0; i < len; i++) {
		std::cout << arr[i] << ' ';
	}
	system("pause");

}