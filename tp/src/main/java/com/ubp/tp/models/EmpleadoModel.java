package com.ubp.tp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Entity
@Table (uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class EmpleadoModel {
	
	
	private String name;	
	private String direccion;	
	private String trabajo;
	private String empresa;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

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

	@Override
	public String toString() {
		return "EmpleadoModel [name=" + name + ", direccion=" + direccion + ", trabajo=" + trabajo + ", Empresa="
				+ empresa + ", id=" + id + "]";
	}

	public EmpleadoModel(String name, String direccion, String trabajo, String empresa, Integer id) {
		super();
		this.name = name;
		this.direccion = direccion;
		this.trabajo = trabajo;
		empresa = empresa;
		this.id = id;
	}

	public EmpleadoModel() {

	}

	// Constructor para los test
	public EmpleadoModel(String name, String direccion, String trabajo, String empresa) {
		super();
		this.name = name;
		this.direccion = direccion;
		this.trabajo = trabajo;
		this.empresa = empresa;
	}
	
	

}
