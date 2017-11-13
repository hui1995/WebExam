package com.example.administrator.webexam.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.webexam.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditActivity extends AppCompatActivity {


    @Bind(R.id.edit_Info)
    EditText editInfo;
    @Bind(R.id.submit)
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String edit = bundle.getString("editinfo");
        editInfo.setText(edit);
    }

    @OnClick(R.id.submit)
    public void onViewClicked() {
        backinfo();

    }

    private void backinfo(){

        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("editinfo",editInfo.getText().toString());
        intent.putExtras(bundle);
        setResult(3,intent);
        EditActivity.this.finish();

    }
}
