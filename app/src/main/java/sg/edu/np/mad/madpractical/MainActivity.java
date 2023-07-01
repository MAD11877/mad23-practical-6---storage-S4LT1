package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button followButton;
    private Button messageButton;
    private TextView userNameTextView;
    private TextView userDescTextView;
    private DBHandler dbHandler;
    private User currentUser;

    final String TITLE = "Main Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);
        Log.v(TITLE, "On Create!");

        // Initialize DBHandler
        dbHandler = new DBHandler(this);

        followButton = findViewById(R.id.button);
        messageButton = findViewById(R.id.button2);
        userNameTextView = findViewById(R.id.textView2); // Update with the correct TextView ID
        userDescTextView = findViewById(R.id.textView); // Update with the correct TextView ID

        // Retrieve the user ID from the intent
        Intent intent = getIntent();
        int userId = intent.getIntExtra("userId", -1);
        if (userId != -1) {
            // Get the user from the database using the ID
            List<User> userList = dbHandler.getUsers();
            for (User user : userList) {
                if (user.id == userId) {
                    currentUser = user;
                    break;
                }
            }

            if (currentUser != null) {
                // Update the UI with the user's details
                userNameTextView.setText(String.valueOf(currentUser.username));
                userDescTextView.setText(currentUser.description);
                userChangeButton();
            }
        }
        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentUser != null) {
                    // Update the current user's follow status
                    currentUser.followed = !currentUser.followed;
                    userChangeButton();

                    // Update the user in the database
                    dbHandler.updateUser(currentUser);

                    if (currentUser.followed) {
                        Toast.makeText(MainActivity.this, "Followed", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Unfollowed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MessageGroup.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.v(TITLE,"On Start!");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.v(TITLE,"On Pause!");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.v(TITLE,"On Resume!");


    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.v(TITLE,"On Stop!");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.v(TITLE,"On Destroy!");
    }

    public void userChangeButton(){

        if (currentUser.followed){
            followButton.setText("Unfollow");
        }
        else{
            followButton.setText("Follow");
        }
    }
}