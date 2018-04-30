package com.example.salinas.britocajero;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Salinas on 11/02/2017.
 */

public class adaptadorTransaccion extends BaseAdapter {
    private Context context;
    private ArrayList<TtransaccionView> arrayList;
    public adaptadorTransaccion(ArrayList<TtransaccionView> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(getCount() - position - 1);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.activity_modelo_transaccion, parent, false);
        }

        ImageView iv_left=(ImageView)convertView.findViewById(R.id.iv_left);
        TextView tv_transaccion=(TextView)convertView.findViewById(R.id.tv_transaccion);
        TextView tv_monto=(TextView)convertView.findViewById(R.id.tv_monto);
        String operacion=arrayList.get(position).getOperacion();
        String fecha_monto=arrayList.get(position).getFecha()+" ... S/. "+arrayList.get(position).getDeposito()+".00";

        tv_transaccion.setText(operacion+"");
        tv_monto.setText(fecha_monto);
        if (operacion.equals("Deposito")){
            iv_left.setImageResource(R.mipmap.arrow_up);
        }else{
            iv_left.setImageResource(R.mipmap.arrow_down);
        }
        return convertView;
    }
}
