package com.ubp.tp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ubp.tp.models.EmpleadoModel;

public interface EmpleadoRepositorio extends JpaRepository<EmpleadoModel,Integer> {
	

}
