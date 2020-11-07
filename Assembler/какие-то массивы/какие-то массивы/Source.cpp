#include <iostream>
#include <time.h>
using namespace std;
int main()
{ //статические массивы
	int a[] = { 4,6,8,10 };
	int x, y, pr;
	short y1;
	short a1[] = { 4,6,8,10 };
	//динамические массивы
	int n;
	cout << "size off array = ";
	cin >> n;
	int* b = new int[n];
	srand(time(0));
	for (int i = 0; i < n; i++)
		b[i] = rand() % 100;
	for (int i = 0; i < n; i++)
		cout << b[i] << " ";
	cout << endl;
	//сумма элементов динамического массива типа int
	_asm {
		mov ecx, n
		mov ebx, b
		xor eax, eax
		//xor esi,esi
		cikla :
		add eax, [ebx]
			add ebx, 4
			loop cikla
			mov y, eax
	}
	cout << "sum=" << y << endl;
	system("Pause");
	return 0;
}