package com.ubp.tp.dto;

import java.util.List;

public class EmpleadoRespuestaDTO {
	private List<EmpleadoDTO>contenido;
	private int numeroPagina;
	private int tamañoPagina;
	private Long cantidadElementos;
	private int cantidadPaginas;
	private Boolean ultima;
	
	
	
	
	public EmpleadoRespuestaDTO() {
	
	}

	public EmpleadoRespuestaDTO(List<EmpleadoDTO> contenido, int numeroPagina, int tamañoPagina, Long cantidadElementos,
			int cantidadPaginas, Boolean ultima) {
		super();
		this.contenido = contenido;
		this.numeroPagina = numeroPagina;
		this.tamañoPagina = tamañoPagina;
		this.cantidadElementos = cantidadElementos;
		this.cantidadPaginas = cantidadPaginas;
		this.ultima = ultima;
	}

	@Override
	public String toString() {
		return "EmpleadoRespuestaDTO [contenido=" + contenido + ", numeroPagina=" + numeroPagina + ", tamañoPagina="
				+ tamañoPagina + ", cantidadElementos=" + cantidadElementos + ", cantidadPaginas=" + cantidadPaginas
				+ ", ultima=" + ultima + "]";
	}

	public List<EmpleadoDTO> getContenido() {
		return contenido;
	}

	public void setContenido(List<EmpleadoDTO> contenido) {
		this.contenido = contenido;
	}

	public int getNumeroPagina() {
		return numeroPagina;
	}

	public void setNumeroPagina(int numeroPagina) {
		this.numeroPagina = numeroPagina;
	}

	public int getTamañoPagina() {
		return tamañoPagina;
	}

	public void setTamañoPagina(int tamañoPagina) {
		this.tamañoPagina = tamañoPagina;
	}

	public Long getCantidadElementos() {
		return cantidadElementos;
	}

	public void setCantidadElementos(Long cantidadElementos) {
		this.cantidadElementos = cantidadElementos;
	}

	public int getCantidadPaginas() {
		return cantidadPaginas;
	}

	public void setCantidadPaginas(int cantidadPaginas) {
		this.cantidadPaginas = cantidadPaginas;
	}

	public Boolean getUltima() {
		return ultima;
	}

	public void setUltima(Boolean ultima) {
		this.ultima = ultima;
	}
	
}
