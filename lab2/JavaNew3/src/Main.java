public class Main {
    public static void main(String[] args) {

        Dog dog = new Dog();
        dog.voice();
    }
}
     abstract class Pet {
        abstract void voice();

    }

    class Dog extends Pet {
        @Override

        void voice() {
            System.out.println("Gav-gav!");
        }
    }


    class Cat extends Pet {

        @Override
         void voice() {
            System.out.println("Miaou!");
        }
    }

    class Cow extends Pet {

        @Override
        void voice() {
            System.out.println("Mu-u-u!");
        }
    }



