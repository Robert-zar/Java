package calc.operation;

public class Subtraction {
    private int raz;
    public Subtraction()
    {
        raz=0;
    }
    public Subtraction(int a)
    {
        this.raz=a;
    }

    public void subtract(int b)
    {
        raz-=b;
    }
    public int getRaz()
    {
        return raz;
    }
}
