package tech.szymanska.mypocketdoctor.home;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AddUserDialog extends Dialog {
    public AddUserDialog(@NonNull Context context) {
        super(context);
    }

    public AddUserDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected AddUserDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
