main <- function(fname) {
    df = read.csv(fname)
    x = df$x6
    y = df$y6

    b1 = (mean(x*y)-mean(x)*mean(y)) / (mean(x^2)-mean(x)^2)
    b0 = mean(y) - b1*mean(x)   

    lrm = lm(y~x,data=df)
    
    print(b0)
    print(b1)
    print(lrm)

    plot(x,y)
    abline(lrm)  
}

main("regression_data.csv")