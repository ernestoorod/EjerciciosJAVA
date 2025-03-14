package Cuenta;

public class CuentaVivienda extends Cuenta {
    private double hipotecaPendiente;

    public CuentaVivienda(String dniCliente, String numeroCuenta, double saldoInicial, double hipotecaPendiente) {
        super(dniCliente, numeroCuenta, saldoInicial);
        this.hipotecaPendiente = hipotecaPendiente;
    }

    public void cancelarImporteHipoteca(double cantidad) {
        if (cantidad <= hipotecaPendiente) {
            hipotecaPendiente -= cantidad;
        }
    }

    public double verRestoHipoteca() {
        return hipotecaPendiente;
    }
}
