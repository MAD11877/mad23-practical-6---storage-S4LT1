package sg.edu.np.mad.madpractical;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.Random;

public class ListActivity extends AppCompatActivity {

    private ImageView icon;

    final String TITLE = "List Activity!";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_list);
        Log.v(TITLE, "On Create!");

        icon = findViewById(R.id.icon);

        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                AlertDialog.Builder image = new AlertDialog.Builder(ListActivity.this);
                image.setTitle("Profile");
                image.setMessage("MADness");
                image.setCancelable(true);

                image.setPositiveButton(
                        "View",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();

                                Random random = new Random();
                                int value = random.nextInt((int) Math.pow(10, 10));;

                                Intent intent = new Intent(ListActivity.this, MainActivity.class);
                                intent.putExtra("num",value);
                                startActivity(intent);
                            }
                        });

                image.setNegativeButton(
                        "Close",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = image.create();
                alert.show();
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
