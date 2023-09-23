import com.ps.jac16.model.Transferencia;
import com.ps.jac16.model.Cuenta;
import com.ps.jac16.repository.CuentaRepository;
import com.ps.jac16.repository.TransferenciaRepositorio;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransferenciaServicies {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private TransferenciaRepositorio transferenciaRepositorio;

    public Transferencia save(Transferencia transferencia) throws Exception {
        // Busca las cuentas de origen y destino por número de cuenta
        Cuenta cuentaOrigenBanco = cuentaRepository.findByNumeroCuenta(transferencia.getCuentaOrigen().getNumeroCuenta());
        Cuenta cuentaDestinoBanco = cuentaRepository.findByNumeroCuenta(transferencia.getCuentaDestino().getNumeroCuenta());

        // Verifica si las cuentas de origen y destino existen
        if (cuentaOrigenBanco == null ) {
            throw new Exception("Una de las cuentas no existe."+ transferencia.getCuentaOrigen().getNumeroCuenta());
        }
        if (cuentaDestinoBanco == null) {
            throw new Exception("Una de las cuentas no existe."+ transferencia.getCuentaDestino().getNumeroCuenta());
        }
        // Realiza las validaciones necesarias
        if (!StringUtils.isNumeric(transferencia.getCuentaOrigen().getNumeroCuenta())
                || !StringUtils.isNumeric(transferencia.getCuentaDestino().getNumeroCuenta())) {
            throw new Exception("Los números de cuenta deben ser numéricos.");
        }

        if (transferencia.getMonto().compareTo(BigDecimal.ZERO) <= 0) {
            throw new Exception("El monto de la transferencia debe ser mayor a 0.");
        }

        // Realiza la lógica de la transferencia aquí si es necesario

        // Guarda la transferencia
        return transferenciaRepositorio.save(transferencia);
    }



    public Cuenta update(Cuenta cuenta) throws Exception {
        // Utiliza el método findByNumeroCuenta para buscar una cuenta por su número de cuenta
        Cuenta cuentaExistente = cuentaRepository.findByNumeroCuenta(cuenta.getNumeroCuenta());

        if (cuentaExistente != null) {
            // Actualiza los datos de la cuenta existente con los nuevos valores
            cuentaExistente.setSaldo(cuenta.getSaldo());
            // Agrega más campos que necesites actualizar

            // Guarda la cuenta actualizada
            return cuentaRepository.save(cuentaExistente);
        } else {
            throw new Exception("No existe una cuenta asociada al número: " + cuenta.getNumeroCuenta());
        }
    }

    public void delete(Cuenta cuenta) {
        // Borra la cuenta utilizando el método delete
        cuentaRepository.delete(cuenta);
    }
}
