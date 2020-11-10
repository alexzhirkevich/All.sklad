from matrix import *


#тестирование
if __name__ == "__main__":
    
        tsle = SimpleIteration(3, 1e-5)

        print("Система уравнений:")
        Matrix.print(tsle.getMatrix(),precision = 2)
        
        print("Точное решение: ", end = '')
        Vector.print(tsle.getSolution())
        
        print("Решение: ", end = '')
        Vector.print(tsle.solve()[0])
        print(tsle.solve()[2])

        sle = Relaxation(copyFrom = tsle)

        Vector.print(sle.getSolution())
        Vector.print(sle.solve()[0])

        print("w = 0.2 | " + str(sle.solve(0.2)[1]) + " | " + str(sle.solve(0.2)[2]))
        print("w = 0.5 | " + str(sle.solve(0.5)[1]) + " | " + str(sle.solve(0.5)[2]))
        print("w = 0.8 | " + str(sle.solve(0.8)[1]) + " | " + str(sle.solve(0.8)[2]))
        print("w = 1.0 | " + str(sle.solve(1.0)[1]) + " | " + str(sle.solve(1.0)[2]))
        print("w = 1.3 | " + str(sle.solve(1.3)[1]) + " | " + str(sle.solve(1.3)[2]))
        print("w = 1.5 | " + str(sle.solve(1.5)[1]) + " | " + str(sle.solve(1.5)[2]))
        print("w = 1.8 | " + str(sle.solve(1.8)[1]) + " | " + str(sle.solve(1.8)[2]))

        it1 = sle.solve(0.01)[1]

        for i in range(1,200):
            it2 = sle.solve(i/100)[1]
            if it2 > it1:
                print("Best w: " +  str(i/100) + " | " + str(it2))
                break
            it1 = it2
        
