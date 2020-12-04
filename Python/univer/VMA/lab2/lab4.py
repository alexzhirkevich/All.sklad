from matrix import ItStepMethod,ScalMulMethod

if __name__ == "__main__":
    
    first = ItStepMethod(7,1e-6).find()
    print("*** Итерационно-степенной метод ***")
    print(f"Начальный вектор: {first[3]}")
    print(f"Максимальное по модулю собственное значение: {first[0]}")
    print(f"Собственный вектор: {first[1]}")
    print(f"Количество итераций: {first[2]}")

    print()

    second = ScalMulMethod(7,1e-6).find()
    print("*** Метод скалярных произведений ***")
    print(f"Начальный вектор: {second[3]}")
    print(f"Максимальное по модулю собственное значение: {second[0]}")
    print(f"Собственный вектор: {second[1]}")
    print(f"Количество итераций: {second[2]}")