package com.example.dictionary.ui.engviet;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EngVietViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EngVietViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Eng-Viet fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}