package sg.edu.np.mad.madpractical;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {

    private ImageView icon;
    private ArrayList<User> userList;
    int imageResource = R.mipmap.ic_launcher_round; // Replace with your desired image resource ID
    customAdapter cuAdapter; // Declare the adapter here

    final String TITLE = "List Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_list);
        Log.v(TITLE, "On Create!");

        userList = new ArrayList<>();
        createUser(userList);

        cuAdapter = new customAdapter(userList, imageResource); // Instantiate the adapter here

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(cuAdapter);

        int margin = getResources().getDimensionPixelSize(R.dimen.item_margin);
        MarginItemDecoration itemDecorator = new MarginItemDecoration(margin);
        recyclerView.addItemDecoration(itemDecorator);

        // Set click listener on the adapter
        cuAdapter.setOnItemClickListener(new customAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                User selectedUser = userList.get(position);

                AlertDialog.Builder image = new AlertDialog.Builder(ListActivity.this);
                image.setTitle("Profile");
                image.setMessage(selectedUser.username);
                image.setCancelable(true);

                image.setPositiveButton(
                        "View",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();

                                Random random = new Random();
                                int value = random.nextInt((int) Math.pow(10, 10));;

                                Intent intent = new Intent(ListActivity.this, MainActivity.class);
                                intent.putExtra("user", selectedUser);
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

    public void createUser(ArrayList<User> arrayList){
        for (int i=1;i<21;i++) {
            byte[] array = new byte[10]; // length is bounded by 7
            Random r = new Random();
            long rangeStart = 1_000_000_000L;
            long rangeEnd = 10_000_000_000L;
            long generatedNumber = (long) (r.nextDouble() * (rangeEnd - rangeStart)) + rangeStart;
            long generatedDesc = (long) (r.nextDouble() * (rangeEnd - rangeStart)) + rangeStart;

            User newUser = new User("Name" + generatedNumber, "Desc" + generatedDesc, i, (Math.random() < 0.5));
            arrayList.add(newUser);
        }
    }
}
