package net.onepagebook.memorypower.add;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net.onepagebook.memorypower.R;
import net.onepagebook.memorypower.create.CreateFileDialogBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Getter;
import lombok.Setter;

public class ItemAddDialogBuilder extends AlertDialog.Builder implements ItemAddPresenter.View {
    @BindView(R.id.textinputlayout)
    TextInputLayout textInputLayout;
    @BindView(R.id.input_edittext)
    EditText inputEditText;
    @BindView(R.id.textinputlayout2)
    TextInputLayout textInputLayout2;
    @BindView(R.id.input_edittext2)
    EditText inputEditText2;
    @Getter
    AlertDialog alertDialog;
    private ItemAddPresenter mPresenter;

    public ItemAddDialogBuilder(@NonNull Context context) {
        super(context);
        mPresenter = new ItemAddPresenterImpl(this);
        mPresenter.init();
    }

    @Override
    public void setContentView() {
        setView(LayoutInflater.from(getContext()).inflate(R.layout.dialog_item_add, null));
    }

    @Override
    public AlertDialog.Builder setView(View view) {
        ButterKnife.bind(this, view);
        return super.setView(view);
    }

    @Override
    public void setInputFilter() {
        // do something : Text input type?
    }

    @Override
    public void addListener() {
        setPositiveButton(android.R.string.ok, null);
        setNegativeButton(android.R.string.cancel, null);
    }

    @Override
    public void setTitleBar() {
        setTitle(R.string.item_add);
        setIcon(R.drawable.ic_menu_item_add);
    }

    @Override
    public void setInputError(@StringRes int resId) {
        textInputLayout.setError(getContext().getString(resId));
        inputEditText.setText("");
    }

    @Override
    public void setInputError2(@StringRes int resId) {
        textInputLayout2.setError(getContext().getString(resId));
        inputEditText2.setText("");
    }
    @Setter
    private CreateFileDialogBuilder.OnDialogListener onDialogListener;

    @Override
    public void dismiss() {
        alertDialog.dismiss();
        onDialogListener.onSuccess(null);
    }

    public void showAlertDialog() {
        alertDialog.show();
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
            ok.setOnClickListener(v -> mPresenter.onClickOk(
                    inputEditText.getText().toString().trim(),
                    inputEditText2.getText().toString().trim()));
        };
    }

    public void setNoteId(String noteId) {
        mPresenter.setNoteId(noteId);
    }

}
