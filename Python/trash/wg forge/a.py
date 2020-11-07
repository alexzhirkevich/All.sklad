n,m=input().split()

if __name__ == "__main__":
    count = 0
    n=int(n)
    m=int(m)
    i = 1
    while(i <= n):
        if (m % i == 0 and m/i<=n):
            count+=1
        i+=1
print(count)
