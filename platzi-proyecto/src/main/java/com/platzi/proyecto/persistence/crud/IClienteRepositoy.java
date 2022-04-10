package com.platzi.proyecto.persistence.crud;

import com.platzi.proyecto.persistence.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteRepositoy extends JpaRepository<Cliente, Integer> {
}
