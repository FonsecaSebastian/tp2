package com.ubp.tp.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


public class EmpleadoDTO {
	private String name;	
	private String direccion;	
	private String trabajo;
	private String empresa;
	private Integer id;
	
	

	public EmpleadoDTO(String name, String direccion, String trabajo, String empresa, Integer id) {
		super();
		this.name = name;
		this.direccion = direccion;
		this.trabajo = trabajo;
		this.empresa = empresa;
		this.id = id;
	}

	public EmpleadoDTO() {
	
	}
	
	@Override
	public String toString() {
		return "EmpleadoDTO [name=" + name + ", direccion=" + direccion + ", trabajo=" + trabajo + ", empresa="
				+ empresa + ", id=" + id + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTrabajo() {
		return trabajo;
	}

	public void setTrabajo(String trabajo) {
		this.trabajo = trabajo;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
}
	
	