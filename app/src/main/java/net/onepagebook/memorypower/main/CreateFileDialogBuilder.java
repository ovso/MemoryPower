package net.onepagebook.memorypower.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;

import net.onepagebook.memorypower.R;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateFileDialogBuilder extends AlertDialog.Builder {

    @BindView(R.id.textinputlayout)
    TextInputLayout textInputLayout;
    @BindView(R.id.input_edittext)
    EditText inputEditText;
    private InputFilter numberAlpabetFilter = (source, start, end, dest, dstart, dend) -> {


        Pattern ps = Pattern.compile("^[a-zA-Z0-9]+$");

        if (!ps.matcher(source).matches()) {

            return "";

        }

        return null;

    };
    private InputFilter koFilter = (source, start, end, dest, dstart, dend) -> {


        Pattern ps = Pattern.compile("^[ㄱ-가-힣]+$");

        if (!ps.matcher(source).matches()) {

            return "";

        }

        return null;

    };
    private InputFilter specialFilter = (source, start, end, dest, dstart, dend) -> {

        for (int i = start; i < end; i++) {
            if (!Character.isLetterOrDigit(source.charAt(i))) {
                return "";
            }
        }
        return null;
    };

    public CreateFileDialogBuilder(@NonNull Context context) {
        super(context);
    }

    public CreateFileDialogBuilder(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    @Override
    public AlertDialog.Builder setView(View view) {
        ButterKnife.bind(this, view);
        initialize();
        return super.setView(view);
    }

    private void initialize() {
        inputEditText.setFilters(new InputFilter[]{specialFilter});
    }

    public void setError(@StringRes int resId) {
        textInputLayout.setError(getContext().getString(resId));
        inputEditText.setText("");
    }

    public void setHint(@StringRes int resId) {
        inputEditText.setHint(resId);
    }

    public String getInputText() {
        return inputEditText.getText().toString().trim();
    }

}
