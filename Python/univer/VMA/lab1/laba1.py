from matrix import *

#тестирование программы
if __name__ == "__main__": 
    try:
        
        rle = RandomSLE(10)
        print("Система уравнений:")
        rle.print()
        print("\nX: " + str(rle.getSolution()))
        print('\nРешение системы: ' + str(rle.solve()))
        print('\nОбратная матрица:')
        Matrix.print(matrix = Matrix.inverse(rle.getMatrix()), precision = 2)
        print("Произведение обратной матрицы и исходной:")
        Matrix.print(Matrix.mul(Matrix.inverse(rle.getMatrix()),rle.getMatrix()), precision=2)
        print("Максимум-норма невязки: " +
            str(max([abs(i) for i in Vector.sub(Matrix.mulVector(rle.getMatrix(),rle.solve()),rle.getF())])))
        print("Максимум-норма погрешности: " + str(abs(max(Vector.sub(rle.solve(),rle.getSolution())))))
        print("Определитель: " + str(Matrix.det(rle.getMatrix())))


    except Exception as e:
        print(repr(e))


