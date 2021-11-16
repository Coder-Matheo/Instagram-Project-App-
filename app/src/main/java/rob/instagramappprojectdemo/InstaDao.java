package rob.instagramappprojectdemo;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface InstaDao {
    @Insert
    void insert(InstaObj instaObj);

    //@Query("SELECT * FROM ")
    //LiveData<List<InstaObj>> getAllPosts();
}
