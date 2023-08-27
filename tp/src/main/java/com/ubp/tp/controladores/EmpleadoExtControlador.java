package com.ubp.tp.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ubp.tp.dto.EmpleadoDTO;
import com.ubp.tp.models.EmpleadoModel;
import com.ubp.tp.services.EmpleadoExtServicio;
import com.ubp.tp.services.EmpleadoService;

@RestController
@RequestMapping("/apiV1/EmpleadoresExt")
public class EmpleadoExtControlador {
	
	@Autowired
	EmpleadoExtServicio empleadoExtServicio;
	
	@Autowired
	EmpleadoService empleadoService;
	
	@GetMapping
	public ResponseEntity<List<EmpleadoDTO>> traerEmpleado(){
		
	List<EmpleadoDTO> empleadoList = empleadoExtServicio.obtenerEmpleadoExt();
		for (EmpleadoDTO it : empleadoList) {
			 empleadoService.crearEmpleado(it);
				
		}
		return new ResponseEntity<>(empleadoExtServicio.obtenerEmpleadoExt(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmpleadoDTO> traerUsuarioPorId(@PathVariable(name="id")Integer id){
		return new ResponseEntity<>(empleadoExtServicio.obtenerEmpleadoExtId(id), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void guardarUsuario(@RequestBody EmpleadoDTO empleadoDTO) {
		empleadoExtServicio.guardarEmpleadoExt(empleadoDTO);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateUser(@PathVariable(name = "id") Integer id, @RequestBody EmpleadoDTO empleadoDTO) {
		empleadoExtServicio.actualizarEmpleado(empleadoDTO, id);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteUser(@PathVariable(name = "id") Integer id) {
		empleadoExtServicio.eliminarEmpleado(id);
	}

}
