from matrix import Matrix
from matrix import Vector
import math
from matrix import ScalMulMethod
from math import sin,cos,tan,atan

#  if (len(a[0])!=len(b)):
#             raise Exception("Matrix mul error: wrong sizes")
#         return [[(sum(a[i][k]*b[k][j] for k in range(len(a)))) for j in range(len(a[i]))] for i in range(len(a))]

def funcMatrixMul(a,b):
    if (len(a[0])!=len(b)):
        raise Exception("Matrix mul error: wrong sizes")
    result = [["" for _ in a] for __ in a]
    for i in range(len(a)):
        for j in range(len(a)):
            summaStr = ""
            summaInt = 0
            for k in range(len(a)):
                if a[i][k].isdigit() and b[k][j].isdigit():
                    summaInt+=int(a[i][k])*int(b[k][j])
                else:
                    summaStr = summaStr + " + " + a[i][k] + "*" + b[j][i]
            result[len(a)-i-1][len(a)-j-1] = str(summaInt) + summaStr 
    
    return result
        

a = [['1','2e','3'],['4','5a','6'],['7','8','9']]
b = [['9','8','7'],['6','5','4'],['3','2t','1']]

d = [[1,2,3],[4,5,6],[7,8,9]]
c = [[9,8,7],[6,5,4],[3,2,1]]

print(funcMatrixMul(a,b))

print(Matrix.mul(c,d))

# f = [4,8,9]

# x0 = [4,8,9]

# a= [[6,-2,2],[-2,5,0],[2,0,7]]

# r0 = (Vector.sub(Matrix.mulVector(a,x0),f))
# #print(f"r0 = {r0}")

# ch = Vector.scalMul(r0,r0)
# zn = Vector.scalMul(Matrix.mulVector(a,r0),r0)

# x1 = Vector.sub(x0,Vector.mulFloat(r0,ch/zn))
# #print(x1)

# r1 = r0 = (Vector.sub(Matrix.mulVector(a,x1),f))

# ch = Vector.scalMul(r1,r1)
# zn = Vector.scalMul(Matrix.mulVector(a,r1),r1)

# x2 = Vector.sub(x1,Vector.mulFloat(r1,ch/zn)) 
# print(x2)


# scal = ScalMulMethod(1)

# print(scal.find(0.1))

# tg = a[0][1]*2/(a[0][0]-a[1][1])
# fi = (math.atan(tg))/2

# #print(math.sin(fi))

# t = [[cos(fi),-sin(fi),0],[sin(fi),cos(fi),0],[0,0,1]]

# a = Matrix.mul(Matrix.mul(Matrix.transpose(t),a),t)
# #Matrix.print(a)

# #########

# tg = a[0][2]*2/(a[0][0]-a[2][2])
# fi = (math.atan(tg))/2


# t = [[cos(fi),0,-sin(fi)],[0,1,0],[sin(fi),0,cos(fi)]]

# a = Matrix.mul(Matrix.mul(Matrix.transpose(t),a),t)

# Matrix.print(a)


