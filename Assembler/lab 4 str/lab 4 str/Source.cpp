#include <iostream>
#include <Windows.h>
using namespace std;

int main() {
	SetConsoleCP(1251);
	SetConsoleOutputCP(1251);
	char nstr[256];
	char sogl[] = ".БбВвГгДдЖжЗзКкЛлМмНнПпРрСсТтФфХхЦцЧчШшЩщЪъЬь";
	char pred;
	int newlen = 0;
	char ctr[256];
	cout << "Введите строку\n";
	cin.getline(ctr, 256);
	_asm {
			LEA ebx, ctr								// строку в ebx;
			XOR ah, ah									// обнулить ah;
			XOR edx, edx								// обнулить edx; 
			_cikl :										// цикл проверки всех букв;
				CMP [ebx + edx], 0						// если конец строки, то
				JE _end									// переместиться в _end
				MOV ecx, 44								// длину строки согласных в ecx;
				_comp :									// цикл проверки буквы на согласность;
					MOV al, byte ptr sogl[ecx]			// согласную в al;
					CMP [ebx + edx] ,al					// если буква согласная, то
					JE _sum								// переместиться в _sum,
					LOOP _comp							
					CMP ah, 0							// если не согласная, то, если перед ней не шёл подсчёт согласных (ah=0),
					JE _push							// переместиться в _push;
					DEC edx								// если перед ней считались согласные, то
					JMP _write							// записать количество согласных в метке _write
				_sum:							
					CMP ah, 0							// если это первая согласная, то
					JE _add								// переместиться в _add,
					MOV al, pred						// если нет, то
					CMP [ebx+edx], al					// сравнить её с предыдущей согласной
					JNE _write							// если не равна, то переместиться в _write
				_add:
					MOV al, [ebx+edx]					// букву а al;
					MOV pred, al						// сохранить её для поиска одинаковых;
					INC ah								// увеличить количество согласных;
					INC edx								// увеличить индекс сравниваемой буквы;
					JMP _cikl							// вернуться к проверке следующей
				_write:
					CMP ah,1							// если согласная была только одна, то
					JG _write2							// переместиться в _write2,
					MOV ecx, newlen						// если нет, то новую длину строки в ecx;
					MOV al, pred						// повторяющуюся букву в al;
					MOV nstr[ecx], al					// записать её в строку;
					XOR ah,ah							// обнулить счетчик повторяющихся букв;
					INC ecx								// увеличить новую длину;
					MOV newlen, ecx						// сохранить её;
					CMP [ebx + edx], 0					// ещё одна проверка на конец строки, нужна, если строка заканчивается одинаковыми согласными
					JE _end					
					MOV al, pred						
					CMP [ebx + edx], al					// если символ прерывания цикла не равен повторяющимся, то увеличивать индекс не нужно
					JNE _back					
					INC edx
					JMP _cikl
				_write2:
					JMP _chislo1						// переместиться в _chislo 1
				_backToWrite:
					XOR ah,ah							// обнулить счетчик повторений
					INC ecx								// увеличить новую длину
					MOV newlen, ecx
					CMP [ebx + edx], 0					// проверка на конец строки
					JE _end
					MOV al, pred
					CMP[ebx + edx], al					
					JNE _back							// если символ прерывания цикла не равен повторяющимся, то увеличивать индекс не нужно
					INC edx
					JMP _cikl
				_back:
					MOV pred, 0							// очистить предыдущий символ
					JMP _cikl							// вернуться в _cikl
				_push:
					MOV ecx,newlen						
					MOV al, [ebx+edx]
					MOV nstr[ecx],al					// запись гласной буквы в строку
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
				JE _end2								// по окончании переписывания, переместиться в конец программы
				MOV al, nstr[ecx-1]						// переписывание из буферной строки в исходную
				MOV [ebx+ecx-1], al
				DEC ecx
				JMP _endcikl
			_chislo1:										// начало записи числа повторений в строку;
				MOV ecx, newlen								// новую длину строки в ecx;
				CMP ah,	10									// если число больше 10, то 
				JGE _chislo2								// переместиться в _chislo2
				MOV al, '0'
				ADD al, ah									// если меньше, то записать число в сроку
				MOV nstr[ecx], al
				JMP _backToWrite							// и вернуться в _backToWrite
			_chislo2:				
				CMP ah, 100									// если число больше 100, то
				JGE _chislo3								// переместиться в _chislo 3
				PUSH ebx			
				PUSH edx
				MOV al,ah
				CBW
				MOV bl, 10
				DIV bl
				ADD al, '0'									// запись в строку числа, состоящего из 2 цифр
				ADD ah, '0'
				MOV nstr[ecx],al
				INC ecx
				MOV nstr[ecx], ah
				POP edx
				POP ebx
				JMP _backToWrite							// вернуться в _backToWrite
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
				DIV bl										// запись в строку числа, состоящего из 3 цифр
				ADD al, '0'
				ADD ah, '0'
				MOV nstr[ecx], al
				INC ecx
				MOV nstr[ecx], ah
				INC ecx
				POP edx
				POP ebx
				JMP _backToWrite							// вернуться в _backToWrite
		_end2:
	}
	cout << "Все повторяющиеся согласные заменены на количество их повторений:\n";
	cout << ctr << endl;
	system("pause");
	return 0;
}