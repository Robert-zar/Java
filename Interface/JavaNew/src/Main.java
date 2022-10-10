public class Main {
    public static void main(String[] args) {

        Animals[] arr = new Animals[9];
        arr[0] = new Dog("Имя: Рекс,", "Тип: Собака,", "Звук: Лай", 5);
        arr[1] = new Dog("Имя: Рекс,", "Тип: Собака,", "Звук: Лай", 5);
        arr[2] = new Dog("Имя: Граф,", "Тип: Собака,", "Звук: Лай", 7);
        arr[3] = new Cat("Имя: Барсик,", "Тип: Кот,", "Звук: Мяу", "синий");
        arr[4] = new Cat("Имя: Барсик,", "Тип: Кот,", "Звук: Мяу", "синий");
        arr[5] = new Cat("Имя: Джерри,", "Тип: Кот,", "Звук: Мяу", "коричневый");
        arr[6] = new Horse("Имя: Мустанг,", "Тип: Лошадь,", "Звук: Ржание", 9, 50);
        arr[7] = new Horse("Имя: Мустанг,", "Тип: Лошадь,", "Звук: Ржание", 9, 50);
        arr[8] = new Horse("Имя: Амиго,", "Тип: Лошадь,", "Звук: Ржание", 15, 70);

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i].getName()  + " " + arr[i].getType() + " " + arr[i].getSound());
            arr[i].Say();
            arr[i].run();
            arr[i].do_command();
            arr[i].eat();
            arr[i].sleep();
        }
    }
}

