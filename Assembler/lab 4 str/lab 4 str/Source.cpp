#include <iostream>
#include <Windows.h>
using namespace std;

int main() {
	SetConsoleCP(1251);
	SetConsoleOutputCP(1251);
	char nstr[256];
	char sogl[] = ".��������������������������������������������";
	char pred;
	int newlen = 0;
	char ctr[256];
	cout << "������� ������\n";
	cin.getline(ctr, 256);
	_asm {
			LEA ebx, ctr								// ������ � ebx;
			XOR ah, ah									// �������� ah;
			XOR edx, edx								// �������� edx; 
			_cikl :										// ���� �������� ���� ����;
				CMP [ebx + edx], 0						// ���� ����� ������, ��
				JE _end									// ������������� � _end
				MOV ecx, 44								// ����� ������ ��������� � ecx;
				_comp :									// ���� �������� ����� �� �����������;
					MOV al, byte ptr sogl[ecx]			// ��������� � al;
					CMP [ebx + edx] ,al					// ���� ����� ���������, ��
					JE _sum								// ������������� � _sum,
					LOOP _comp							
					CMP ah, 0							// ���� �� ���������, ��, ���� ����� ��� �� ��� ������� ��������� (ah=0),
					JE _push							// ������������� � _push;
					DEC edx								// ���� ����� ��� ��������� ���������, ��
					JMP _write							// �������� ���������� ��������� � ����� _write
				_sum:							
					CMP ah, 0							// ���� ��� ������ ���������, ��
					JE _add								// ������������� � _add,
					MOV al, pred						// ���� ���, ��
					CMP [ebx+edx], al					// �������� � � ���������� ���������
					JNE _write							// ���� �� �����, �� ������������� � _write
				_add:
					MOV al, [ebx+edx]					// ����� � al;
					MOV pred, al						// ��������� � ��� ������ ����������;
					INC ah								// ��������� ���������� ���������;
					INC edx								// ��������� ������ ������������ �����;
					JMP _cikl							// ��������� � �������� ���������
				_write:
					CMP ah,1							// ���� ��������� ���� ������ ����, ��
					JG _write2							// ������������� � _write2,
					MOV ecx, newlen						// ���� ���, �� ����� ����� ������ � ecx;
					MOV al, pred						// ������������� ����� � al;
					MOV nstr[ecx], al					// �������� � � ������;
					XOR ah,ah							// �������� ������� ������������� ����;
					INC ecx								// ��������� ����� �����;
					MOV newlen, ecx						// ��������� �;
					CMP [ebx + edx], 0					// ��� ���� �������� �� ����� ������, �����, ���� ������ ������������� ����������� ����������
					JE _end					
					MOV al, pred						
					CMP [ebx + edx], al					// ���� ������ ���������� ����� �� ����� �������������, �� ����������� ������ �� �����
					JNE _back					
					INC edx
					JMP _cikl
				_write2:
					JMP _chislo1						// ������������� � _chislo 1
				_backToWrite:
					XOR ah,ah							// �������� ������� ����������
					INC ecx								// ��������� ����� �����
					MOV newlen, ecx
					CMP [ebx + edx], 0					// �������� �� ����� ������
					JE _end
					MOV al, pred
					CMP[ebx + edx], al					
					JNE _back							// ���� ������ ���������� ����� �� ����� �������������, �� ����������� ������ �� �����
					INC edx
					JMP _cikl
				_back:
					MOV pred, 0							// �������� ���������� ������
					JMP _cikl							// ��������� � _cikl
				_push:
					MOV ecx,newlen						
					MOV al, [ebx+edx]
					MOV nstr[ecx],al					// ������ ������� ����� � ������
					INC ecx
					MOV newlen, ecx
					INC edx
					JMP _cikl
			_end:
				XOR ecx, ecx
				CMP ah,0
				JG _write
				MOV ecx, newlen
				MOV nstr[ecx], 0
				INC ecx
			_endcikl:
				CMP ecx, 0
				JE _end2								// �� ��������� �������������, ������������� � ����� ���������
				MOV al, nstr[ecx-1]						// ������������� �� �������� ������ � ��������
				MOV [ebx+ecx-1], al
				DEC ecx
				JMP _endcikl
			_chislo1:										// ������ ������ ����� ���������� � ������;
				MOV ecx, newlen								// ����� ����� ������ � ecx;
				CMP ah,	10									// ���� ����� ������ 10, �� 
				JGE _chislo2								// ������������� � _chislo2
				MOV al, '0'
				ADD al, ah									// ���� ������, �� �������� ����� � �����
				MOV nstr[ecx], al
				JMP _backToWrite							// � ��������� � _backToWrite
			_chislo2:				
				CMP ah, 100									// ���� ����� ������ 100, ��
				JGE _chislo3								// ������������� � _chislo 3
				PUSH ebx			
				PUSH edx
				MOV al,ah
				CBW
				MOV bl, 10
				DIV bl
				ADD al, '0'									// ������ � ������ �����, ���������� �� 2 ����
				ADD ah, '0'
				MOV nstr[ecx],al
				INC ecx
				MOV nstr[ecx], ah
				POP edx
				POP ebx
				JMP _backToWrite							// ��������� � _backToWrite
			_chislo3:
				PUSH ebx
				PUSH edx
				MOV al,ah
				CBW
				MOV bl, 10
				DIV bl
				ADD ah, '0'
				MOV nstr[ecx+2], ah
				CBW
				DIV bl										// ������ � ������ �����, ���������� �� 3 ����
				ADD al, '0'
				ADD ah, '0'
				MOV nstr[ecx], al
				INC ecx
				MOV nstr[ecx], ah
				INC ecx
				POP edx
				POP ebx
				JMP _backToWrite							// ��������� � _backToWrite
		_end2:
	}
	cout << "��� ������������� ��������� �������� �� ���������� �� ����������:\n";
	cout << ctr << endl;
	system("pause");
	return 0;
}