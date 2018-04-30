package com.example.salinas.britocajero;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class retiro extends AppCompatActivity {
    Button bt_100;
    Button bt_500;
    Button bt_1000;
    Button bt_5000;
    Button bt_10000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retiro);
        final String dni=getIntent().getStringExtra("dni");
        DatabaseReference refdata = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = refdata.child(getIntent().getStringExtra("dni"));
        bt_100=(Button)findViewById(R.id.bt_100);
        bt_500=(Button)findViewById(R.id.bt_500);
        bt_1000=(Button)findViewById(R.id.bt_1000);
        bt_5000=(Button)findViewById(R.id.bt_5000);
        bt_10000=(Button)findViewById(R.id.bt_10000);
        Button bt_salir=(Button)findViewById(R.id.bt_salir);
        bt_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        bt_100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(retiro.this, pin.class);
                intent.putExtra("dni", dni);
                intent.putExtra("monto", "100");
                intent.putExtra("val", "2");
                startActivity(intent);
                finish();
            }
        });
        bt_500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(retiro.this, pin.class);
                intent.putExtra("dni", dni);
                intent.putExtra("monto", "500");
                intent.putExtra("val", "2");
                startActivity(intent);
                finish();
            }
        });
        bt_1000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(retiro.this, pin.class);
                intent.putExtra("dni", dni);
                intent.putExtra("monto", "1000");
                intent.putExtra("val", "2");
                startActivity(intent);
                finish();
            }
        });
        bt_5000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(retiro.this, pin.class);
                intent.putExtra("dni", dni);
                intent.putExtra("monto", "5000");
                intent.putExtra("val", "2");
                startActivity(intent);
                finish();
            }
        });
        bt_10000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(retiro.this, pin.class);
                intent.putExtra("dni", dni);
                intent.putExtra("monto", "10000");
                intent.putExtra("val", "2");
                startActivity(intent);
                finish();
            }
        });
    }

}
