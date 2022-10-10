class Cat extends Animals {
    private String eyeСolor;

    public String getEyeСolor() {
        return eyeСolor;
    }

    public void setEyeСolor(String eyeСolor) {
        this.eyeСolor = eyeСolor;
    }

    public Cat(String Name, String Type, String Sound, String eyeСolor) {
        super(Name, Type, Sound);
        this.eyeСolor = eyeСolor;
    }


    public void Say() {
        System.out.println("Miau!");
    }

    public void eat() {
        System.out.println("Кушать!");
    }
}
