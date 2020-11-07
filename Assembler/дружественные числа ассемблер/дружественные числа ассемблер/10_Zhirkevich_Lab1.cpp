#include <iostream>
using namespace std;
int main()
{
	setlocale(0, "rus");
	int n, k, j = 1, sum1 = 0, sum2, mid, temp;
	bool found = false;
	cout << "Введите  начало и конец интервала для поиска дружетсвенных чисел: ";
	cin >> n >> k;
	_asm
	{
		mov ecx, n // ecx - счетчик от n до к
		MOV edi, 2 // всегда число 2 для поиска половины
		first:
				INC ecx			// увеличить счётчик;
				MOV ebx, 0		// обнулить сумму делителей;
				MOV eax, ecx	// счётчик в eax;
				MOV edx, 0		// обнулить edx для деления;
				IDIV edi		// найти половину от счётчика;
				MOV mid, eax	// половину в mid;
				MOV esi, 1		// внутренний счётчик;
			frstsum:
				CMP esi, mid	// если внутренний счётчик больше половины внешнего
				JG second		// переместиться в second;
				MOV eax, ecx	// счётчик в eax;
				MOV edx, 0		// обнулить edx для деления;
				IDIV esi		// если при делении внешнего счётчика на внутренний
				CMP edx, 0		// остаток равен нулю (поделилось)
				JE firstadd		// переместиться в firstadd;
				INC esi			// если нет, увеличить счетчик;
				JMP frstsum		// переместиться в frstsum
			firstadd:
				ADD ebx, esi	// добавить внутренний счётчик к сумме делителей первого;
				INC esi			// увеличить внутренний счётчик
				JMP frstsum		// переместиться в frstsum
		second:
				MOV sum1, ebx	// сохранить сумму делителей первого числа в sum1;
				MOV ebx, 0		// обнулить сумму делителей;
				MOV eax, sum1	// сумму делителей первого в eax;
				MOV edx, 0		// обнулить edx для деления;
				IDIV edi		// поделить сумму делителей первого на 2;
				MOV mid, eax	// сохранить середину в mid;
				MOV esi, 1		// запустить внутренний счётчик от 1;
			scndsum:
				CMP esi, mid	// если внутренний счётчик больше mid
				JG comp1		// то переместиться в comp1;
				MOV eax, sum1	// если нет, то сумму делителей первого в eax;
				MOV edx, 0		// обнулить edx для деления;
				IDIV esi		// поделить сумму делителей первого на счётчик;
				CMP edx, 0		// если поделилось нацело
				JE secondadd	// переместиться в secondadd;
				INC esi			// увеличить внутренний счётчик;
				JMP scndsum		// переместиться в scndsum;
			secondadd:
				ADD ebx, esi	// добавить к сумме делителей второго внутренний счетчик;
				INC esi			// увеличить внутренний счётчик;
				JMP scndsum		// переместиться в scndsum;
		comp1:
			MOV sum2, ebx	// сохранить сумму делителей второго в sum2;
			CMP ecx, sum2	// если первое число равно сумме делителей второго
			JE comp2		// переместиться в comp2;
			JMP stop		// если нет, то переместиться в stop;
		comp2:
			MOV j, ecx		// первое число в j;
			CMP ecx, sum1	// если два числа не равны
			JNE print		// числа найдены -> распечатать
		repeat:
			MOV ecx, sum1	// продолжить поиск с последнего найденного числа
			MOV edi, 2		//	восстановление константной двойки;
			JMP stop		//	переместитсья в stop;
		stop:
			CMP ecx, k		// если счётчик меньше или равен заданному диапазону
			JLE first		// переместиться в first;
			// если счётчик вышел за диапазон, завершение программы;
	}


	if (!found)
		cout << "Дружественные числа не найдены\n";
	system("pause");
	return 0;

	_asm
	{
		print:
	}

	found = true;
	cout << j << " и " << sum1 << endl;

	_asm
	{
		JMP repeat
	}
}