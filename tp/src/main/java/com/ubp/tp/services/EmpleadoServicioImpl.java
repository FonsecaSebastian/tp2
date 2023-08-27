package com.ubp.tp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ubp.tp.models.EmpleadoModel;
import com.ubp.tp.dto.EmpleadoDTO;
import com.ubp.tp.dto.EmpleadoRespuestaDTO;
import com.ubp.tp.excepciones.ResourceNotFoundException;
import com.ubp.tp.repositories.EmpleadoRepositorio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmpleadoServicioImpl implements EmpleadoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmpleadoServicioImpl.class);
	
	@Autowired
	private EmpleadoRepositorio empleadoRepositorio;  
	
	private EmpleadoModel mapearDto(EmpleadoDTO empleadoDto) {
		//Convierto de DTO a Entidad
				EmpleadoModel empleadoModel2 = new EmpleadoModel();		
				empleadoModel2.setDireccion(empleadoDto.getDireccion());
				empleadoModel2.setEmpresa(empleadoDto.getEmpresa());
				empleadoModel2.setName(empleadoDto.getName());
				empleadoModel2.setTrabajo(empleadoDto.getTrabajo());		
				LOGGER.info("mapearDto: "+empleadoModel2);		
				return empleadoModel2;
	}
	
	private EmpleadoDTO mapearEntidad (EmpleadoModel empleadoModel) {
		EmpleadoDTO empleadoRespuesta = new EmpleadoDTO();	
		
		empleadoRespuesta.setId(empleadoModel.getId());
		empleadoRespuesta.setDireccion(empleadoModel.getDireccion());
		empleadoRespuesta.setEmpresa(empleadoModel.getEmpresa());
		empleadoRespuesta.setName(empleadoModel.getName());
		empleadoRespuesta.setTrabajo(empleadoModel.getTrabajo());	
		LOGGER.info("mapearEntidad: "+empleadoModel);		
		return empleadoRespuesta;
	}
	
	@Override
	public EmpleadoDTO crearEmpleado (EmpleadoDTO empleadoDTO ) {
		
		EmpleadoModel empleado = mapearDto(empleadoDTO);		
		EmpleadoModel nuevoEmpleadoModel = empleadoRepositorio.save(empleado);		
		EmpleadoDTO empleadoRespuesta = mapearEntidad(nuevoEmpleadoModel);
		
		return empleadoRespuesta;
	}

	@Override
	public EmpleadoRespuestaDTO listarempleadoDTO(int numeroPagina, int tamañoDePagina) {
		Pageable pageable = PageRequest.of(numeroPagina, tamañoDePagina);
		Page<EmpleadoModel> empleadoModels = empleadoRepositorio.findAll(pageable);		
		List<EmpleadoModel> EmpleadosPaginados = empleadoModels.getContent();
			
		
		List<EmpleadoDTO> contenido = new ArrayList<>();
//		List<EmpleadoDTO> empleadoDTO = empleadoModels.stream()
//				.map(empleado -> mapearEntidad(empleado))
//				.collect(Collectors.toList());
				
		  for (EmpleadoModel empleado : EmpleadosPaginados) {			  
			  contenido.add(mapearEntidad(empleado)); 
		  }
 
		  EmpleadoRespuestaDTO empleadoRespuestaDTO = new EmpleadoRespuestaDTO();
		  empleadoRespuestaDTO.setContenido(contenido); // todo nuestro ArryList de Empleados 
		  empleadoRespuestaDTO.setCantidadPaginas(empleadoModels.getTotalPages()); // Total de las Paginas
		  empleadoRespuestaDTO.setTamañoPagina(empleadoModels.getSize()); // Tamaño
		  empleadoRespuestaDTO.setCantidadElementos(empleadoModels.getTotalElements()); // Cuantos empleados hay en total
		  empleadoRespuestaDTO.setNumeroPagina(empleadoModels.getNumber()); // Numero de pagina actual, donde me quiero parar
		  empleadoRespuestaDTO.setUltima(empleadoModels.isLast()); // Me dice si estoy parando en la ultima pagina
		  
		  return empleadoRespuestaDTO;
	}

	@Override
	public EmpleadoDTO buscarEmpleadoId(Integer id) {
		EmpleadoModel empleadoModel = empleadoRepositorio.findById(id).orElseThrow(() ->  new ResourceNotFoundException("empleado","id",id));	
		return mapearEntidad(empleadoModel);
	}
	

	@Override
	public EmpleadoDTO actualizarEmpleado(EmpleadoDTO empleadoDTO, Integer Id) {
		EmpleadoModel empleadoModel = empleadoRepositorio.findById(Id).orElseThrow(() ->  new ResourceNotFoundException("empleado","id",Id));	
		
		empleadoModel.setDireccion(empleadoDTO.getDireccion());
		empleadoModel.setEmpresa(empleadoDTO.getEmpresa());
		empleadoModel.setName(empleadoDTO.getName());
		empleadoModel.setTrabajo(empleadoDTO.getEmpresa());
		
		LOGGER.info("actualizarEmpleado empleadoModel : "+empleadoModel);
		
		EmpleadoModel nuevoEmpleado = empleadoRepositorio.save(empleadoModel);
		
		LOGGER.info("actualizarEmpleado nuevoEmpleado : "+nuevoEmpleado);
		return mapearEntidad(nuevoEmpleado);
	}

	@Override
	public void eliminarEmpleado(Integer Id) {
		EmpleadoModel empleadoModel = empleadoRepositorio.findById(Id).orElseThrow(() ->  new ResourceNotFoundException("empleado","id",Id));
		empleadoRepositorio.delete(empleadoModel);
	}
}
