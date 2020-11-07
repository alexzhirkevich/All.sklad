library(moments)
x = rbeta(200,0.5,0.5)
print(x[1:10])
print(x[180:200])
print(min(x))
print(max(x))
print(0.5)
print(var(x))
print(median(x))
print(kurtosis(x))
print(skewness(x))
print(quantile(x,c(0.25,0.75,0.95)))
boxplot(x)
plot.ecdf(x)   
y=x[x!=0]
hist(y,col="green",border="blue",prob = TRUE,main = "Гистрограмма")
lines(density(y),lwd = 10,col = "red")
dbeta(x,0,5,0,5)



