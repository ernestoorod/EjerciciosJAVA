package Cuenta;

public class CuentaNomina extends Cuenta {
    public CuentaNomina(String dniCliente, String numeroCuenta, double saldoInicial) {
        super(dniCliente, numeroCuenta, saldoInicial);
    }

    @Override
    public void realizarIngreso(double cantidad) {
        if (cantidad > 1500) {
            saldo += cantidad * 1.01;  // Aumenta un 1%
        } else {
            saldo += cantidad;
        }
    }

    @Override
    public void realizarTransferencia(double cantidad) {
        saldo -= (cantidad + 0.05); // Cobra comisión de 0.05€
    }
}
