package redditreader.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

/**
 * Created by dkholodniak on 12.06.17.
 */

public class SearchFormFragment extends Fragment implements View.OnClickListener {
    Button search_button;
    EditText editText;
    List<RedditResponse.RedditData> result;



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_form_fragment_layout, container, false);
        search_button = (Button) view.findViewById(R.id.search_button);
        editText = (EditText) view.findViewById(R.id.subredit_search_form);
        search_button.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, SubreditFragment.CreateInstance(editText.getText().toString())).addToBackStack(SubreditFragment.class.getName()).commit();
  }







    }



