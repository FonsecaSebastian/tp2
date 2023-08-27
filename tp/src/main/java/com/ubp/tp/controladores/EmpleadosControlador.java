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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ubp.tp.dto.EmpleadoDTO;
import com.ubp.tp.dto.EmpleadoRespuestaDTO;
import com.ubp.tp.services.EmpleadoService;

@RestController
@RequestMapping("/apiV1/Empleadores")
public class EmpleadosControlador {
	
	@Autowired
	EmpleadoService empleadoService;
	
	
	@GetMapping
	public EmpleadoRespuestaDTO obtenerTodosLosEmpleados(
			@RequestParam(value = "PagNo", defaultValue="0",required=false)int numeroDePagina,
			@RequestParam(value = "PageSize", defaultValue="5",required=false)int tamañoPagina){
		
		return empleadoService.listarempleadoDTO(numeroDePagina,tamañoPagina);
	}
	@GetMapping("/{id}")
	public ResponseEntity<EmpleadoDTO> obtenerEmpleadoPorId(@PathVariable(name ="id")Integer id){
		return new ResponseEntity<>(empleadoService.buscarEmpleadoId(id), HttpStatus.OK);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<EmpleadoDTO> guardar(@RequestBody EmpleadoDTO empleadoDTO){
	return new ResponseEntity <>(empleadoService.crearEmpleado(empleadoDTO), HttpStatus.CREATED);
	
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<EmpleadoDTO> actualizarEmpleado(@RequestBody EmpleadoDTO empleadoDTO,@PathVariable(name ="id")Integer id){
		EmpleadoDTO empleadoRespuesta = empleadoService.actualizarEmpleado(empleadoDTO, id);		
		return new ResponseEntity<>(empleadoRespuesta, HttpStatus.OK);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarEmpleado(@PathVariable(name ="id")Integer id){
		empleadoService.eliminarEmpleado(id) ;
		return new ResponseEntity<>("Empleado Eliminado", HttpStatus.OK);		
	}

}
