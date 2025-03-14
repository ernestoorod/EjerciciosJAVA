package Cuenta;

public abstract class Cuenta {
    protected String dniCliente;
    protected String numeroCuenta;
    protected double saldo;

    public Cuenta(String dniCliente, String numeroCuenta, double saldoInicial) {
        this.dniCliente = dniCliente;
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
    }

    public void realizarIngreso(double cantidad) {
        saldo += cantidad;
    }

    public void realizarTransferencia(double cantidad) {
        saldo -= cantidad;
    }

    public double getSaldo() {
        return saldo;
    }

    @Override
    public String toString() {
        return "Cuenta: " + numeroCuenta + " | Cliente: " + dniCliente + " | Saldo: " + saldo;
    }
}
