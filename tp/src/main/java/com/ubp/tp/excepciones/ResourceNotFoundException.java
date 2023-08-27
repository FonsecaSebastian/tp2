package com.ubp.tp.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value =HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
private String nombreRecurso;
private String nombreCampo;
private Integer valor;
public String getNombreRecurso() {
	return nombreRecurso;
}
public void setNombreRecurso(String nombreRecurso) {
	this.nombreRecurso = nombreRecurso;
}
public String getNombreCampo() {
	return nombreCampo;
}
public void setNombreCampo(String nombreCampo) {
	this.nombreCampo = nombreCampo;
}
public Integer getValor() {
	return valor;
}
public void setValor(Integer valor) {
	this.valor = valor;
}

public ResourceNotFoundException(String nombreRecurso, String nombreCampo, Integer valor) {
	super(String.format("%s no encontrado con: %s,'%s'",nombreRecurso,nombreCampo,valor));
	this.nombreRecurso = nombreRecurso;
	this.nombreCampo = nombreCampo;
	this.valor = valor;
}




}
