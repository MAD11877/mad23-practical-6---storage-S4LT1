package sg.edu.np.mad.madpractical;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MessageGroup extends AppCompatActivity {
    final String TITLE = "Message Group";
    private FrameLayout frameLayoutContainer;
    private Button group1;
    private Button group2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_message_group);
        Log.v(TITLE, "On Create!");

        group1 = findViewById(R.id.button5);
        group2 = findViewById(R.id.button6);
        frameLayoutContainer = findViewById(R.id.your_placeholder);
        // Begin the transaction
        group1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new instance of your fragment
                FragmentOne fragment = new FragmentOne();

                // Begin the transaction
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                // Replace the fragment in the container
                ft.replace(R.id.your_placeholder, fragment);

                // Commit the transaction
                ft.commit();
            }
        });

        group2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new instance of another fragment
                FragmentTwo fragment = new FragmentTwo();

                // Begin the transaction
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                // Replace the fragment in the container
                ft.replace(R.id.your_placeholder, fragment);

                // Commit the transaction
                ft.commit();
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.v(TITLE, "On Start!");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.v(TITLE, "On Pause!");
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

}
