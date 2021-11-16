package rob.instagramappprojectdemo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class InstaViewModel extends AndroidViewModel {
    MyInstaDatabase myInstaDatabase;

    public InstaViewModel(@NonNull Application application) {
        super(application);

        myInstaDatabase = MyInstaDatabase.getInstance(application.getApplicationContext());

    }


}
