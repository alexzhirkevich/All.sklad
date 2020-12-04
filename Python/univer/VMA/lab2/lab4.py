from matrix import ItPowMethod,ScalMulMethod, Matrix,Vector

if __name__ == "__main__":
    
    M = ItPowMethod(7)
    first = M.find(1e-6)
    print("*** Итерационно-степенной метод ***")
    print(f"Начальный вектор: {first[3]}")
    print(f"Максимальное по модулю собственное значение: {first[0]}")
    print(f"Собственный вектор: {first[1]}")
    r = Vector.sub(Matrix.mulVector(M.getMatrix(),first[1]),
     Vector.mulFloat(first[1], first[0])) 
    print(f"Вектор r: {r}")
    print(f"Количество итераций: {first[2]}")

    print()

    M = ScalMulMethod(7)
    second = M.find(1e-6)
    print("*** Метод скалярных произведений ***")
    print(f"Начальный вектор: {second[3]}")
    print(f"Максимальное по модулю собственное значение: {second[0]}") 
    print(f"Собственный вектор: {second[1]}")
    r = Vector.sub(Matrix.mulVector(M.getMatrix(),second[1]),
     Vector.mulFloat(second[1], second[0]))
    print(f"Вектор r: {r}")
    print(f"Количество итераций: {second[2]}")