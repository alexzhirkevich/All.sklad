import random
from functools import reduce
from decimal import *
import time
from abc import abstractclassmethod,ABC, abstractmethod
from typing import List

class Vector:
    @staticmethod
    def sub(a:List[Decimal],b:List[Decimal]) -> List[Decimal]:
        if (len(a)!=len(b)):
            raise Exception("Vector sub error: wrong sizes")
        return [i-j for i,j in zip(a,b)]

    @staticmethod  
    def sum(a:List[Decimal],b:List[Decimal]) -> List[Decimal]:
        if (len(a)!=len(b)):
            raise Exception("Vector sub error: wrong sizes")
        return [i+j for i,j in zip(a,b)]

    @staticmethod
    def mulFloat(vec:List, val:float) -> List:
        return [el*val for el in vec]

    @staticmethod
    def divFloat(vec:List, val:float) -> List:
        return [el/val for el in vec]


    @staticmethod
    def norm(vec:List) -> float:
        return max([abs(el) for el in vec])

    @staticmethod
    def normalize(vec:List) -> List:
        return Vector.divFloat(vec, Vector.norm(vec))
    
    @staticmethod
    def print(vector:List[Decimal]) -> None:
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
    def _gauss(matrix:List[List[Decimal]], vector:List[Decimal] = None) -> (List[List[Decimal]], int, List[Decimal]):
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
    def _solveSystem(matrix:List[List[Decimal]],f:List[Decimal]) -> List[Decimal]:
        
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
    def transpose(matrix:List[List[Decimal]])->List[List[Decimal]]:
        return [[matrix[j][i] for j in range(len(matrix[i]))] for i in range(len(matrix))]

    #определитель
    @staticmethod
    def det(matrix:List[List[Decimal]])->Decimal:
        tMatrix, replaces = Matrix._gauss(matrix)
        return reduce(lambda x,y:x*y,[tMatrix[i][i] for i in range(len(tMatrix))]) * ((-1) ** int(replaces % 2 != 0))

    #поиск обратной матрицы 
    @staticmethod
    def inverse(matrix:List[List[Decimal]]) -> List[List[Decimal]]:
        return Matrix.transpose([Matrix._solveSystem(matrix, line) for line in 
            [[int(i==j) for j in range(len(matrix[i]))] for i in range(len(matrix))]])

    #умножение матриц
    @staticmethod
    def mul(a:List[List[Decimal]],b:List[List[Decimal]]) -> List[List[Decimal]]:
        if (len(a[0])!=len(b)):
            raise Exception("Matrix mul error: wrong sizes")
        return [[(sum(a[i][k]*b[k][j] for k in range(len(a)))) for j in range(len(a[i]))] for i in range(len(a))]

    #умножение матрицы на столбец
    @staticmethod
    def mulVector(matrix:List[List[Decimal]], vector:List[Decimal])->List[Decimal]:
        if (len(matrix[0])!=len(vector)):
            raise Exception("Matrix mulVector error: wrong sizes")
        return [(sum([matrix[i][j]*vector[j] for j in range(len(vector))])) for i in range(len(matrix))]       

    @staticmethod
    def mulFloat(a:List[List], b:float) -> List[List]:
        return [[el*b for el in line] for line in a ]

    @staticmethod
    def divFloat(a:List[List], b:float) -> List[List]:
        if (b == 0):
            raise DivisionByZero()
        return [[el/b for el in line] for line in a ]

    #сумма матриц
    @staticmethod
    def sum(a:List[List[Decimal]],b:List[List[Decimal]]) -> List[List[Decimal]]:
        if (len(a)!= len(b) or len(a[0]) != len(b[0])): 
            raise Exception("Matrix sub error: wrond size")   
        return [[a[i][j]+b[i][j] for j in range(len(a[i]))]for i in range(len(a))]

    #разность матриц
    @staticmethod
    def sub(a:List[List[Decimal]], b:List[List[Decimal]]) -> List[List[Decimal]]:
        if (len(a)!= len(b) or len(a[0]) != len(b[0])): 
            raise Exception("Matrix sub error: wrond size")   
        return [[a[i][j]-b[i][j] for j in range(len(a[i]))]for i in range(len(a))]
    
    #печать матрицы      
    @staticmethod
    def print(matrix:List[List[Decimal]] ,precision:int = 16) -> None: 
        for line in matrix:
            for item in line:
                print(str(round(item,precision))+'\t', end = '')
            print('\n')

class SLE:
    #инициализация СЛУ
    def __init__(self,n:int)->None:
        super()
        #случайное задание матрицы A 
        self._matrix = [[Decimal(str(round(-n+random.random()*2*n,2))) for j in range(n)] for i in range(n)]
        #решения Х
        self._solution = [Decimal(str(round(-n+random.random()*2*n,2))) for i in range(n)]
        #нахождение f
        self._result = Matrix.mulVector(self._matrix,self._solution)


    def _countResult(self) -> None:
        self._result = Matrix.mulVector(self._matrix, self._solution)

    #A
    def getMatrix(self)->List[List[Decimal]]:
        return [(list(i)) for i in self._matrix]
    
    #x
    def getSolution(self)->List[Decimal]:
        return list(self._solution)
    
    #f
    def getF(self)->List[Decimal]:
        return list(self._result)

    #решение системы
    def solve(self,f:List[Decimal] = None) ->List[Decimal]:
        if (f == None):
            return Matrix._solveSystem(self._matrix,self._result)
        else:
            return Matrix._solveSystem(self._matrix,f)

class TridiagonalSLE(SLE):
  
    #создание трехдиагональной матрицы с диоганальным преобладанием
    def __init__(self,n:int)->None:

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

    
    def solve(self) -> List[Decimal]:

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
    def __init__(self, n:int = 10, eps:float = 2e-5) -> None:
        SLE.__init__(self,n)
        self._matrix = [[float(el.to_eng_string()) for el in line] for line in self._matrix]
        self._solution = [float(el) for el in self._solution]
        self._countResult()

    
    def getEps(self):
        return self._eps
    
    def setEps(self,eps):
        self._eps = eps

    @abstractmethod
    def solve(self, f=None):
        pass
    
class SimpleIteration(Iterative):

    def __init__(self,copyFrom:SLE = None, n:int = 10, eps:float = 2e-5) -> None:
        if (not copyFrom):
            Iterative.__init__(self,n,eps)
            self._matrix  = Matrix.mul(Matrix.transpose(self._matrix), self._matrix)
            self._result = Matrix.mulVector(Matrix.transpose(self._matrix),self._result)
            self._eps = eps
            self._countResult()
        else :
            self._matrix = [list(line) for line in copyFrom._matrix]
            self._result = list(copyFrom._result)
            self._solution = list(copyFrom._solution)
            if (copyFrom._eps):
                self._eps = copyFrom._eps  

    def solve(self) -> (List[float], int, float):

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
    def __init__(self,copyFrom=None, n:int=10, eps:float=1e-5) -> None:
        if (not copyFrom):
            Iterative.__init__(self, n,eps)
            self._matrix  = Matrix.mul(Matrix.transpose(self._matrix), self._matrix)
            self._countResult()
        else:
            self._matrix = [list(line) for line in copyFrom._matrix]
            self._result = list(copyFrom._result)
            self._solution = list(copyFrom._solution)
            if (copyFrom._eps):
                self._eps = copyFrom._eps  

    def solve(self, w:float=1.0) -> (List[float], int, float,):
        tResult = [self._result[i] / self._matrix[i][i] for i in range(len(self._matrix))]
        result = [0 for i in range(len(self._matrix))]
        iterations = 0
            
        exitValue = float('inf')
        while(exitValue >= self._eps):
            result = list(tResult)
            for i in range(len(self._matrix)):
                a = w * Vector.sub(Matrix.mulVector(self._matrix, tResult),self._result)[i] / self._matrix[i][i]
                eXa = [0 if i!= j else a for j in range(len(self._matrix))]
                tResult = Vector.sub(tResult, eXa)
            
            exitValue = max([abs(i) for i in Vector.sub(tResult, result)])
            iterations +=1

        return tResult, iterations, exitValue
    

class Eigen(ABC):

    def __init__(self, var:int) -> None:
        B =   [[1.342, 0.432, 0.599, 0.202, 0.603, 0.202],
                    [0.432, 1.342, 0.256, 0.599, 0.204, 0.304],
                    [0.599, 0.256, 1.342, 0.532, 0.101, 0.506],
                    [0.202, 0.599, 0.532, 1.342, 0.106, 0.311],
                    [0.603, 0.204, 0.101, 0.106, 1.342, 0.102],
                    [0.202, 0.304, 0.506, 0.311, 0.102, 1.342]]

        C =   [[0.05, 0, 0, 0, 0, 0],
                    [0, 0.03, 0, 0, 0, 0],
                    [0, 0, 0.02, 0, 0, 0],
                    [0, 0, 0, 0.04, 0, 0],
                    [0, 0, 0, 0, 0.06, 0],
                    [0, 0, 0, 0, 0, 0.07]]

        self._A = Matrix.sum(B, Matrix.mulFloat(C, var))

    def getMatrix(self) -> List:
        return [list(line) for line in self._A]

    @abstractmethod
    def find(self) -> (float, List, int, List):
        """
        Counts maximum absolute eigenvalue and eigenvector

        Returns:

            float - max absolute eigenvalue
            list - eigenvector for this eigenvalue 
            int - iterations count
            lsit - initial approximation vector
        """
        pass

        
#Итерационно-степенной метод
class ItPowMethod(Eigen):

    def find(self,eps:float) -> (float, List, int, List):

        delta = float("inf")
        startVector = [random.randrange(1,1000)/1000 for _ in self._A]

        y0 = Vector.normalize(startVector)
        Lambda0 = [random.randrange(0,10)/10 for _ in self._A]
        iterations = 0

        while (delta > eps):
            y = Matrix.mulVector(self._A,y0)

            Lambda = [el1/el2 for el1,el2 in zip(y,y0)]
            delta = abs(max(Vector.sub(Lambda0,Lambda)))
            y0 = Vector.normalize(y)
            Lambda0 = Lambda
            iterations+=1

        return sum(Lambda)/len(Lambda), y0, iterations, startVector
        

#Метод скалярных произведений
class ScalMulMethod(Eigen):

    def find(self,eps:float) -> (float, List, int, List):

        delta = float("inf")
        startVector = [random.randrange(1,1000)/1000 for _ in self._A]

        y0 = Vector.normalize(startVector)
        Lambda0 = random.randrange(1,1000)/1000
        iterations = 0

        while (delta > eps):
            y = Matrix.mulVector(self._A,y0)
            Lambda = (sum([el1*el2 for el1,el2 in zip(y,y0)]) /
                sum([el1*el2 for el1,el2 in zip(y0,y0)]))
            delta = abs(Lambda - Lambda0)
            y0 = Vector.normalize(y)
            Lambda0 = Lambda
            iterations+=1

        return Lambda, y0, iterations, startVector
        

            



                
