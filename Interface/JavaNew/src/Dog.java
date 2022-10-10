class Dog extends Animals {

    private int weight;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Dog(String Name, String Type, String Sound, int weight) {
        super(Name, Type, Sound);
        this.weight = weight;
    }


    public void Say() {
        System.out.println("Gav-gav!");
    }

    public void do_command() {
        System.out.println("Исполнить команду хозяина!");
    }

    public void sleep() {
        System.out.println("Спать!");
    }

}
