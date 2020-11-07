public class Main {

    static int n = 5;

    public static double expectation(int[] x, double[] p) {
        System.out.print("E(x) = ");
        for (int i = 0; i < n; i++) {
            if (i != n - 1)
                System.out.printf("(%d * %.2f) + ", x[i], p[i]);
            else
                System.out.printf("(%d * %.2f) =\n", x[i], p[i]);
        }
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += x[i] * p[i];
            if (i != n - 1)
                System.out.printf("(%.2f) + ", x[i] * p[i]);
            else
                System.out.printf("(%.2f) = ", x[i] * p[i]);
        }
        System.out.printf("%.2f\n", sum);
        return sum;
    }

    public static double dispersion(int[] x, double[] p, double E) {
        double sum = 0;
        System.out.print("V(x) = ");
        for (int i = 0; i < n; i++) {
            if (i != n - 1)
                System.out.printf("(%.2f * (%d - %.2f)^2) + ", p[i], x[i], E);
            else
                System.out.printf("(%.2f * (%d - %.2f)^2) =\n", p[i], x[i], E);
        }
        for (int i = 0; i < n; i++) {
            sum +=  p[i] * (x[i] - E) * (x[i] - E);
            if (i != n - 1)
                System.out.printf("%.2f + ", p[i] * (x[i] - E) * (x[i] - E));
            else
                System.out.printf("%.2f = ", p[i] * (x[i] - E) * (x[i] - E));
        }
        System.out.printf("%.2f\n", sum);
        return sum;
    }

    public static void asymmetric(int[] x, double[] p, double E) {
        double numerator = 0, denominator = 0;
        System.out.println("A(x). Числитель дроби: ");
        for (int i = 0; i < n; i++) {
            if (i != n - 1)
                System.out.printf("%.2f(%d - %.2f)^3 + ", p[i], x[i], E);
            else
                System.out.printf("%.2f(%d - %.2f)^3 =\n", p[i], x[i], E);
        }
        for (int i = 0; i < n; i++) {
            numerator += p[i] * (x[i] - E) * (x[i] - E) * (x[i] - E);
            if (i != n - 1)
                System.out.printf("(%.2f) + ", p[i] * (x[i] - E) * (x[i] - E) * (x[i] - E));
            else
                System.out.printf("(%.2f) = ", p[i] * (x[i] - E) * (x[i] - E) * (x[i] - E));
        }
        System.out.printf("%.4f\n", numerator);
        System.out.print("Знаменатель дроби: \n( ");
        for (int i = 0; i < n; i++) {
            if (i != n - 1)
                System.out.printf("%.2f(%d - %.2f)^2 + ", p[i], x[i], E);
            else
                System.out.printf("%.2f(%d - %.2f)^2 ) ^ 3/2 =\n( ", p[i], x[i], E);
        }

        for (int i = 0; i < n; i++) {
            denominator += p[i] * (x[i] - E) * (x[i] - E);
            if (i != n - 1)
                System.out.printf("%.2f + ", p[i] * (x[i] - E) * (x[i] - E));
            else
                System.out.printf("%.2f ) ^ 3/2 = ", p[i] * (x[i] - E) * (x[i] - E));
        }
        denominator = Math.sqrt(denominator * denominator * denominator);
        System.out.printf("%.4f\n", denominator);
        System.out.printf("\nA(x) = %.4f\n", numerator / denominator);
    }

    public static void kurtosis(int[] x, double[] p, double E, double D) {
        double numerator = 0;
        System.out.println("K(x). Мю_4: ");
        for (int i = 0; i < n; i++) {
            if (i != n - 1)
                System.out.printf("%.2f(%d - %.2f)^4 + ", p[i], x[i], E);
            else
                System.out.printf("%.2f(%d - %.2f)^4 =\n", p[i], x[i], E);
        }
        for (int i = 0; i < n; i++) {
            numerator += p[i] * (x[i] - E) * (x[i] - E) * (x[i] - E) * (x[i] - E);
            if (i != n - 1)
                System.out.printf("%.2f + ", p[i] * (x[i] - E) * (x[i] - E) * (x[i] - E) * (x[i] - E));
            else
                System.out.printf("%.2f = ", p[i] * (x[i] - E) * (x[i] - E) * (x[i] - E) * (x[i] - E));
        }
        System.out.printf("%.4f\n", numerator);
        double delta = D * D;
        System.out.printf("Дельта^4: ( %.4f ) ^ 2 = %.4f\n", D, delta);

        System.out.printf("\nK(x) = (%.2f / %.2f) - 3 = %.4f\n", numerator, delta, (numerator / delta) - 3);
    }

    public static void main(String[] args) {
        int[] x = new int[]{-1,1,3,4,7 }; //сюда вводите случайные величины (x_i+1 > x_i)
        double[] p = new double[] {0.1,0.3,0.2,0.15,0.25 }; //сюда вводите вероятности (их сумма должна быть равна 1)
        n = x.length;

        System.out.print("x");
        for (int j : x)
            System.out.printf("%7d", j);
        System.out.print("\np");
        for (double j : p)
            System.out.printf("%7.2f", j);
        System.out.print("\n\n");

        double E = expectation(x, p);
        System.out.println();
        System.out.println();
        double D = dispersion(x, p, E);
        System.out.println();
        System.out.println();
        asymmetric(x, p, E);
        System.out.println();
        System.out.println();
        kurtosis(x, p, E, D);
    }
}