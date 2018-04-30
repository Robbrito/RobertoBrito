package com.example.salinas.britocajero;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        ImageView img_load=(ImageView)findViewById(R.id.img_load);
        img_load.setImageResource(R.drawable.tarjeta_animada);
        AnimationDrawable frameAnimation=(AnimationDrawable)img_load.getBackground();
        frameAnimation.start();
*/
        Button bt_ingresar_tarjeta=(Button)findViewById(R.id.bt_ingresar_tarjeta);
        bt_ingresar_tarjeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, dni.class);
                startActivity(intent);
                finish();
            }
        });

    }


}
