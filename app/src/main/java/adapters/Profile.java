package adapters;

public class Profile {

    public int id;
    public String name, about, activeTime;
    public boolean isActive;

    public Profile(int id, String name, String about, String activeTime, boolean isActive) {
        this.id = id;
        this.name = name;
        this.about = about;
        this.activeTime = activeTime;
        this.isActive = isActive;
    }
}
