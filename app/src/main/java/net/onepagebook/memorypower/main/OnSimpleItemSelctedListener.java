package net.onepagebook.memorypower.main;

import android.view.View;
import android.widget.AdapterView;

public abstract class OnSimpleItemSelctedListener implements AdapterView.OnItemSelectedListener {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        onItemSelected(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public abstract void onItemSelected(int position);
}
