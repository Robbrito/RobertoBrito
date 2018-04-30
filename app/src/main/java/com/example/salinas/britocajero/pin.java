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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class pin extends AppCompatActivity {
    EditText et_clave;
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
    String val="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);

        final String dnix=getIntent().getStringExtra("dni");
        final String monto=getIntent().getStringExtra("monto");
        final String val=getIntent().getStringExtra("val");
        final DatabaseReference refdata = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference ref = refdata.child(getIntent().getStringExtra("dni"));
        et_clave=(EditText)findViewById(R.id.et_clave);
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
                if(et_clave.getText().length()==4){

                    final ProgressDialog progressx = new ProgressDialog(pin.this);
                    progressx.setTitle("Comprobando CLAVE");
                    progressx.setMessage("Espere un momento, estamos comprobando su CLAVE...");
                    progressx.show();
                    if (val.equals("1")) {
                        ref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                if (dataSnapshot.child("clave").getValue().toString().equals(et_clave.getText().toString())) {

                                        int suma = Integer.parseInt(dataSnapshot.child("Saldo").getValue() + "") + Integer.parseInt(monto);
                                        ref.child("Saldo").setValue(suma + "");
                                        et_clave.setText("");

                                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                                        Calendar calendar = Calendar.getInstance();

                                        Map<String, Object> map = new HashMap<String, Object>();
                                        String temp_key = ref.push().getKey();
                                        ref.updateChildren(map);
                                        DatabaseReference operaciones = ref.child(temp_key);
                                        Map<String, Object> map2 = new HashMap<String, Object>();

                                        map2.put("operacion", "Deposito");
                                        map2.put("saldo_anterior", dataSnapshot.child("Saldo").getValue() + "");
                                        map2.put("saldo_actual", suma + "");
                                        map2.put("deposito", monto);
                                        map2.put("fecha", simpleDateFormat.format(calendar.getTime()) + "");
                                        operaciones.updateChildren(map2);


                                        Intent intent = new Intent(pin.this, saldo.class);
                                        intent.putExtra("dni", dnix);

                                        intent.putExtra("val", "2");
                                        startActivity(intent);

                                        finish();
                                        progressx.dismiss();
                                } else {
                                    progressx.dismiss();
                                    Toast.makeText(pin.this, "El DNI no esta registrado.", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                    if(val.equals("2")){
                          ref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                if (dataSnapshot.child("clave").getValue().toString().equals(et_clave.getText().toString())) {
                                    if(Integer.parseInt(monto)<=Integer.parseInt(dataSnapshot.child("Saldo").getValue()+"")) {
                                    int descuento = Integer.parseInt(dataSnapshot.child("Saldo").getValue() + "") - Integer.parseInt(monto);
                                    ref.child("Saldo").setValue(descuento + "");
                                    et_clave.setText("");

                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                                    Calendar calendar = Calendar.getInstance();

                                    Map<String, Object> map = new HashMap<String, Object>();
                                    String temp_key = ref.push().getKey();
                                    ref.updateChildren(map);
                                    DatabaseReference operaciones = ref.child(temp_key);
                                    Map<String, Object> map2 = new HashMap<String, Object>();
                                    map2.put("operacion", "Retiro");
                                    map2.put("saldo_anterior", dataSnapshot.child("Saldo").getValue() + "");
                                    map2.put("saldo_actual", descuento + "");
                                    map2.put("deposito", monto);
                                    map2.put("fecha", simpleDateFormat.format(calendar.getTime()) + "");
                                    operaciones.updateChildren(map2);
                                    finish();

                                    Intent intent=new Intent(pin.this, saldo.class);
                                    intent.putExtra("dni", dnix);
                                    intent.putExtra("val", "2");
                                    startActivity(intent);

                                    progressx.dismiss();
                                    }else{
                                        progressx.dismiss();
                                        Toast.makeText(pin.this, "Lo sentimos, SALDO INSUFICIENTE.", Toast.LENGTH_SHORT).show();
                                    }

                                } else {
                                    Log.d("ComproTito", "Clave incorrecta: " + dataSnapshot.child("clave").getValue());
                                    progressx.dismiss();
                                    //Toast.makeText(pin.this, "El DNI no esta registrado.", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                    if(val.equals("3")){
                        ref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.child("clave").getValue().toString().equals(et_clave.getText().toString())) {
                                    et_clave.setText("");
                                    Intent intent=new Intent(pin.this, saldo.class);
                                    intent.putExtra("dni", getIntent().getStringExtra("dni"));
                                    intent.putExtra("val", "3");
                                    startActivity(intent);
                                    progressx.dismiss();
                                    finish();
                                } else {
                                    if (!et_clave.getText().toString().equals(""))
                                    {
                                        Toast.makeText(pin.this, "La clave ingresada es INCORRECTA.", Toast.LENGTH_SHORT).show();
                                    }
                                    progressx.dismiss();

                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                }

            }
        });

        bt_borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cantidad=dni.length();
                if (cantidad>0) {
                    dni = dni.substring(0, (cantidad - 1));
                    et_clave.setText(dni);
                }
            }
        });
        bt_limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_clave.setText("");
                dni="";
            }
        });
        bt_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cont=dni.length();
                if (cont<4) {
                    dni += "0";
                    et_clave.setText(dni);
                }else{
                    Toast.makeText(pin.this, "La CLAVE no puede contener mas de 4 digitos.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cont=dni.length();
                if (cont<4) {
                    dni += "1";
                    et_clave.setText(dni);
                }else{
                    Toast.makeText(pin.this, "La CLAVE no puede contener mas de 4 digitos.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        bt_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cont=dni.length();
                if (cont<4) {
                    dni += "2";
                    et_clave.setText(dni);
                }else{
                    Toast.makeText(pin.this, "La CLAVE no puede contener mas de 4 digitos.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        bt_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cont=dni.length();
                if (cont<4) {
                    dni += "3";
                    et_clave.setText(dni);
                }else{
                    Toast.makeText(pin.this, "La CLAVE no puede contener mas de 4 digitos.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        bt_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cont=dni.length();
                if (cont<4) {
                    dni += "4";
                    et_clave.setText(dni);
                }else{
                    Toast.makeText(pin.this, "La CLAVE no puede contener mas de 4 digitos.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cont=dni.length();
                if (cont<4) {
                    dni += "5";
                    et_clave.setText(dni);
                }else{
                    Toast.makeText(pin.this, "La CLAVE no puede contener mas de 4 digitos.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        bt_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cont=dni.length();
                if (cont<4) {
                    dni += "6";
                    et_clave.setText(dni);
                }else{
                    Toast.makeText(pin.this, "La CLAVE no puede contener mas de 4 digitos.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        bt_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cont=dni.length();
                if (cont<4) {
                    dni += "7";
                    et_clave.setText(dni);
                }else{
                    Toast.makeText(pin.this, "La CLAVE no puede contener mas de 4 digitos.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        bt_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cont=dni.length();
                if (cont<4) {
                    dni += "8";
                    et_clave.setText(dni);
                }else{
                    Toast.makeText(pin.this, "La CLAVE no puede contener mas de 4 digitos.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        bt_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cont=dni.length();
                if (cont<4) {
                    dni += "9";
                    et_clave.setText(dni);
                }else{
                    Toast.makeText(pin.this, "La CLAVE no puede contener mas de 4 digitos.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
