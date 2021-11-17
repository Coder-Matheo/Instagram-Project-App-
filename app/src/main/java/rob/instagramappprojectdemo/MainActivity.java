package rob.instagramappprojectdemo;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import rob.instagramappprojectdemo.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import rob.instagramappprojectdemo.roomDatabase.InstaObj;
import rob.instagramappprojectdemo.roomDatabase.InstaViewModel;
import rob.instagramappprojectdemo.roomDatabase.MyInstaDatabase;
import rob.instagramappprojectdemo.Adapters.InitialRecyclerView_Adapter;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    public static final String TAG = MainActivity.class.getSimpleName();
    List<String> usernameToInitRecycler;
    List<String> commentsToInitRecycler;
    List<String> postMessageToInitRecycler;
    List<String> dateTimeToInitRecycler;
    List<byte[]> imagePostToInitRecycler;

    InstaViewModel instaViewModel;
    InitialRecyclerView_Adapter initRecyclerView_adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
        insertPostsFun();
        getAllPostsFun();
        invokeRecyclerView_Adapter();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        initRecyclerView_adapter = new InitialRecyclerView_Adapter(this, this);
        initRecyclerView_adapter.initial();




    }

    public void init(){
        //initial the List Array for putting Value for RecyclerAdapter
        usernameToInitRecycler = new ArrayList<>();
        commentsToInitRecycler = new ArrayList<>();
        postMessageToInitRecycler = new ArrayList<>();
        dateTimeToInitRecycler = new ArrayList<>();
        imagePostToInitRecycler = new ArrayList<>();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }



    public void insertPostsFun(){
        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.sqlite_icon);
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bitMapData = stream.toByteArray();

        //InstaObj(String username, String password, String comments, String postMessage,String dateTime, byte[] instaImage)
        InstaObj instaObj = new InstaObj("Mattheo", "123456", "Hi this is nice", "Today was cool", "11.12.2001", bitMapData);
        InsertAsynTask insertAsynTask = new InsertAsynTask();
        //insertAsynTask.execute(instaObj);



    }

    class InsertAsynTask extends AsyncTask<InstaObj, Void, Void> {
        @Override
        protected Void doInBackground(InstaObj... times) {
            MyInstaDatabase.getInstance(getApplicationContext())
                    .instaDao()
                    .insert(times[0]);
            return null;
        }
    }
    private void getAllPostsFun() {
        instaViewModel = ViewModelProviders.of(this).get(InstaViewModel.class);
        instaViewModel.getAllPosts().observe(this, new Observer<List<InstaObj>>() {
            @Override
            public void onChanged(List<InstaObj> instaObjs) {
                Log.i(TAG, "onChanged: "+ instaObjs.size());
                Log.i(TAG, "onChanged: "+ instaObjs.get(0).getComments());
                for (int i = 0; i < instaObjs.size(); i++){
                    usernameToInitRecycler.add(instaObjs.get(i).getUsername());
                    commentsToInitRecycler.add(instaObjs.get(i).getComments());
                    postMessageToInitRecycler.add(instaObjs.get(i).getPostMessage());
                    dateTimeToInitRecycler.add(instaObjs.get(i).getDateTime());
                    imagePostToInitRecycler.add(instaObjs.get(i).getInstaImage());
                }


                initRecyclerView_adapter.initRecyclerAdapter(usernameToInitRecycler, commentsToInitRecycler, postMessageToInitRecycler, dateTimeToInitRecycler, imagePostToInitRecycler);

            }
        });

    }


    public void invokeRecyclerView_Adapter(){

    }

}