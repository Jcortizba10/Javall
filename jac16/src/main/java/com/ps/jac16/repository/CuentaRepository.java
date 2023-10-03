package com.ps.jac16.repository;

import com.ps.jac16.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CuentaRepository extends JpaRepository<Cuenta,Long> {
    Cuenta findByNumeroCuenta(String numeroCuenta);
    void deleteByNumeroCuenta(String numeroCuenta);
}
