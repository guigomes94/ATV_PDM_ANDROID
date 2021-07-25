package com.example.deviceinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView hardInfo;
    private TextView descricao;
    private Button btn;
    private Info info = new Info();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.hardInfo = findViewById(R.id.infoId);
        this.descricao = findViewById(R.id.descricao);
        this.btn = findViewById(R.id.btnSobre);

        this.hardInfo.setText(info.getHardwareInfo());
        this.descricao.setText("...");

        this.btn.setOnClickListener(new tratarClick());
    }

    class tratarClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            descricao.setText("Nome do processador.");
        }
    }
}