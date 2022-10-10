package JavaNew;

public class Main {

    static class User {
        private String name;
        private String surname;
        private int birthYear;

        User(String name, String surname, int birthYear) {
            this.name = name;
            this.surname = surname;
            this.birthYear = birthYear;
        }

        @Override
        public String toString() {
            return this.name+","+ " " +this.birthYear+"г.";
        }
    }

    #Выводит HashCode и имя
        
    public static void main(String[] args) {
        User usl = new User("Иван", "Петров", 1996);
        System.out.println(usl.toString());
        System.out.println("Hash:" + usl.hashCode());
    }
}