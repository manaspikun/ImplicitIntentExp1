package com.techpalle.implicitintentexp1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button button1,button2,button3;
    public static final int REQ_CD=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1= (Button) findViewById(R.id.button1);
        button2= (Button) findViewById(R.id.button2);
        button3= (Button) findViewById(R.id.button3);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open dialer application-9853269886
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_CALL);
                Uri myuri=Uri.parse("tel:9853269886");
                intent.setData(myuri);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent();
                in.setAction(Intent.ACTION_VIEW);
                Uri myuri=Uri.parse("http://skillgun.com");
                in.setData(myuri);
                startActivity(in);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                //intent.setAction(Intent.ACTION_VIEW);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");//(intent.setType("images/png");)
                //startActivity(intent);
                startActivityForResult(intent,REQ_CD);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==REQ_CD && resultCode==RESULT_OK){
            Uri imagepath=data.getData();
            Bitmap image=null;
            try {
                image=MediaStore.Images.Media.getBitmap(getContentResolver(),imagepath);
            } catch (IOException e) {
                e.printStackTrace();
            }
//            Bundle bundle=data.getExtras();
//            String imagepath=bundle.getString("data");
//            Bitmap image= BitmapFactory.decodeFile(imagepath);
            ImageView imageView= (ImageView) findViewById(R.id.imageview);
            imageView.setImageBitmap(image);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
