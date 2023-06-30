package sg.edu.np.mad.madpractical;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    public String username;
    public String description;
    public int id;
    private int myImageView;
    public boolean followed;

    public User(String username, String description, int id, boolean followed) {
        this.username = username;
        this.description = description;
        this.id = id;
        this.followed = followed;
    }

    protected User(Parcel in) {
        username = in.readString();
        description = in.readString();
        id = in.readInt();
        myImageView = in.readInt();
        followed = in.readByte() != 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    // Implement Parcelable methods
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(description);
        dest.writeInt(id);
        dest.writeInt(myImageView);
        dest.writeByte((byte) (followed ? 1 : 0));
    }
}
