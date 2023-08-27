package com.ubp.tp.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ubp.tp.dto.EmpleadoDTO;

@Service
public class EmpleadoImplExtServicio implements EmpleadoExtServicio {

	@Autowired
	private RestTemplate restTemplate;
	
	private final String URL_BASE = "https://64d528f8b592423e469535b9.mockapi.io/apiExtTp/Empleado/";
	
	@Override
	public List<EmpleadoDTO> obtenerEmpleadoExt() {
		EmpleadoDTO[] respuesta = restTemplate.getForObject(URL_BASE, EmpleadoDTO[].class);
		return Arrays.asList(respuesta);
	}

	@Override
	public void guardarEmpleadoExt(EmpleadoDTO empleadoDTO) {
		restTemplate.postForObject(URL_BASE,empleadoDTO ,EmpleadoDTO.class);
		
	}

	@Override
	public EmpleadoDTO obtenerEmpleadoExtId(Integer id) {
		EmpleadoDTO empleadoDto = restTemplate.getForObject(URL_BASE + id, EmpleadoDTO.class);
		return empleadoDto;
		
	}

	@Override
	public void actualizarEmpleado(EmpleadoDTO empleadoDTO, Integer id) {
		restTemplate.put(URL_BASE + id, empleadoDTO, EmpleadoDTO.class);
		
	}

	@Override
	public void eliminarEmpleado(Integer id) {
		restTemplate.delete(URL_BASE + id);
		
	}

}
