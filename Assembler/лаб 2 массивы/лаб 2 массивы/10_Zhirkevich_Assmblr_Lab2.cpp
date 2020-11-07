#include <iostream>
#include <vector>
using namespace std;
int main()
{
	setlocale(LC_ALL, "Rus");
	vector<int> vec;
	cout << "Введите эл-ты массива. Ctrl+Z для завершения\n";
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
		cout << "Нет элементов";
		system("pause");
		return 0;
	}
	cout << "Числа в порядке убывания частоты встречаемости:\n";
	_asm
	{
		MOV esi, 0
		MOV edx, 0
		MOV ebx, 0
		MOV edi, 0
		MOV eax, arr
		
	cikl:
			INC esi
			CMP esi, n						// если подсчитали максимум
			JGE sort						// переместиться к сортировке;
			DEC esi
			MOV edx, [eax +esi* 4]			// элемент массива в eax;
			MOV ecx, n						// длину в ecx
		
		cnt:
			DEC ecx
			CMP edx, [eax +ecx * 4]			// сравнить элемент со всеми по очереди;
			JE nubm							// если равен переместиться в numb,
			JMP tst							// если нет то в tst;
		
	nubm:
		INC ebx								// увеличить счетчик количества одинаковых элементов;
		JMP tst								// переместиться в tst;
	
	tst:
		cmp ecx, 0 							// цикл поиска наиболее часто встречаемогося
		JG cnt
		CMP ebx, maxcount					// если равны, то сравнить количество сравниваемого элемента и максимальное;
		JG setmax							// если больше, то переместиться в setmax;

		continuee:
			MOV ebx, 0						// обнулить счетчик количества;
			INC esi							// увеличить номер сравниваемого элемента;
			JMP cikl						// переместиться в cikl;

	setmax :
			MOV maxcount, ebx				// сохранить макимальное количество
			MOV edi, [eax +esi * 4]			// сохранить элемент, которого больше всего;
			JMP continuee					// переместиться в continue;
		
	sort:
		MOV maxcount, 0						// обнулить максимум;
		MOV ebx, pos						// в edx поместить текущую позицию, с которой будем размещать элементы;
		MOV ecx, pos						// счетчик, начиная с позиции
		INC ebx
		CMP ebx, n							// если позиция равна последнему
		JGE stop							// массив отсортирован - переместиться в stop;
		DEC ebx
	check:
		CMP ecx, n							// сравнить текущий номер с длинной;
		JGE repeat							// если больше, переместиться в repeat;
		MOV edx, [eax +ecx * 4]				// в eax элемент, начиная с позиции;
		CMP edx,edi							// сравнить самый частовстречаемыйся с текущим
		JE change							// если равны, то переместиться в change,
		INC ecx								// если нет, увеличить счетчик;
		JMP check							// вернуться в check;
	change:
		MOV edx, [eax +ecx * 4]
		PUSH edx
		MOV edx, [eax +ebx * 4]				// поменять местами элементы с индексами ecx и edx
		MOV [eax +ecx * 4], edx				
		POP edx
		MOV [eax +ebx * 4], edx
		INC ebx								// увеличить номер позиции;
		INC ecx								// увеличить счетчик
		JMP check							// вернуться в check;

	repeat:
		MOV pos, ebx						// сохранить последнюю позицию
		MOV esi, pos						// начать искать наиболее часто встречаемыйся с нужной позиции
		MOV ebx, 0							// обнулить ebx
		JMP cikl							// вернуться в начало cikl
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