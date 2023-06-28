package sg.edu.np.mad.madpractical;

public class User {
    public String username;

    public String description;

    public int id;

    public boolean followed;

    public User(String username, String description, int id, boolean followed) {
        this.username = username;
        this.description = description;
        this.id = id;
        this.followed = followed;
    }
}