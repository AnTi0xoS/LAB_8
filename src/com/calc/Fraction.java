package com.calc;

class Fraction {

    private int num, denom;

    Fraction() {
        this.num = 1;
        this.denom = 1;
    }

    Fraction(int num, int denom) {
        if (denom == 0) {
            System.out.println("ОШИБКА: Деление на ноль");
            System.out.println("        Пожалуйста, повторите попытку");
            System.exit(1);
        } else {
            this.num = num;
            this.denom = denom;
            this.reductionValue();
        }
    }

//    void setValue(int num, int denom) {
//        if (denom == 0) {
//            System.out.println("ОШИБКА: Деление на ноль");
//            System.out.println("        Пожалуйста, повторите попытку");
//            System.exit(1);
//        } else {
//            this.num = num;
//            this.denom = denom;
//            this.reductionValue();
//        }
//    }

    String getValue() {
        if (this.num == this.denom) {
            return "1";
        } else if (this.denom == 1) {
            return this.num + "";
        } else if (this.num == 0) {
            return "0";
        } else if (this.denom < 0) {
            return(this.num * -1) + "/" + (this.denom * -1);
        } else {
            return this.num + "/" + this.denom;
        }
    }

    private void reductionValue() {
        if (this.num == this.denom) {
            this.num = 1;
            this.denom = 1;
        } else if (this.num == 0) {
            this.denom = 1;
        } else {
            int gcd = greatestCommonDivisor(this.num, this.denom);
            this.num /= gcd;
            this.denom /= gcd;
        }
    }

    private int greatestCommonDivisor(int first, int second) {
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

    void summation(Fraction summand) {
        this.num = this.num * summand.denom + summand.num * this.denom;
        this.denom *= summand.denom;
        reductionValue();
    }

    void summation(Fraction first, Fraction second) {
        this.num = first.num * second.denom + second.num * first.denom;
        this.denom = first.denom * second.denom;
        reductionValue();
    }

    void substraction(Fraction deductible) {
        this.num = this.num * deductible.denom - deductible.num * this.denom;
        this.denom *= deductible.denom;
        reductionValue();
    }

    void substraction(Fraction first, Fraction second) {
        this.num = first.num * second.denom - second.num * first.denom;
        this.denom = first.denom * second.denom;
        reductionValue();
    }

    void multiplication(Fraction multiplier) {
        this.num *= multiplier.num;
        this.denom *= multiplier.denom;
        reductionValue();
    }

    void multiplication(Fraction first, Fraction second) {
        this.num = first.num * second.num;
        this.denom = first.denom * second.denom;
        reductionValue();
    }

    void division(Fraction divisible) {
        if (divisible.num == 0) {
            System.out.println("ОШИБКА: Деление на ноль");
            System.out.println("        Пожалуйста, повторите попытку");
            System.exit(1);
        } else {
            this.num *= divisible.denom;
            this.denom *= divisible.num;
            reductionValue();
        }
    }

    void division(Fraction first, Fraction second) {
        if (second.num == 0) {
            System.out.println("ОШИБКА: Деление на ноль");
            System.out.println("        Пожалуйста, повторите попытку");
            System.exit(1);
        } else {
            this.num = first.num * second.denom;
            this.denom = first.denom * second.num;
            reductionValue();
        }
    }
}
