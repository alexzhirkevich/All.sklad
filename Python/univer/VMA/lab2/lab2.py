from matrix import *


#тестирование
if __name__ == "__main__":
    try:

        tsle = TridiagonalSLE(10)

        print("Система уравнений:")
        tsle.print()
        
        print("Точное решение: ", end = '')
        print([i.to_eng_string() for i in tsle.getSolution()])
        
        print("Решение: ", end = '')
        print([i.to_eng_string() for i in tsle.solve()])

        print("Максимум-норма невязки: ", end = '')
        print(max([abs(i) for i in Vector.sub(Matrix.mulVector(tsle.getMatrix(),tsle.solve()),tsle.getF())]))

        print("Максимум-норма погрешности: ", end = '')
        print(max([abs(i) for i in Vector.sub(tsle.getSolution(),tsle.solve())]))
    
    except Exception as e:
        print(repr(e))