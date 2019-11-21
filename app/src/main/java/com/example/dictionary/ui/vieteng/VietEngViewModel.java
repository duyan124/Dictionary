package com.example.dictionary.ui.vieteng;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VietEngViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public VietEngViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Viet-Eng fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}