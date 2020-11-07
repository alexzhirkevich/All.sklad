getmode <- function(v) {
uniqv <- unique(v)
uniqv[which.max(tabulate(match(v, uniqv)))]
}

main <- function() {
    df = read.csv("avianHabitat.csv")
    vector1 = df$EHt[df$EHt!=0]
    print(median(vector1))
    print(max(vector1))
    print(mean(vector1))
    print(getmode(vector1))
    print(var(vector1))
    print(sqrt(var(vector1)))
    print(quantile(vector1, c(.25,.75)))
    boxplot(vector1,col = "white", border = "red") 
    vector2 = df$EHt[df$WHt!=0]
    boxplot(vector1,vector2,col=c("white", "yellow"), border = c("red", "blue"))
    plot.ecdf(vector1,col = "red")
    hist(vector1,col = "red",probability = T)
    lines(density(vector1), lwd = 7, col = "#00FF00")
    qqnorm((vector1-mean(vector1))/sd(vector1))
    lines(c(-4,4), c(-4,4), lwd = 3, col = "#FF0000")   
}

main()