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

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private ImageView icon;
    private ArrayList<User> userList;
    private customAdapter cuAdapter;
    private DBHandler dbHandler; // Declare the DBHandler instance
    int imageResource = R.mipmap.ic_launcher_round;

    final String TITLE = "List Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_list);
        Log.v(TITLE, "On Create!");

        userList = new ArrayList<>();
        dbHandler = new DBHandler(this); // Instantiate the DBHandler here

        cuAdapter = new customAdapter(this, userList, imageResource);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(cuAdapter);

        int margin = getResources().getDimensionPixelSize(R.dimen.item_margin);
        MarginItemDecoration itemDecorator = new MarginItemDecoration(margin);
        recyclerView.addItemDecoration(itemDecorator);

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

                                Intent intent = new Intent(ListActivity.this, MainActivity.class);
                                intent.putExtra("userId", selectedUser.id); // Pass the user ID to MainActivity
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

        // Load users from the database and update the userList
        userList.addAll(dbHandler.getUsers());
        cuAdapter.notifyDataSetChanged();
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
