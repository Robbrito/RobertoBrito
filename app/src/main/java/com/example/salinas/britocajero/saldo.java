package com.example.salinas.britocajero;

import android.graphics.Path;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class saldo extends AppCompatActivity {
    ListView lista;
    DatabaseReference refdata = FirebaseDatabase.getInstance().getReference();
    private adaptadorTransaccion adaptadorTransaccionv;
    ArrayList<TtransaccionView> modeloTransaccion = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saldo);
        final TextView tv_nombres=(TextView)findViewById(R.id.tv_nombres);
        final TextView tv_dnis=(TextView)findViewById(R.id.tv_dnis);
        Button bt_salir=(Button)findViewById(R.id.bt_salir);
        bt_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        final String dnix=getIntent().getStringExtra("dni");
        final int i=1;
        refdata.child(dnix).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tv_nombres.setText(dataSnapshot.child("dni").getValue().toString()+" "+dataSnapshot.child("nombres").getValue()+" "+dataSnapshot.child("apellidos").getValue());
                tv_dnis.setText("Saldo: S/. "+dataSnapshot.child("Saldo").getValue().toString()+".00");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        refdata.child(dnix).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                if (dataSnapshot.child("deposito").getValue()!=null)
                {
                    TtransaccionView m=new TtransaccionView();
                    m.setDeposito(dataSnapshot.child("deposito").getValue()+"");
                    m.setFecha(dataSnapshot.child("fecha").getValue()+"");
                    m.setOperacion(dataSnapshot.child("operacion").getValue()+"");
                    modeloTransaccion.add(m);
                }

                Log.d("operacionesTito", dataSnapshot.child("deposito").getValue()+"");
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


       // Collections.reverse(modeloTransaccion);
        adaptadorTransaccionv = new adaptadorTransaccion(modeloTransaccion, saldo.this);
        lista = (ListView) findViewById(R.id.lista);
        lista.setAdapter(adaptadorTransaccionv);
        lista.setSelection(lista.getCount() - 1);
        //adaptadorTransaccionv.notifyDataSetChanged();
    }

    }
