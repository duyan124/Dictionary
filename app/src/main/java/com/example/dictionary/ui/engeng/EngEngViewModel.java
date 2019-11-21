package com.example.dictionary.ui.engeng;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EngEngViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EngEngViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Eng-Eng fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}