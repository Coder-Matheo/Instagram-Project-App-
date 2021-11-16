package rob.instagramappprojectdemo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class InstaObj {
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "user_name")
    private String username;

    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "comments")
    private String comments;

    @ColumnInfo(name = "postMessage")
    private String postMessage;



    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] instaImage;

    public InstaObj(String username, String password, String comments, String postMessage, byte[] instaImage) {
        this.username = username;
        this.password = password;
        this.comments = comments;
        this.postMessage = postMessage;
        
        this.instaImage = instaImage;
    }


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getPostMessage() {
        return postMessage;
    }

    public void setPostMessage(String postMessage) {
        this.postMessage = postMessage;
    }

 

    public byte[] getInstaImage() {
        return instaImage;
    }

    public void setInstaImage(byte[] instaImage) {
        this.instaImage = instaImage;
    }
}
