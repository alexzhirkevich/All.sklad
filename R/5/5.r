library(cluster)
library(fpc)
library(party)

km = function(m) {
    cl = kmeans(m, 5)
    plot(m, col = cl$cluster,pch= cl$cluster)
}   
cl =function(m) {
    cl = clara(m, 5)
    plot(m, col = cl$clustering,pch=cl$clustering)
}

pm = function(m) {
    cl = pam(m, 5) 
    plot(m, col = cl$clustering,pch=cl$clustering)
}

kmr = function(m) {
    cl =  kmeansruns(m)
    plot(m, col = cl$cluster,pch=cl$cluster)
}
pmk = function(m) {
    cl = pamk(m)
    plot(m, col = cl$pamobject$clustering,pch=cl$pamobject$clustering)
}

x<-c(rnorm(20,3),rnorm(25,8),rnorm(30,5))
y<-c(rnorm(20,1),rnorm(25,4),rnorm(30,9))   
m = matrix(c(x,y),ncol=2)

pdf("qwe.pdf")
#1
plot(x,y)
#2
km(m)
cl(m)
pm(m)
#3
kmr(m)
pmk(m)
#4
cl = kmeans(m, 5)
df<-data.frame(x=x,y=y,cluster = cl$cluster)
plot(ctree(cl$cluster~.,df))
dev.off()