package com.example.salinas.britocajero;

public class TtransaccionView {

    private String deposito;
    private String fecha;
    private String operacion;

    public void setDeposito(String deposito) {
        this.deposito = deposito;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getDeposito() {
        return deposito;
    }

    public String getFecha() {
        return fecha;
    }

    public String getOperacion() {
        return operacion;
    }


}
