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

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    private Button followButton;
    private Button messageButton;
    final String TITLE = "Main Activity";
    private static User user;
    private TextView userID;
    private TextView userDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);
        Log.v(TITLE, "On Create!");

        followButton = findViewById(R.id.button);
        messageButton = findViewById(R.id.button2);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = (User) extras.getParcelable("user"); // Update this line
            userID = findViewById(R.id.textView2);
            String displayUser = user.username;
            userID.setText(displayUser);

            userDesc = findViewById(R.id.textView);
            String displayDesc = user.description;
            userDesc.setText(displayDesc);
        }

        userChangeButton();
        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.followed = !user.followed;
                userChangeButton();

                if (user.followed){
                    Toast.makeText(MainActivity.this, "Followed", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Unfollowed", Toast.LENGTH_SHORT).show();
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

        if (user.followed){
            followButton.setText("Unfollow");
        }
        else{
            followButton.setText("Follow");
        }
    }
}