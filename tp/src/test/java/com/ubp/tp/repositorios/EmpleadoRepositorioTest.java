package com.ubp.tp.repositorios;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ubp.tp.models.EmpleadoModel;
import com.ubp.tp.repositories.EmpleadoRepositorio;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmpleadoRepositorioTest {

	@Autowired
	private EmpleadoRepositorio empleadoRepositorio;
	
	private EmpleadoModel empleado; 
	@BeforeEach
	void setup() {
		 empleado = new EmpleadoModel("name generico","direccion generica","trabajo generico","empresa generica");
	}
	@DisplayName("Test GuardarEmpleado")
	@Test
	void testGuardarEmpleado() {		
		EmpleadoModel empleadoBD = empleadoRepositorio.save(empleado);
		assertThat(empleadoBD).isNotNull();
		assertThat(empleadoBD.getId()).isGreaterThan(0);
	}
	
	@DisplayName("Test Listar Empleado")
	@Test
	void testListarEmpleado() {
		EmpleadoModel empleadoTesLisA = new EmpleadoModel ("name generico Seba","direccion generica Guemes","trabajo generico Develop","empresa generica Dicsys"); 
		EmpleadoModel empleadoTesLisB = new EmpleadoModel ("name generico Daniel","direccion generica Observatorio","trabajo generico PQA","empresa generica Dicsys"); 
		EmpleadoModel empleadoTesLisC = new EmpleadoModel ("name generico Juan","direccion generica Alberdi","trabajo generico Desa","empresa generica Dicsys");
 
	    empleadoRepositorio.save(empleado);
		empleadoRepositorio.save(empleadoTesLisA);
		empleadoRepositorio.save(empleadoTesLisB);
		empleadoRepositorio.save(empleadoTesLisC);
		
		List<EmpleadoModel> listEmpBD = empleadoRepositorio.findAll();
		assertThat(listEmpBD).isNotNull();
		assertThat(listEmpBD.size()).isEqualTo(4);
	}
	
	@DisplayName("Test Buscar Empleado por ID")
	@Test
	void testActualizarEmpleadoId() {
		 empleadoRepositorio.save(empleado);
		 EmpleadoModel empleadoBD = empleadoRepositorio.save(empleado);
		 EmpleadoModel listEmpBDRespuesta = empleadoRepositorio.findById(empleadoBD.getId()).get();
		 assertThat(listEmpBDRespuesta).isNotNull();
	}
	
	@DisplayName("Test ActualizarEmpleado")
	@Test
	void testActualizarEmpleado() {
		 empleadoRepositorio.save(empleado);
		 EmpleadoModel listEmpBD = empleadoRepositorio.findById(empleado.getId()).get();
		 listEmpBD.setDireccion("Direccion Cambiada");
		 listEmpBD.setEmpresa("Empresa Cambiada");
		 listEmpBD.setName("Nombre actualizado");		 
		 listEmpBD.setTrabajo("Trabajo actualizado");
		 
		 EmpleadoModel empleadoActualizado =  empleadoRepositorio.save(listEmpBD);
		 assertThat(empleadoActualizado.getDireccion()).isEqualTo("Direccion Cambiada");
		 assertThat(empleadoActualizado.getEmpresa()).isEqualTo("Empresa Cambiada");
		 assertThat(empleadoActualizado.getName()).isEqualTo("Nombre actualizado");
		 assertThat(empleadoActualizado.getTrabajo()).isEqualTo("Trabajo actualizado");
		 
	}

	@DisplayName("Test Eliminar Empleado")
	@Test
	void testEliminarEmpleado() {
		empleadoRepositorio.save(empleado);
		empleadoRepositorio.deleteById(empleado.getId());
		 Optional<EmpleadoModel> listEmpBDEmpleadoBorrado = empleadoRepositorio.findById(empleado.getId());
		 assertThat(listEmpBDEmpleadoBorrado).isEmpty();
	}
	
	
}
