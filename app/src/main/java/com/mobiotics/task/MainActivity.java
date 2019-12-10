package com.mobiotics.task;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_encrypt)
    Button btnEncrypt;

    @BindView(R.id.btn_decrypt)
    Button btnDecrypt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_encrypt)
    public void onClickBtnEncrypt() {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("action", "encrypt");
        startActivity(intent);
    }


    @OnClick(R.id.btn_decrypt)
    public void onClickBtnDecrypt() {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("action", "decrypt");
        startActivity(intent);

    }


}
