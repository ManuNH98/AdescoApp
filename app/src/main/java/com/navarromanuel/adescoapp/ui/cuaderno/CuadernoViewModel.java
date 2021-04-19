package com.navarromanuel.adescoapp.ui.cuaderno;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CuadernoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CuadernoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is cuaderno fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}