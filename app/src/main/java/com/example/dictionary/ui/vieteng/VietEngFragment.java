package com.example.dictionary.ui.vieteng;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.dictionary.CustomAdapter;
import com.example.dictionary.DatabaseAccess;
import com.example.dictionary.Definition2;
import com.example.dictionary.R;
import com.example.dictionary.Vocabulary;

import java.util.ArrayList;

public class VietEngFragment extends Fragment {
    AutoCompleteTextView searchW2;
    ListView listView2;
    DatabaseAccess db2;
    CustomAdapter adapterSearch2,adapterOne2;
    ArrayList<Vocabulary> list2,wordOne2;

    private int mCurrentPage2 = 1;
    private int mItemPerRow2 = 20;

    private boolean isLoadMore2 = false;
    private Handler mHandler2 = new Handler();
    private ProgressDialog mLoading;

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_vieteng, container, false);

        listView2 = (ListView) root.findViewById(R.id.lv_vieteng);
        searchW2 =(AutoCompleteTextView) root.findViewById(R.id.edt_search);

        mLoading = new ProgressDialog(getContext());
        mLoading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mLoading.setMessage("Loading....");

        db2 = DatabaseAccess.getInstance(getContext());
        list2 = new ArrayList<>();
        db2.open2();
        list2 = db2.getWords2();
        db2.close2();

        adapterSearch2= new CustomAdapter(getContext(), R.layout.row_listview, list2);
        searchW2.setAdapter(adapterSearch2);
        searchW2.setThreshold(1);

        wordOne2 =new ArrayList<Vocabulary>();
        getData();
        adapterOne2 = new CustomAdapter(getContext(),R.layout.row_listview, wordOne2);
        listView2.setAdapter(adapterOne2);

        searchW2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), Definition2.class);
                String word, definition;
                word = list2.get(i).getWord();
                db2.open2();
                definition = db2.getDefinition2(word);
                db2.close2();
                intent.putExtra("tu", word);
                intent.putExtra("dn", definition);
                startActivity(intent);
            }
        });
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), Definition2.class);
                String word= list2.get(i).getWord();
                db2.open2();
                String definition= db2.getDefinition2(word);
                db2.close2();
                intent.putExtra("tu",word);
                intent.putExtra("dn",definition);
                startActivity(intent);
            }
        });
        listView2.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollstate) {
            }
            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int VisibleItemCount, int TotalItemCount) {
                int last= firstVisibleItem+VisibleItemCount;
                if ((last == TotalItemCount) && !isLoadMore2 && (firstVisibleItem != 0)) {
                    isLoadMore2 = true;
                    mLoading.show();

                    mHandler2.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getData();
                            adapterOne2.notifyDataSetChanged();
                            isLoadMore2 = false;
                            mLoading.dismiss();
                        }
                    }, 1000);
                }
            }
        });
        return root;
    }

    public void getData() {
        if (mItemPerRow2 * mCurrentPage2 >= list2.size()) {
            Toast.makeText(getContext(), "No Data", Toast.LENGTH_SHORT).show();
            return;
        }
        for (int i = 0; i < mItemPerRow2; i++) {
            wordOne2.add(list2.get(i + (( mCurrentPage2 - 1) * mItemPerRow2)) );
        }
        mCurrentPage2 +=1;
    }
}