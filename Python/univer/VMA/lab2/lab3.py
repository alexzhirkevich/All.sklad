from matrix import *


#тестирование
if __name__ == "__main__":

        first = SimpleIteration(n=10, eps =1e-5)
        print("Система уравнений:")
        Matrix.print(first.getMatrix(),precision = 2)
        print("Точное решение: ", end = '')
        Vector.print(first.getSolution())
    
        print("\n*****МЕТОД ПРОСТЫХ ИТЕРАЦИЙ*****\n")
        print("Решение: ", end = '')
        Vector.print(first.solve()[0])
        print("Максимум-норма погрешности:",end = '')
        print(max([abs(i) for i in Vector.sub(first.getSolution(),first.solve()[0])]))

        print("\n*****МЕТОД РЕЛАКСАЦИИ*****\n")
        second = Relaxation(copyFrom = first)
        wList = [0.2, 0.5, 0.8, 1.0, 1.3, 1.5, 1.8]
        for w in wList:
            solution = second.solve(w)
            print("| w = {} | {} | {}".format(w, solution[1], solution[2]))
        it1 = second.solve(0.01)[1]
        for i in range(1,200):
            it2 = second.solve(i/100)[1]
            if it2 > it1 or i == 199:
                print("Наилучший результат при w = " +  str((i-1)/100) + ": " + str(it1) + " итераций")
                print("Решение: ", end = '')
                Vector.print(second.solve()[0])
                break
            it1 = it2
        
