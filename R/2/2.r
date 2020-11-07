func <- function(x) {
	return (1 / (1 + sqrt(x)))
}

integral <- function(f,a,b,e) {
    znak = 1
    n = 2
    i = 0
    s1 = 0
    s2 = 0
    h = 0
	if (a == b) {
		return(0)
    }
	if (a > b) {
		temp = a
		a = b
		b = temp
		znak = -1
	}
	s2 = (b - a)* f((a+b)/2)
	repeat {
		s1 = s2
		s2 = 0
		n = n * 2
		h = (b - a)/n
		for (i in 1:(n-1)) {
			s2 = s2 + f(a + i * h - h/2)
        }
		s2 = (s2 + f(b))*h
		if (abs(s2 - s1) < e){
			break
		}
	} 
	return (znak * s2)
}
print("Result:")
print(integral(sin,1,4,0.001))
print("Integrate function:")
print(integrate(sin,1,4,rel.tol=0.0001))