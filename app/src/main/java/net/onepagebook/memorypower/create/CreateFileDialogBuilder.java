package net.onepagebook.memorypower.create;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net.onepagebook.memorypower.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Getter;
import lombok.Setter;

public class CreateFileDialogBuilder extends AlertDialog.Builder implements CreateFilePresenter
        .View {

    @BindView(R.id.textinputlayout)
    TextInputLayout textInputLayout;
    @BindView(R.id.input_edittext)
    EditText inputEditText;
    @Getter
    AlertDialog alertDialog;
    private CreateFilePresenter mPresenter;
    private InputFilter specialFilter = (source, start, end, dest, dstart, dend) -> {

        for (int i = start; i < end; i++) {
            if (!Character.isLetterOrDigit(source.charAt(i))) {
                return "";
            }
        }
        return null;
    };
    @Setter
    private OnDialogListener onDialogListener;

    public CreateFileDialogBuilder(@NonNull Context context) {
        super(context);
        mPresenter = new CreateFilePresenterImpl(this);
        mPresenter.init();
    }

    @Override
    public void setContentView() {
        setView(LayoutInflater.from(getContext()).inflate(R.layout.dialog_create, null));
    }

    @Override
    public void setInputFilter() {
        inputEditText.setFilters(new InputFilter[]{specialFilter});
    }

    @Override
    public void addListener() {
        setPositiveButton(android.R.string.ok, null);
        setNegativeButton(android.R.string.cancel, null);
    }

    @Override
    public void setTitleBar() {
        setTitle(R.string.create_file);
        setIcon(R.drawable.ic_menu_file_add);
    }

    @Override
    public AlertDialog.Builder setView(View view) {
        ButterKnife.bind(this, view);
        return super.setView(view);
    }

    @Override
    public void setInputError(@StringRes int resId) {
        textInputLayout.setError(getContext().getString(resId));
        inputEditText.setText("");
    }

    @Override
    public AlertDialog create() {
        alertDialog = super.create();
        alertDialog.setOnShowListener(onShowListener());
        return alertDialog;
    }

    private DialogInterface.OnShowListener onShowListener() {
        return dialog -> {
            Button ok = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
            ok.setOnClickListener(v -> mPresenter.onClickOk(inputEditText.getText().toString().trim()));
        };
    }

    @Override
    public void setId(String id) {
        if (onDialogListener != null) {
            onDialogListener.onSuccess(id);
        }
    }

    @Override
    public void dismiss() {
        alertDialog.dismiss();
    }

    public void showAlertDialog() {
        alertDialog.show();
    }

    public interface OnDialogListener {
        void onSuccess(String id);
    }
}
