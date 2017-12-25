package wego.com;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import wego.com.widget.CustomDialog;

public class Main2Activity extends AppCompatActivity {

    private Button pause_btn;
    private Button load_btn;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        pause_btn=findViewById(R.id.pause_btn);
        load_btn=findViewById(R.id.load_btn);

        initDialog();

        pause_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.dismiss();
            }
        });

        load_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
            }
        });
    }

    private void initDialog(){
//        if(progressDialog==null){
//            progressDialog=new ProgressDialog(this);
//            progressDialog.setMessage("加载中...");
//            progressDialog.setCancelable(true);
//            progressDialog.setCanceledOnTouchOutside(false);
//        }

        CustomDialog.Companion.showDialog(this);

    }
}
