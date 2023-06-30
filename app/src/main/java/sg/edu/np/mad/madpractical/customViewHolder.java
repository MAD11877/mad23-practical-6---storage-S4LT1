package sg.edu.np.mad.madpractical;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class customViewHolder extends RecyclerView.ViewHolder {

    TextView txt;
    TextView text;
    ImageView image;
    ImageView additionalImageView;

    public customViewHolder(View itemView){
        super(itemView);
        txt = itemView.findViewById(R.id.textView4);
        text = itemView.findViewById(R.id.textViewAdditional);
        image = itemView.findViewById(R.id.icon);
        additionalImageView = itemView.findViewById(R.id.imageViewAdditional);
    }
}