package com.example.dictionary.ui.engviet;

import android.app.ProgressDialog;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

import android.widget.ListView;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.dictionary.AddWord;
import com.example.dictionary.CustomAdapter;
import com.example.dictionary.DatabaseAccess;
import com.example.dictionary.Definition1;
import com.example.dictionary.R;
import com.example.dictionary.Vocabulary;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class EngVietFragment extends Fragment {
    AutoCompleteTextView searchW1;
    ListView listView1;
    DatabaseAccess db1;
    FloatingActionButton btnAdd1;
    ArrayList<Vocabulary> list,wordOne1;
    CustomAdapter adapterOne1,adapterSearch1;
    private int mCurrentPage = 1;
    private int mItemPerRow = 20;

    private boolean isLoadMore = false;
    private Handler mHandler = new Handler();
    private ProgressDialog mLoading;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_engviet, container, false);

        mLoading = new ProgressDialog(getContext());
        mLoading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mLoading.setMessage("Loading....");

        searchW1 = (AutoCompleteTextView) root.findViewById(R.id.edt_search);
        listView1 = (ListView) root.findViewById(R.id.lv_engviet);
        btnAdd1 = (FloatingActionButton) root.findViewById(R.id.btn_add1);

        db1 = new DatabaseAccess(getContext());
        list = new ArrayList<>();
        db1.open1();
        list= db1.getWords1();
        db1.close1();

        adapterSearch1= new CustomAdapter(getContext(), R.layout.row_listview, list);
        searchW1.setAdapter(adapterSearch1);
        searchW1.setThreshold(1);

        wordOne1 =new ArrayList<Vocabulary>();
        getData();
        adapterOne1 = new CustomAdapter(getContext(), R.layout.row_listview, wordOne1);
        listView1.setAdapter(adapterOne1);

        searchW1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), Definition1.class);
                String word, definition;
                word = list.get(i).getWord();
                db1.open1();
                definition = db1.getDefinition1(word);
                db1.close1();
                intent.putExtra("w", word);
                intent.putExtra("df", definition);
                startActivity(intent);
            }
        });

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), Definition1.class);
                String word, definition;
                word = list.get(i).getWord();
                db1.open1();
                definition = db1.getDefinition1(word);
                db1.close1();
                intent.putExtra("w", word);
                intent.putExtra("df", definition);
                startActivity(intent);
            }
        });

        listView1.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollstate) {
                }
            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int VisibleItemCount, int TotalItemCount) {
                int last= firstVisibleItem+VisibleItemCount;
                if ((last == TotalItemCount) && !isLoadMore && (firstVisibleItem != 0)) {
                    isLoadMore = true;
                    mLoading.show();

                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getData();
                            adapterOne1.notifyDataSetChanged();
                            isLoadMore = false;
                            mLoading.dismiss();
                        }
                    }, 3000);
                }
            }
        });
        btnAdd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddWord.class);
                startActivity(intent);
            }
        });
        return root;
    }
    public void getData() {
        if (mItemPerRow * mCurrentPage >= list.size()) {
            Toast.makeText(getContext(), "No Data", Toast.LENGTH_SHORT).show();
            return;
        }
        for (int i = 0; i < mItemPerRow; i++) {
            wordOne1.add(list.get(i + (( mCurrentPage - 1) * mItemPerRow)));
        }
        mCurrentPage +=1;
    }
}
