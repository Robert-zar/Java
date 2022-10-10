class Horse extends Animals {
    private int weight;
    private int runningSpeed;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getRunningSpeed() {
        return runningSpeed;
    }

    public void setRunningSpeed(int runningSpeed) {
        this.runningSpeed = runningSpeed;
    }

    public Horse(String Name, String Type, String Sound, int weight, int runningSpeed) {
        super(Name, Type, Sound);
        this.weight = weight;
        this.runningSpeed = runningSpeed;
    }


    public void Say() {
        System.out.println("I-go-go!");
    }

    public void run() {
        System.out.println("Бежать!");
    }
}
