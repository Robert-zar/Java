package calc;

public class calc {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Calculator calc=new Calculator();
        System.out.println("2+2="+calc.sum(2,2));
        System.out.println("10-7="+calc.raz(10,7));
        System.out.println("5*100="+calc.mult(5,100));
        System.out.println("150/3="+calc.div(150,3));
        System.out.println("5^2="+calc.exp(5));
    }
}
