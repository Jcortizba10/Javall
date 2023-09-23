package com.ps.jac16.repository;
import com.ps.jac16.model.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface TransferenciaRepositorio extends JpaRepository<Transferencia,Long>  {
}
