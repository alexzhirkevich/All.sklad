import random
from functools import reduce
from decimal import *
import time
from abc import abstractclassmethod,ABC

class Vector:
    @staticmethod
    def sub(a,b):
        if (len(a)!=len(b)):
            raise Exception("Vector sub error: wrong sizes")
        return [i-j for i,j in zip(a,b)]

    @staticmethod  
    def sum(a,b):
        if (len(a)!=len(b)):
            raise Exception("Vector sub error: wrong sizes")
        return [i+j for i,j in zip(a,b)]
    
    @staticmethod
    def print(vector):
        if (vector and len(vector)>0):
            if (vector[0].__class__== Decimal):
                print([i.to_eng_string() for i in vector])
            else:
                print(vector)
        else:
            print(None)

class Matrix:
   
    #метод гаусса с выбором главного эл-та по столбцу
    @staticmethod
    def _gauss(matrix, vector=None):
        tMatrix = [list(i) for i in matrix]
        if (vector != None):
            tResult = list(vector)
        replaces = 0
        for i in range(len(tMatrix)-1):
            maxnumber = i
            maxvalue = tMatrix[i][i]
            # выбор максимального элемента
            for k in range(i+1,len(tMatrix)):
                if abs(tMatrix[k][i]) > abs(maxvalue):
                    maxvalue = tMatrix[k][i]
                    maxnumber = k
            #перестановка строки с макс. элементом
            if (maxnumber != i):
                tMatrix[maxnumber], tMatrix[i] = list(tMatrix[i]), list(tMatrix[maxnumber])
                if (vector != None):
                    tResult[maxnumber], tResult[i] = tResult[i], tResult[maxnumber]
                replaces+=1
            for j in range(i,len(tMatrix)-1):
            #преобразование строки
                for k in range(i,len(tMatrix)-1):
                    if (tMatrix[i][i] != 0):
                        tMatrix[j+1][k+1] -= tMatrix[i][k+1] * tMatrix[j+1][i] / tMatrix[i][i]
                    else:
                        raise Exception("Impossible to use Gauss-method: division by 0")
                if (vector != None):
                    tResult[j+1] -= tResult[i] * tMatrix[j+1][i] / tMatrix[i][i]

                for k in range(0,i+1):
                    tMatrix[j+1][k]=0
        #возвращает преобразованную матрицу, количество перестановок и вектор F
        if (vector != None):
            return (tMatrix, replaces, tResult)
        else:
            return (tMatrix, replaces)
    
    #решение СЛУ
    @staticmethod
    def _solveSystem(matrix,f):
        
        if Matrix.det(matrix)==0:
            raise Exception("Impossible to solve: det = 0")

        result = []
        tMatrix,replaces,tResult = Matrix._gauss(matrix,f)
        
        #обратный ход метода гаусса
        for i in reversed(range(len(tMatrix))):
            x = tResult[i]
            k=0
            for j in reversed(range(1,len(tMatrix))):
                if (tMatrix[i][j-1]==0):
                    break
                x-=result[len(result)-k-1]*tMatrix[i][j]
                k+=1
            result.insert(0,x/tMatrix[i][i])

        return result
    
    #транспонирование
    @staticmethod
    def transpose(matrix):
        return [[matrix[j][i] for j in range(len(matrix[i]))] for i in range(len(matrix))]

    #определитель
    @staticmethod
    def det(matrix):
        tMatrix, replaces = Matrix._gauss(matrix)
        return reduce(lambda x,y:x*y,[tMatrix[i][i] for i in range(len(tMatrix))]) * ((-1) ** int(replaces % 2 != 0))

    #поиск обратной матрицы 
    @staticmethod
    def inverse(matrix):
        return Matrix.transpose([Matrix._solveSystem(matrix, line) for line in 
            [[int(i==j) for j in range(len(matrix[i]))] for i in range(len(matrix))]])

    #умножение матриц
    @staticmethod
    def mul(a,b):
        if (len(a[0])!=len(b)):
            raise Exception("Matrix mul error: wrong sizes")
        return [[(sum(a[i][k]*b[k][j] for k in range(len(a)))) for j in range(len(a[i]))] for i in range(len(a))]

    #умножение матрицы на столбец
    @staticmethod
    def mulVector(matrix,vector):
        if (len(matrix[0])!=len(vector)):
            raise Exception("Matrix mulVector error: wrong sizes")
        return [(sum([matrix[i][j]*vector[j] for j in range(len(vector))])) for i in range(len(matrix))]               
    
    #сумма матриц
    @staticmethod
    def sum(a,b):
        if (len(a)!= len(b) or len(a[0]) != len(b[0])): 
            raise Exception("Matrix sub error: wrond size")   
        return [[a[i][j]+b[i][j] for j in range(len(a[i]))]for i in range(len(a))]

    #разность матриц
    @staticmethod
    def sub(a,b):
        if (len(a)!= len(b) or len(a[0]) != len(b[0])): 
            raise Exception("Matrix sub error: wrond size")   
        return [[a[i][j]-b[i][j] for j in range(len(a[i]))]for i in range(len(a))]
    
    #печать матрицы      
    @staticmethod
    def print(matrix,precision = 16): 
        for line in matrix:
            for item in line:
                print(str(round(item,precision))+'\t', end = '')
            print('\n')

class SLE:
    #инициализация СЛУ
    def __init__(self,n):
        super()
        #случайное задание матрицы A 
        self._matrix = [[Decimal(str(round(-n+random.random()*2*n,2))) for j in range(n)] for i in range(n)]
        #решения Х
        self._solution = [Decimal(str(round(-n+random.random()*2*n,2))) for i in range(n)]
        #нахождение f
        self._result = Matrix.mulVector(self._matrix,self._solution)


    def _countResult(self):
        self._result = Matrix.mulVector(self._matrix, self._solution)

    #A
    def getMatrix(self):
        return [(list(i)) for i in self._matrix]
    
    #x
    def getSolution(self):
        return list(self._solution)
    
    #f
    def getF(self):
        return list(self._result)

    #решение системы
    def solve(self,f = None):
        if (f == None):
            return Matrix._solveSystem(self._matrix,self._result)
        else:
            return Matrix._solveSystem(self._matrix,f)

class TridiagonalSLE(SLE):
  
    #создание трехдиагональной матрицы с диоганальным преобладанием
    def __init__(self,n):

        self._a = [Decimal(str(round(-n+random.random()*2*n,2))) for _ in range(n)]
        self._c = [Decimal(str(round(-n+random.random()*2*n,2))) for _ in range(n-1)]
        self._b = [self._c[0]]
        for i in range(1,n-1):
            self._b.append(abs(self._a[i])+abs(self._c[i]))
        self._b.append(self._a[n-1]+1)
        

        self._solution = [Decimal(str(round(-n+random.random()*2*n,2))) for _ in range(n)]
        self._matrix=[[Decimal("0") for _ in range(n)] for __ in range(n)]

        for i in range(n-1):
            self._matrix[i][i]=self._b[i]
            self._matrix[i][i+1]= self._c[i]
            self._matrix[i+1][i] = self._a[i+1]
        self._matrix[n-1][n-1] = self._b[n-1]

        self._countResult()

    
    def solve(self):

        #прямой ход
        y = [self._b[0]]
        alpha = [(-self._c[0])/y[0]]
        beta = [self._result[0]/y[0]]
        n = len(self._b)

        for i in range(1,n-1):
            y.append(self._b[i]+self._a[i]*alpha[i-1])
            alpha.append((-self._c[i])/y[i])
            beta.append((self._result[i]- self._a[i]*beta[i-1])/y[i])
        
        y.append(self._b[n-1] + self._a[n-1]*alpha[n-2])
        beta.append((self._result[n-1] - self._a[n-1]*beta[n-2])/y[n-1])

        #обратный ход
        result = [beta[n-1]]

        for i in reversed(range(0,n-1)):
            result.insert(0,alpha[i]*result[0]+beta[i])
        
        return result

class Iterative(ABC, SLE):
    def __init__(self,n,eps = 1e-5):
        SLE.__init__(self,n)
        self._eps = eps
    
    def getEps(self):
        return self._eps
    
    def setEps(self,eps):
        self._eps = eps

    @abstractclassmethod
    def solve(cls, f=None):
        pass
    
class SimpleIteration(Iterative):

    def __init__(self,n,eps = 1e-5):
        Iterative.__init__(self,n,eps)

        for i in range(len(self._matrix)):
           self._matrix[i][i] = sum([abs(a) for a in self._matrix[i]])
        self._eps = eps

        self._countResult()

    def solve(self):
        """
        return:\n
                result (list) - [result of solving]
                iterations (int) - number of iterations
                exitValue (Decimal) - first <eps iteration value
        """

        iterations = 0
        exitValue = 0
        result = [self._result[i] / self._matrix[i][i] for i in range(len(self._matrix))]
        
        while (True):
            tResult = [self._result[i] / self._matrix[i][i] for i in range(len(self._matrix))]
            for i in range(len(self._matrix)):
                for j in range(len(self._matrix)):
                    if (i==j):
                        continue
                    tResult[i] -= self._matrix[i][j] / self._matrix[i][i] * result[j]
            iterations+=1
            exitValue = max([abs(i) for i in Vector.sub(tResult,result)])
            if (exitValue < self._eps):
                result = tResult
                break
            result = list(tResult)
        
        return result, iterations, exitValue

class Relaxation(Iterative):
    def __init__(self,n,w,eps=1e-5):
        Iterative.__init__(self, n,eps)
        self.__w = w
        self._matrix  = Matrix.mul(Matrix.transpose(self._matrix), self._matrix)
        self._countResult()

    def solve(self):
        pass