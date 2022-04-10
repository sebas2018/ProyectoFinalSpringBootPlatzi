package com.platzi.proyecto.persistence.crud;

import com.platzi.proyecto.persistence.entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ICompraRepository extends JpaRepository<Compra, Integer> {



    Optional<List<Compra>> findByIdCliente(Integer idCliente);

}
