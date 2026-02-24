package service;
public class CalculatorService {
    public double add(double a,double b){
        return a+b;
    }
    public double subtract(double a,double b){
        return a-b;
    }
    public double mul(double a,double b){
        return a*b;
    }
    public double div(double a,double b){
        if(b==0){
            throw new IllegalArgumentException("Division by zero");
        }
        return a/b;
    }
    public double sqr(double a){
        return Math.sqrt(a);
    }
    public double power(double a,double b){
        return Math.pow(a,b);
    }

}
