package fraction;

public class Fraction {

    private int n;
    private int m;
    
    Fraction(int n, int m) throws Exception {
        this.n = n;
        this.m = m;
        this.transform();
    }

    Fraction(Fraction f) throws Exception{
        this.n = f.n;
        this.m = f.m;
        this.transform();
    }

    private int nod(int a, int b) {
        int t;
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }
        while (b != 0) 
        {
            t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    private Fraction transform() throws Exception {
        if (m == 0) {
            throw new Exception("Can't devide by zero");
        }
        
        int nod = nod(n, m);
        n /= nod;
        m /= nod;
        if (n > 0 && m < 0) {
            n = -n;
            m = -m;
        }
        if (n < 0 && m < 0) {
            n = -n;
            m = -m;
        }
        return this;
    }

    public final Fraction add(final Fraction f) throws Exception {
        Fraction temp = new Fraction(f);
        Fraction thisTemp = new Fraction(this);
        int tm = thisTemp.m;
        thisTemp.m *= temp.m;
        thisTemp.n *= temp.m;
        temp.m *= tm;
        temp.n *= tm;
        thisTemp.n += temp.n;
        return thisTemp.transform();
    }

    public final Fraction add(final int value) throws Exception {
        Fraction temp = new Fraction(value,1);
        return this.add(temp);
    }

    public final Fraction sub(final Fraction f) throws Exception {
        Fraction temp = new Fraction(f);
        Fraction thisTemp = new Fraction(this);
        int tm = thisTemp.m;
        thisTemp.m *= temp.m;
        thisTemp.n *= temp.m;
        temp.m *= tm;
        temp.n *= tm;
        thisTemp.n -= temp.n;
        return thisTemp.transform();
    }

    public final Fraction sub(final int value) throws Exception {
        Fraction temp = new Fraction(value,1);
        return this.sub(temp);
    }

    public final Fraction mul(final Fraction f) throws Exception {
        Fraction thisTemp = new Fraction(this);
        thisTemp.n *= f.n;
        thisTemp.m *= f.m;
        return thisTemp.transform();
    }

    public final Fraction mul(final int value) throws Exception {
        Fraction temp = new Fraction(value,1);
        return this.mul(temp);
    }

    public final Fraction div(final Fraction f) throws Exception {
        Fraction thisTemp = new Fraction(this);
        thisTemp.n *= f.m;
        thisTemp.m *= f.n;
        return thisTemp.transform();
    }

    public final Fraction div(final int value) throws Exception {
        Fraction temp = new Fraction(value,1);
        return this.div(temp);
    }

    public final int compare(final Fraction f) { 
        if (this.n == f.n && this.m == f.n) {
            return 0;
        }
        if (this.n/this.m > f.n/f.m) {
            return 1;
        }
        return -1;
    }

    public final int compare(final int value) throws Exception {
        Fraction temp = new Fraction(value,1);
        return this.compare(temp);
    }

    public final int compare(final double value) {
        if (this.n/this.m == value){
            return 0;
        }
        if (this.n/this.m > value){
            return 1;
        }
        return -1;
    }

    @Override
    public final String toString() {
        return new String("["+n + " / " + m +"]");
    }

}
