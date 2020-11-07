if __name__ == "__main__":
    result = 0
    number= []
    n=int(input())
    price=list(int(i) for i in input().split())
    lowestIndex = 8
    lowestPrice = price[8]
    highestPrice = price[8]
    for i in reversed(range(len(price)-1)):
        if price[i]<lowestPrice:
            lowestIndex=i
            lowestPrice=price[i]
        if highestPrice<price[i]:
            highestPrice=price[i]
    if (lowestPrice>n):
        result = 1
    else:
        while (n-lowestPrice>=highestPrice):
            number.insert(0,str(lowestIndex+1))
            n-=lowestPrice
        for i in reversed(range(len(price))):
            if (n>=price[i]):
                number.insert(0,str(i+1))
                break
        result = "".join(number)
    print(result)
        
