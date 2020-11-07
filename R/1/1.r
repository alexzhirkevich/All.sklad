geom_progr <- function(first_element,q,count) {
    vector = rep(0,count)
    k=1
    while(k <= count) {
        vector[k] = first_element * (q^(k-1))
        k = k+1
    }
    return(vector)
}

task_1_1 <- function(number,surname) {
    return(seq(number, 6.5, length.out = nchar(surname)))
}

task_1_2 <- function(number,name) {
    first_element <- abs(13-number)+2
    last_element <- 100
    count = nchar(name)
    q <- (last_element/first_element)^(1/(count-1))
    return(geom_progr(first_element,q,count))
}

task_1_3 <- function(v1,v2,number) {
    vector = rep(0,length(v1)+length(v2))
    if(round(v1[1]+v2[1]) %%2 == 0){
        k=1
        for(i in v1) {
            vector[k]=i
            k=k+1
        }
        for(i in v2){
            vector[k]=i
            k=k+1
        }
    }
    else {
        k=1
        for(i in v2){
            vector[k]=i
            k=k+1
        }
        for(i in v1){
            vector[k]=i
            k=k+1
        }
    }
    if (number %% 2 == 0){
        m <- matrix(data = vector,nrow=4, byrow = TRUE)
    }
    else {
        m <- matrix(data = vector,nrow=4)
    }
    return(m)
}

task_2_1 <- function() {
    num <- 1:5
    bool <- c(T,F,T,F,T)
    fact = factor(num)
    return(data.frame(num,bool,fact))
}

task_3 <- function() {
    df <- read.csv("iris.csv")
    print(nrow(subset(df, Sepal.Width <= 3)))
    print(nrow(subset(df, Petal.Length > 4.5)))
    print(nrow(subset(df, Sepal.Width <= 3 & Petal.Length > 4.5)))
    print(mean(df$Petal.Width))
}

v1=task_1_1(9,"Жиркевич")
print ("First vector:")
print(v1)

v2=task_1_2(9,"Александр")
print("Second vector:")
print(v2)


m=task_1_3(v1,v2,9)
print("Matrix:")
print(m)

df = task_2_1()
print("Data Frame:")
print(df)

print("TRUE lines:")
print(subset(df,bool==TRUE))

print("TASK 3:")
task_3()