package com.example.dictionary.ui.yourwords;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class YourwordsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public YourwordsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Flashcards fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}