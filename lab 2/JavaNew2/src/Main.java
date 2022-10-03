import java.util. Scanner;
public class Main {
    public static class MyDate {
        private int day, month, year;

        public MyDate(int d, int m, int y) {
            day = d;
            month = m;
            year = y;
        }

        @Override
        public boolean equals(Object о) {

            boolean result = false;

            MyDate o = null;
            if (o != null && o instanceof MyDate) {
                MyDate d = (MyDate) о;
                if ((day == d.day) && (month == d.month) && (year == d.year))
                    result = true;
            }
            return result;

        }

    }

    public class TestEquals {
        public void main(String[] args) {

            MyDate date1 = new MyDate(7, 11, 2014);
            MyDate date2 = new MyDate(7, 11, 2014);
            if (date1 == date2)
                System.out.println("date1 is identical date2");
            else
                System.out.println("date1 is not identical date2");

            if (date1.equals(date2))

                System.out.println("date1 is equals date2");
            else

                System.out.println("date1 is not equals date2");

            System.out.println("'set date2=date1");
            date2 = date1;
            if (date1 == date2)
                System.out.println("“datel is identical date2");
            else
                System.out.println("“datel is not identical date2");
        }
    }

        public static void main(String[ ] args) {
            System.out.println("Введите Ваше имя и нажмите <Enter>:");
            Scanner scan = new Scanner(System.in);
            String name = scan.next();
            System.out.println("Привет, " + name);
            scan.close();
        }

}