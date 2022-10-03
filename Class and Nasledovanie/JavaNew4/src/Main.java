public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog("Рекс", "Собака", "Лай", 5);
        dog.setName("Граф");
        System.out.println("Тип животного" + " " + dog.getType() + ", его зовут" + " " + dog.getName() + ", ему" + " " + dog.getWeight()
                + " " + "лет, он издаёт" + " " + dog.getSound().toString());

        Horse horse = new Horse("Мустанг", "Лошадь", "Ржание", 9, 50);
        System.out.println("Тип животного" + " " + horse.getType() + ", его зовут" + " " + horse.getName() + ", ему" + " " + horse.getWeight()
                + " " + "лет, он издаёт" + " " + horse.getSound()+ ", его скорость " + " "  + horse.getRunningSpeed() +"км/ч");

        Cat cat = new Cat("Барсик", "Кот", "Мяу", "синий");
        System.out.println("Тип животного" + " " + cat.getType() + ", его зовут" + " " + cat.getName() + " " + ", он издаёт"
                + " " + cat.getSound() + ", у него цвет глаз " + " " + cat.getEyeСolor().toString());

        Animals[] arr = new Animals[9];
        arr[0] = new Dog("Имя: Рекс,", "Тип: Собака,", "Звук: Лай", 5);
        arr[1] = new Dog("Имя: Рекс,", "Тип: Собака,", "Звук: Лай", 5);
        arr[2] = new Dog("Имя: Рекс,", "Тип: Собака,", "Звук: Лай", 5);
        arr[3] = new Cat("Имя: Барсик,", "Тип: Кот,", "Звук: Мяу", "синий");
        arr[4] = new Cat("Имя: Барсик,", "Тип: Кот,", "Звук: Мяу", "синий");
        arr[5] = new Cat("Имя: Барсик,", "Тип: Кот,", "Звук: Мяу", "синий");
        arr[6] = new Horse("Имя: Мустанг,", "Тип: Лошадь,", "Звук: Ржание", 9, 50);
        arr[7] = new Horse("Имя: Мустанг,", "Тип: Лошадь,", "Звук: Ржание", 9, 50);
        arr[8] = new Horse("Имя: Мустанг,", "Тип: Лошадь,", "Звук: Ржание", 9, 50);

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i].getName()  + " " + arr[i].getType() + " " + arr[i].getSound());
            arr[i].Say();
        }
    }
}
abstract class Animals {
    private String Name;
    private String Type;
    private String Sound;

    abstract void Say();

    public String getName() {
        return Name;
    }

    public void setName(String Name){
        this.Name = Name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type){
        this.Type = Type;
    }

    public String getSound() {
        return Sound;
    }

    public void setSound(String Sound){
        this.Sound = Sound;
    }
    public Animals(String Name, String Type, String Sound) {
        this.Name = Name;
        this.Type = Type;
        this.Sound = Sound;
    }

    @Override
    public String toString(){
        return getName() + " " + getType() + " " + getSound();
    }
}

class Dog extends Animals{

    private int weight;

    public int getWeight(){
        return weight;
    }

    public void setWeight(int weight){
        this.weight = weight;
    }
    public Dog(String Name, String Type, String Sound, int weight) {
        super(Name, Type, Sound);
        this.weight = weight;
    }

           @Override
           void Say(){
               System.out.println("Gav-gav!");
           }

}
class Cat extends Animals{
    private String eyeСolor;

    public String getEyeСolor(){
        return eyeСolor;
    }

    public void setEyeСolor(String eyeСolor){
        this.eyeСolor = eyeСolor;
    }
    public Cat(String Name, String Type, String Sound, String eyeСolor) {
        super(Name, Type, Sound);
        this.eyeСolor = eyeСolor;
    }

    @Override
    void Say(){
        System.out.println("Miau!");
    }

}

class Horse extends Animals{
   private int weight;
    private int runningSpeed;

    public int getWeight(){
        return weight;
    }

    public void setWeight(int weight){
        this.weight = weight;
    }

    public int getRunningSpeed(){
        return runningSpeed;
    }

    public void setRunningSpeed(int runningSpeed){
        this.runningSpeed = runningSpeed;
    }
    public Horse(String Name, String Type, String Sound, int weight, int runningSpeed) {
        super(Name, Type, Sound);
        this.weight = weight;
        this.runningSpeed = runningSpeed;
    }

    @Override
    void Say(){
        System.out.println("I-go-go!");
    }

}