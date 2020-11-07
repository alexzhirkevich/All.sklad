// простое консольное приложение
import java.util.*;

// объявление класса
class Prg_01 {

    public static void main( String[] args )
    {
        // Объект класса Scanner для ввода данных
        Scanner in = new Scanner( System.in );

        // Элементарный ввод-вывод
        // Построчное чтение строки:
        System.out.print("Enter your name: ");
        String name = in.nextLine();
        System.out.println("Hello " +  name);

        // целые
        System.out.print("Enter integer value: ");
        int i = in.nextInt();

        // действительные
        System.out.print("Enter floating point number (error with wrong delimiter!) : ");
        double d = in.nextDouble();  // Исключение при неверном разделителе дробной части
        
	// Вывод числовых данных:
        System.out.println("You has enter: " + i + " and " + d );
        in.close();
    }
}

