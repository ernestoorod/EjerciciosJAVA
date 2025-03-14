package Cuenta;

public class CuentaAhorro extends Cuenta {
    private double comisionesCobradas;

    public CuentaAhorro(String dniCliente, String numeroCuenta, double saldoInicial) {
        super(dniCliente, numeroCuenta, saldoInicial);
        this.comisionesCobradas = 0;
    }

    public void realizarTransferenciaConComision(double cantidad) {
        double comision = cantidad * 0.01;
        saldo -= (cantidad + comision);
        comisionesCobradas += comision;
    }

    public double verComisionesCobradas() {
        return comisionesCobradas;
    }
}
