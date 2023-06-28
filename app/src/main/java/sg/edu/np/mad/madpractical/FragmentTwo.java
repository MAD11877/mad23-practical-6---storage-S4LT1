package sg.edu.np.mad.madpractical;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import sg.edu.np.mad.madpractical.R;

public class FragmentTwo extends Fragment {

    public FragmentTwo(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.group2, container, false);

        // Initialize your fragment's UI components and handle any other setup
        ImageView image = view.findViewById(R.id.icon);

        return view;
    }
}