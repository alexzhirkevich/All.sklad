from matrix import *


#тестирование
if __name__ == "__main__":
    try:
    
        tsle = SimpleIteration(3, 1e-5)

        print("Система уравнений:")
        Matrix.print(tsle.getMatrix(),precision = 2)
        
        print("Точное решение: ", end = '')
        Vector.print(tsle.getSolution())
        
        print("Решение: ", end = '')
        Vector.print(tsle.solve()[0])
        print(tsle.solve()[2].__class__)

        

    except Exception as e:
        print(repr(e.with_traceback))