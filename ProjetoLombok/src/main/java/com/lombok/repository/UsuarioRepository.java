package com.lombok.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lombok.entities.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> {

}
