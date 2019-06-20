package wilburhsu.CodingInterview;

public class _11_Power {
    boolean invalidInput = false;

    public double Power(double base, int exponent) {
        //底数等于0且指数小于等于0
        if(equals(base,0.0) && exponent <= 0){
            invalidInput = true;
            return 0.0;
        }

        int absExponent = exponent;
        if(exponent < 0)
            absExponent = -exponent;

        double result = powerWithExponent(base,absExponent);
        if(exponent < 0)
            result = 1.0/result;
        return result;
    }

    public double powerWithExponent(double base,int exponent){
        double result = 1.0;
        for(int i = 1;i <= exponent;i++){
            result *= base;
        }
        return result;
    }

    public boolean equals(double num1,double num2){
        double tmp = num1 - num2;
        if(tmp > 0.0000001 && tmp < -0.0000001)
            return true;
        else
            return false;
    }
}
