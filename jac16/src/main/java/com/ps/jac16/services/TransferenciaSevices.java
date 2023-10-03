package com.ps.jac16.services;
import com.ps.jac16.model.Transferencia;
import com.ps.jac16.model.Cuenta;
import com.ps.jac16.repository.CuentaRepository;
import com.ps.jac16.repository.TransferenciaRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
@Service
public class TransferenciaSevices {
    @Autowired
    CuentaRepository cuentaRepository;
    @Autowired
    TransferenciaRepository transferenciaRepository;

    public Transferencia save(Transferencia transferencia) throws Exception{
        Cuenta  cuentaOrigenBanco =cuentaRepository.findByNumeroCuenta(transferencia.getCuentaOrigen().getNumeroCuenta());

        Cuenta cuentaDestinoBanco =cuentaRepository.findByNumeroCuenta(transferencia.getCuentaDestino().getNumeroCuenta());

        if(cuentaOrigenBanco == null && cuentaDestinoBanco == null) {
            throw new Exception("Las cuentas origen y destino no Existen");
        }
        if (!StringUtils.isNumeric(transferencia.getCuentaDestino().getNumeroCuenta()) || !StringUtils.isNumeric(transferencia.getCuentaDestino().getNumeroCuenta())) {
            throw new Exception("Las cuentas deben ser numericas.");
        }

        if (cuentaOrigenBanco.getSaldo().compareTo(BigDecimal.ZERO) < transferencia.getMonto().compareTo(BigDecimal.ZERO)){
            throw new Exception("El saldo de la cuenta origen debe ser mayor al monto a transferir" + transferencia.getMonto());
        }

        return transferenciaRepository.save(transferencia);
    }
    public Transferencia get(Long idTransferencia) throws  Exception{

        return transferenciaRepository.findById(idTransferencia).get();
    }

    public void delete(Transferencia transferencia){ transferenciaRepository.delete(transferencia);
    }
}
