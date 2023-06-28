package sg.edu.np.mad.madpractical;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import sg.edu.np.mad.madpractical.R;

public class FragmentOne extends Fragment {

    public FragmentOne() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.group1, container, false);

        // Initialize your fragment's UI components and handle any other setup
        TextView textView = view.findViewById(R.id.textView3);

        return view;
    }
}
