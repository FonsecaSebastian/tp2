package com.ubp.tp.controladores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.http.MediaType;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import com.ubp.tp.dto.EmpleadoDTO;
import com.ubp.tp.dto.EmpleadoRespuestaDTO;
import com.ubp.tp.services.EmpleadoServicioImpl;

@WebMvcTest(EmpleadosControlador.class)
public class controladores {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private EmpleadoServicioImpl publicacionServicio;

	@DisplayName("Test para obtener todas las publicaciones")
	@Test
	void testObtenerTodasLasPublicaciones() throws Exception {
		List<EmpleadoDTO> contenidoSimulado = new ArrayList<>();
		
		EmpleadoRespuestaDTO respuestaSimulada = new EmpleadoRespuestaDTO();
		
		respuestaSimulada.setContenido(contenidoSimulado);
		respuestaSimulada.setCantidadElementos(2L);
		respuestaSimulada.setTamañoPagina(5);
		respuestaSimulada.setNumeroPagina(0);
		respuestaSimulada.setCantidadPaginas(1);
		respuestaSimulada.setUltima(true);
		
		when(publicacionServicio.listarempleadoDTO(0, 5)).thenReturn(respuestaSimulada);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/apiV1/Empleadores"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andReturn();	
	}
	
	@DisplayName("Test para obtener todas las publicaciones")
	@WithMockUser(roles = "ADMIN")
	@Test
	void guardarPublicacionTest() throws Exception{
		 
		
		EmpleadoDTO empleadoDtoSimulado = new EmpleadoDTO("name generico","direccion generica","trabajo generico","empresa generica",null);
		EmpleadoDTO empleadoRespuestaSimulada = new EmpleadoDTO("name generico","direccion generica","trabajo generico","empresa generica",1);
	    
	    when(publicacionServicio.crearEmpleado(ArgumentMatchers.eq(empleadoDtoSimulado))).thenReturn(empleadoRespuestaSimulada);
	    
	    mockMvc.perform(MockMvcRequestBuilders.post("/apiV1/publicaciones")
	    		.contentType(MediaType.APPLICATION_JSON)
	    		.content("{\"name generico\":\"direccion generica\",\"trabajo generico\":\"empresa generica\",\"contenido\":\"empresa generica\"}"))
	    .andExpect(MockMvcResultMatchers.status().isCreated())
	    .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$.titulo").value("titulo simulado"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.descripcion").value("descripcion simulada"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.contenido").value("contenido simulado"))
        .andReturn();
	}
}
