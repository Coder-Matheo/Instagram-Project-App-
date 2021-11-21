package rob.instagramappprojectdemo.Adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import rob.instagramappprojectdemo.MainActivity;
import rob.instagramappprojectdemo.R;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private static final String TAG = MainActivity.class.getSimpleName();
    List<String> usernameForRecycler;
    List<String> commentsForRecycler;
    List<String> postMessageForRecycler;
    List<String> dateTimeForRecycler;
    List<byte[]> imagePostForRecycler;

    public RecyclerAdapter(List<String> usernameForRecycler, List<String> commentsForRecycler, List<String> postMessageForRecycler, List<String> dateTimeForRecycler, List<byte[]> imagePostForRecycler) {
        Log.i(TAG, "initRecyclerAdapter: "+ usernameForRecycler.get(0));
        this.usernameForRecycler = usernameForRecycler;
        this.commentsForRecycler = commentsForRecycler;
        this.postMessageForRecycler = postMessageForRecycler;
        this.dateTimeForRecycler = dateTimeForRecycler;
        this.imagePostForRecycler = imagePostForRecycler;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imagePostForRowItem.setImageBitmap(byteImageToBitmap(imagePostForRecycler.get(position)));
        holder.postMessageForRowItem.setText(String.valueOf(position));
        holder.commentsForRowItem.setText(commentsForRecycler.get(position));
    }
    public Bitmap byteImageToBitmap(byte[] imgOfByteParam){
        Bitmap bitmap = BitmapFactory.decodeByteArray(imgOfByteParam, 0, imgOfByteParam.length);
        return bitmap;
    }

    @Override
    public int getItemCount() {
        return usernameForRecycler.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imagePostForRowItem;
        TextView postMessageForRowItem;
        TextView commentsForRowItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imagePostForRowItem = (ImageView) itemView.findViewById(R.id.imagePostImageView);
            postMessageForRowItem = itemView.findViewById(R.id.messagePostTextView);
            commentsForRowItem = itemView.findViewById(R.id.commentsTextView);
        }
    }
}
