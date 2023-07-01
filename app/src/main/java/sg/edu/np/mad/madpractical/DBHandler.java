package sg.edu.np.mad.madpractical;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "accountDB.db";
    private static final String ACCOUNTS = "Accounts";
    private static final String COLUMN_USERNAME = "UserName";
    private static final String COLUMN_DESCRIPTION = "Password";
    private static final String COLUMN_FOLLOW = "Follow";
    private static final String COLUMN_ID = "Id";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        generateAndInsertUsers(); // Call the method to generate and insert users
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ACCOUNT_TABLE = "CREATE TABLE " + ACCOUNTS + "("
                + COLUMN_USERNAME + " TEXT, "
                + COLUMN_DESCRIPTION + " TEXT, "
                + COLUMN_FOLLOW + " TEXT, "
                + COLUMN_ID + " INTEGER PRIMARY KEY" + ")";
        db.execSQL(CREATE_ACCOUNT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exists
        db.execSQL("DROP TABLE IF EXISTS " + ACCOUNTS);
        // Create tables again
        onCreate(db);
    }

    public List<User> getUsers() {
        List<User> userList = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT * FROM " + ACCOUNTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Check if the cursor contains the columns
        int columnIndexUsername = cursor.getColumnIndex(COLUMN_USERNAME);
        int columnIndexDescription = cursor.getColumnIndex(COLUMN_DESCRIPTION);
        int columnIndexFollow = cursor.getColumnIndex(COLUMN_FOLLOW);
        int columnIndexId = cursor.getColumnIndex(COLUMN_ID);

        // Loop through all rows and add to list
        while (cursor.moveToNext()) {
            if (columnIndexUsername != -1 && columnIndexDescription != -1 &&
                    columnIndexFollow != -1 && columnIndexId != -1) {
                String username = cursor.getString(columnIndexUsername);
                String description = cursor.getString(columnIndexDescription);
                boolean follow = cursor.getInt(columnIndexFollow) == 1;
                int id = cursor.getInt(columnIndexId);

                User user = new User(username, description, id, follow);
                userList.add(user);
            }
        }

        // Close cursor and database connection
        cursor.close();
        db.close();

        return userList;
    }

    private void generateAndInsertUsers() {
        SQLiteDatabase db = this.getWritableDatabase();

        for (int i = 1; i <= 20; i++) {
            long generatedNumber = generateRandomNumber(1_000_000_000L, 10_000_000_000L);
            long generatedDesc = generateRandomNumber(1_000_000_000L, 10_000_000_000L);
            String username = "Name" + generatedNumber;
            String description = "Desc" + generatedDesc;
            boolean follow = Math.random() < 0.5;

            ContentValues values = new ContentValues();
            values.put(COLUMN_USERNAME, username);
            values.put(COLUMN_DESCRIPTION, description);
            values.put(COLUMN_FOLLOW, follow ? "1" : "0");

            // Insert row
            db.insert(ACCOUNTS, null, values);
        }

        // Close database connection
        db.close();
    }

    private long generateRandomNumber(long rangeStart, long rangeEnd) {
        Random r = new Random();
        return (long) (r.nextDouble() * (rangeEnd - rangeStart)) + rangeStart;
    }

    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DESCRIPTION, user.description);
        values.put(COLUMN_FOLLOW, user.followed ? "1" : "0"); // Update the follow status

        // Updating row
        db.update(ACCOUNTS, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(user.id)});

        // Close database connection
        db.close();
    }

}



