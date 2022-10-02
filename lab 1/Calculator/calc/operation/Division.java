package calc.operation;

public class Division {
    private int div;
    public Division()
    {
        div=0;
    }
    public Division(int a)
    {
        this.div=a;
    }

    public void divide(int b)
    {
        div/=b;
    }
    public int getDiv()
    {
        return div;
    }
}
