package com.ubp.tp.services;

import java.util.List;

import com.ubp.tp.dto.EmpleadoDTO;


public interface EmpleadoExtServicio {
	
	public List<EmpleadoDTO> obtenerEmpleadoExt();
	
	public void guardarEmpleadoExt(EmpleadoDTO empleadoDTO);
	
	public EmpleadoDTO obtenerEmpleadoExtId(Integer id);
	
	public void actualizarEmpleado(EmpleadoDTO empleadoDTO, Integer id);
	
	public void eliminarEmpleado(Integer id);

}
