# if __name__ == "__main__":
#     with open("input.txt","r") as inp,open("output.txt","w") as out:
#         out.write(str(sum({a)))
import sys

class Node:

    def __init__(self, val):
        
        
        self.left = None
        self.right = None  
        self.data = val

    def insert(self, data):
        if self.data:
            if (data == self.data):
                return
            elif data < self.data:
                if self.left is None:
                    self.left = Node(data)
                else:
                    self.left.insert(data)
            elif data > self.data:
                if self.right is None:
                    self.right = Node(data)
                else:
                    self.right.insert(data)
        else:
            self.data = data

    def PrintTree(self,stream):
        if self.left:
            self.left.PrintTree(stream)
        stream.write(str(self.data) + "\n")
        if self.right:
            self.right.PrintTree(stream)

if __name__ == "__main__":

    sys.setrecursionlimit(30000)

    with open("input.txt","r") as inp,open("output.txt","w") as out:
        a = list({int(i) for i in inp.readlines()})
        n = Node(a[0])
        for i in a:
            n.insert(i)
        n.PrintTree(out)


