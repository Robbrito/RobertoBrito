package com.example.salinas.britocajero;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class principal extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        DatabaseReference refdata = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = refdata.child(getIntent().getStringExtra("dni"));

        final TextView tv_nombres=(TextView)findViewById(R.id.tv_nombres);
        final TextView tv_dnis=(TextView)findViewById(R.id.tv_dnis);
        Button bt_deposito=(Button) findViewById(R.id.bt_deposito);
        Button bt_retiro=(Button)findViewById(R.id.bt_retiro);
        Button bt_saldo=(Button)findViewById(R.id.bt_saldo);
        Button bt_salir=(Button)findViewById(R.id.bt_salir);
        bt_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        bt_deposito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(principal.this, deposito.class);
                intent.putExtra("dni", getIntent().getStringExtra("dni"));
                intent.putExtra("val", "1");
                startActivity(intent);
            }
        });
        bt_retiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(principal.this, retiro.class);
                intent.putExtra("dni", getIntent().getStringExtra("dni"));
                intent.putExtra("val", "2");
                startActivity(intent);
            }
        });
        bt_saldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(principal.this, pin.class);
                intent.putExtra("dni", getIntent().getStringExtra("dni"));
                intent.putExtra("val", "3");
                startActivity(intent);
            }
        });
        final ProgressDialog progress = new ProgressDialog(principal.this);
        progress.setTitle("Cargando datos");
        progress.setMessage("Espere un momento estamos cargando sus datos...");
        progress.show();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tv_nombres.setText(dataSnapshot.child("nombres").getValue()+" "+dataSnapshot.child("apellidos").getValue());
                tv_dnis.setText(dataSnapshot.child("dni").getValue().toString());
                progress.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
