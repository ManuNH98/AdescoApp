package com.navarromanuel.adescoapp.ui.parcela;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ParcelaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ParcelaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is parcela fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}