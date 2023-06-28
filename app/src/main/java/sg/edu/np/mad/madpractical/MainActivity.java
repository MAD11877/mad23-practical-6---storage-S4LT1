package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private Button followButton;
    final String TITLE = "Main Activity";
    private User user;
    private TextView userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);
        Log.v(TITLE, "On Create!");

        user = new User("username", "description", 1, false);
        followButton = findViewById(R.id.button);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int num = extras.getInt("num");
            userID = findViewById(R.id.textView2);
            String displayText = "MAD " + num;
            userID.setText(displayText);
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