public class Animals implements Action {
    private String Name;
    private String Type;
    private String Sound;

    @Override
    public void Say() {

    }

    @Override
    public void run() {

    }

    @Override
    public void do_command() {

    }

    @Override
    public void eat() {

    }

    @Override
    public void sleep() {

    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getSound() {
        return Sound;
    }

    public void setSound(String Sound) {
        this.Sound = Sound;
    }

    public Animals(String Name, String Type, String Sound) {
        this.Name = Name;
        this.Type = Type;
        this.Sound = Sound;
    }

    @Override
    public String toString() {
        return getName() + " " + getType() + " " + getSound();
    }
}
