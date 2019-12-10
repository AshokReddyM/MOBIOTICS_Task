package com.mobiotics.task;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResultActivity extends AppCompatActivity {

    @BindView(R.id.et_input)
    EditText etInput;

    @BindView(R.id.btn_submit)
    Button btnSubmit;

    @BindView(R.id.tv_result)
    TextView tvResult;

    @BindView(R.id.btn_note)
    Button note;

    private String intentAction;

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        assert imm != null;
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);
        intentAction = getIntent().getStringExtra("action");


    }

    @OnClick(R.id.btn_submit)
    public void onClickSubmit() {
        if (intentAction != null) {
            if (intentAction.equals("encrypt")) {
                if (!etInput.getText().toString().trim().equals("")) {
                    String encryptedData = EncryptDecryptUtil.encrypt(etInput.getText().toString().trim(), "key_secret_007");
                    tvResult.setText(encryptedData);
                    note.setVisibility(View.VISIBLE);
                } else {
                    tvResult.setError("Should not be empty");
                }
            } else {
                if (!etInput.getText().toString().trim().equals("")) {
                    String decryptedData = EncryptDecryptUtil.decrypt(etInput.getText().toString().trim(), "key_secret_007");
                    tvResult.setText(decryptedData);
                    note.setVisibility(View.VISIBLE);
                } else {
                    tvResult.setError("Should not be empty");
                }
            }

            //hiding keyboard after submit
            hideKeyboard(this);
        }
    }

    @OnClick(R.id.btn_note)
    public void onClickCopyResult() {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Result", tvResult.getText().toString());
        assert clipboard != null;
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, "Copied", Toast.LENGTH_SHORT).show();
    }


}
