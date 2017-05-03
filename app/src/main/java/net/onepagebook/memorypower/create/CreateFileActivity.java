package net.onepagebook.memorypower.create;

import android.view.MenuItem;

import net.onepagebook.memorypower.R;
import net.onepagebook.memorypower.common.AbsBaseActivity;

public class CreateFileActivity extends AbsBaseActivity {
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_create;
    }

    @Override
    protected String getToolbarTitle() {
        return "파일생성";
    }

    @Override
    protected void setDisplayHomeAsUpEnabled() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
