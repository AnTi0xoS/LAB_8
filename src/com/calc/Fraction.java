package com.calc;

public class Fraction {

    private int numenator, denominator;

    Fraction() {
        this.numenator = 1;
        this.denominator = 1;
    }

    Fraction(int num, int denom) {
        if (denom == 0) {
            throw new ArithmeticException();
        } else {
            this.numenator = num;
            this.denominator = denom;
            this.reductionValue();
        }
    }

    public String getValue() {
        String value = "";
        if (this.numenator == this.denominator) {
            value += "1";
        } else if (this.denominator == 1) {
            value += this.numenator;
        } else if (this.numenator == 0) {
            value += "0";
        } else if (this.denominator < 0) {
            value += ((this.numenator * -1) + "/" + (this.denominator * -1));
        } else {
            value += (this.numenator + "/" + this.denominator);
        }
        return value;
    }

    private void reductionValue() {
        if (this.numenator == this.denominator) {
            this.numenator = 1;
            this.denominator = 1;
        } else if (this.numenator == 0) {
            this.denominator = 1;
        } else {
            int gcd = greatestCommonDivisor(this.numenator, this.denominator);
            this.numenator /= gcd;
            this.denominator /= gcd;
        }
    }

    private static void reductionValue(Fraction fraction) {
        if (fraction.numenator == fraction.denominator) {
            fraction.numenator = 1;
            fraction.denominator = 1;
        } else if (fraction.numenator == 0) {
            fraction.denominator = 1;
        } else {
            int gcd = greatestCommonDivisor(fraction.numenator, fraction.denominator);
            fraction.numenator /= gcd;
            fraction.denominator /= gcd;
        }
    }

    private static int greatestCommonDivisor(int first, int second) {
        int gcd = 1;
        int minValue = Math.min(Math.abs(first), Math.abs(second));
        for (int i = minValue; i >= 2; i--) {
            if ((Math.abs(first) % i == 0) && (Math.abs(second) % i == 0)) {
                gcd = i;
                break;
            }
        }
        return gcd;
    }

    public void summation(Fraction summand) {
        this.numenator = this.numenator * summand.denominator + summand.numenator * this.denominator;
        this.denominator *= summand.denominator;
        reductionValue();
    }

    public static Fraction summation(Fraction first, Fraction second) {
        Fraction sum = new Fraction();
        sum.numenator = first.numenator * second.denominator + second.numenator * first.denominator;
        sum.denominator = first.denominator * second.denominator;
        reductionValue(sum);
        return sum;
    }

    public void substraction(Fraction deductible) {
        this.numenator = this.numenator * deductible.denominator - deductible.numenator * this.denominator;
        this.denominator *= deductible.denominator;
        reductionValue();
    }

    public static Fraction substraction(Fraction first, Fraction second) {
        Fraction reduced = new Fraction();
        reduced.numenator = first.numenator * second.denominator - second.numenator * first.denominator;
        reduced.denominator = first.denominator * second.denominator;
        reductionValue(reduced);
        return reduced;
    }

    public void multiplication(Fraction multiplier) {
        this.numenator *= multiplier.numenator;
        this.denominator *= multiplier.denominator;
        reductionValue();
    }

    public static Fraction multiplication(Fraction first, Fraction second) {
        Fraction product = new Fraction();
        product.numenator = first.numenator * second.numenator;
        product.denominator = first.denominator * second.denominator;
        reductionValue(product);
        return product;
    }

    public void division(Fraction divisible) {
        if (divisible.numenator == 0) {
            throw new ArithmeticException();
        } else {
            this.numenator *= divisible.denominator;
            this.denominator *= divisible.numenator;
            reductionValue();
        }
    }

    public static Fraction division(Fraction first, Fraction second) {
        Fraction divisible = new Fraction();
        if (second.numenator == 0) {
            throw new ArithmeticException();
        } else {
            divisible.numenator = first.numenator * second.denominator;
            divisible.denominator = first.denominator * second.numenator;
            reductionValue(divisible);
        }
        return divisible;
    }
}
