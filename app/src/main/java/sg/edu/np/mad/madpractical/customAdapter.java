package sg.edu.np.mad.madpractical;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class customAdapter extends RecyclerView.Adapter<customAdapter.customViewHolder> {
    private ArrayList<User> users;
    private OnItemClickListener clickListener;
    private int imageResource;

    public customAdapter(ArrayList<User> input, int imageResource) {
        if (input != null) {
            this.users = input;
        } else {
            this.users = new ArrayList<>();
        }
        this.imageResource = imageResource;
    }

    @NonNull
    @Override
    public customViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_profiles, parent, false);
        return new customViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull customViewHolder holder, int position) {
        User user = users.get(position);
        holder.txt.setText(user.username);
        holder.textViewAdditional.setText(user.description);
        holder.image.setImageResource(imageResource);
        holder.additionalImageView.setImageResource(imageResource);

        // Get the current user's username
        String username = user.username;

// Check if the username ends with 7
        if (username.charAt(username.length()-1) == '7') {
            // Set visibility of the additional ImageView to View.VISIBLE
            holder.additionalImageView.setVisibility(View.VISIBLE);
        } else {
            // Set visibility of the additional ImageView to View.GONE
            holder.additionalImageView.setVisibility(View.GONE);
        }

        // Set click listener on the item view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    int clickedPosition = holder.getAdapterPosition();
                    clickListener.onItemClick(clickedPosition);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public static class customViewHolder extends RecyclerView.ViewHolder {
        TextView txt;
        ImageView image;
        ImageView additionalImageView;
        TextView textViewAdditional;

        public customViewHolder(View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.textView4);
            image = itemView.findViewById(R.id.icon);
            additionalImageView = itemView.findViewById(R.id.imageViewAdditional);
            textViewAdditional = itemView.findViewById(R.id.textViewAdditional);
        }
    }
}




