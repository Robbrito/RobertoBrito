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
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class dni extends AppCompatActivity {
    EditText et_dni;
    Button bt_continuar;
    Button bt_borrar;
    Button bt_limpiar;
    Button bt_0;
    Button bt_1;
    Button bt_2;
    Button bt_3;
    Button bt_4;
    Button bt_5;
    Button bt_6;
    Button bt_7;
    Button bt_8;
    Button bt_9;

    String dni="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dni);



        et_dni=(EditText)findViewById(R.id.et_dni);
        bt_continuar=(Button)findViewById(R.id.bt_continuar);
        bt_borrar=(Button)findViewById(R.id.bt_borrar);
        bt_limpiar=(Button)findViewById(R.id.bt_limpiar);
        bt_0=(Button)findViewById(R.id.bt_0);
        bt_1=(Button)findViewById(R.id.bt_1);
        bt_2=(Button)findViewById(R.id.bt_2);
        bt_3=(Button)findViewById(R.id.bt_3);
        bt_4=(Button)findViewById(R.id.bt_4);
        bt_5=(Button)findViewById(R.id.bt_5);
        bt_6=(Button)findViewById(R.id.bt_6);
        bt_7=(Button)findViewById(R.id.bt_7);
        bt_8=(Button)findViewById(R.id.bt_8);
        bt_9=(Button)findViewById(R.id.bt_9);
        bt_continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_dni.getText().length()==8){
                    DatabaseReference refdata = FirebaseDatabase.getInstance().getReference();
                    DatabaseReference ref = refdata.child(et_dni.getText().toString());
                  final   ProgressDialog progress = new ProgressDialog(dni.this);
                    progress.setTitle("Comprobando DNI");
                    progress.setMessage("Espere un momento estamos comprobando su DNI...");
                    progress.show();
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.child("dni").getValue()!=null){
                                if(et_dni.getText().toString().equals(dataSnapshot.child("dni").getValue()+"")){
                                    Log.d("ComproAbel", "Entra solo una vez");
                                    Intent intent=new Intent(dni.this, principal.class);
                                    intent.putExtra("dni", dataSnapshot.child("dni").getValue().toString());
                                    startActivity(intent);
                                    et_dni.setText("");
                                }
                                finish();
                                progress.dismiss();
                            }else{
                                Log.d("ComproTito", "No existe");
                                progress.dismiss();
                                Toast.makeText(dni.this, "El DNI no esta registrado.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }

            }
        });
        bt_borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cantidad=dni.length();
                if (cantidad>0) {
                    dni = dni.substring(0, (cantidad - 1));
                    et_dni.setText(dni);
                }
            }
        });
        bt_limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_dni.setText("");
                dni="";
            }
        });
        bt_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cont=dni.length();
                if (cont<8) {
                    dni += "0";
                    et_dni.setText(dni);
                }else{
                    Toast.makeText(dni.this, "El DNI no puede contener mas de 8 digitos.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cont=dni.length();
                if (cont<8) {
                    dni += "1";
                    et_dni.setText(dni);
                }else{
                    Toast.makeText(dni.this, "El DNI no puede contener mas de 8 digitos.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        bt_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cont=dni.length();
                if (cont<8) {
                    dni += "2";
                    et_dni.setText(dni);
                }else{
                    Toast.makeText(dni.this, "El DNI no puede contener mas de 8 digitos.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        bt_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cont=dni.length();
                if (cont<8) {
                    dni += "3";
                    et_dni.setText(dni);
                }else{
                    Toast.makeText(dni.this, "El DNI no puede contener mas de 8 digitos.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        bt_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cont=dni.length();
                if (cont<8) {
                    dni += "4";
                    et_dni.setText(dni);
                }else{
                    Toast.makeText(dni.this, "El DNI no puede contener mas de 8 digitos.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cont=dni.length();
                if (cont<8) {
                    dni += "5";
                    et_dni.setText(dni);
                }else{
                    Toast.makeText(dni.this, "El DNI no puede contener mas de 8 digitos.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        bt_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cont=dni.length();
                if (cont<8) {
                    dni += "6";
                    et_dni.setText(dni);
                }else{
                    Toast.makeText(dni.this, "El DNI no puede contener mas de 8 digitos.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        bt_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cont=dni.length();
                if (cont<8) {
                    dni += "7";
                    et_dni.setText(dni);
                }else{
                    Toast.makeText(dni.this, "El DNI no puede contener mas de 8 digitos.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        bt_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cont=dni.length();
                if (cont<8) {
                    dni += "8";
                    et_dni.setText(dni);
                }else{
                    Toast.makeText(dni.this, "El DNI no puede contener mas de 8 digitos.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        bt_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cont=dni.length();
                if (cont<8) {
                    dni += "9";
                    et_dni.setText(dni);
                }else{
                    Toast.makeText(dni.this, "El DNI no puede contener mas de 8 digitos.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

}
