package com.ubp.tp.services;

import com.ubp.tp.dto.EmpleadoDTO;
import com.ubp.tp.dto.EmpleadoRespuestaDTO;


public interface EmpleadoService {

	public EmpleadoDTO crearEmpleado (EmpleadoDTO empleadoDTO );
	public EmpleadoRespuestaDTO listarempleadoDTO(int numeroPagina, int tamañoDePagina);
	public EmpleadoDTO buscarEmpleadoId (Integer id);
	public EmpleadoDTO actualizarEmpleado (EmpleadoDTO empleadoDTO, Integer Id);
	public void eliminarEmpleado(Integer Id);
	
}
