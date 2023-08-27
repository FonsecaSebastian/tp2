package com.ubp.tp.servicios;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import static org.mockito.ArgumentMatchers.any;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.ubp.tp.dto.EmpleadoDTO;
import com.ubp.tp.dto.EmpleadoRespuestaDTO;
import com.ubp.tp.models.EmpleadoModel;
import com.ubp.tp.repositories.EmpleadoRepositorio;
import com.ubp.tp.services.EmpleadoServicioImpl;
import static org.mockito.BDDMockito.willDoNothing;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class EmpleadoServicioTest {
	
	@Mock
	private EmpleadoRepositorio empleadoRepositorio;
	@InjectMocks
	private EmpleadoServicioImpl empleadoServicioImpl;
	
	private EmpleadoModel empleado;
	private EmpleadoDTO empleadoDTO;
	
	@BeforeEach
	void setup() {
		 empleado = new EmpleadoModel("name generico","direccion generica","trabajo generico","empresa generica");
		 empleadoDTO = new EmpleadoDTO("name generico","direccion generica","trabajo generico","empresa generica",1);
	}
	
	@DisplayName("test para guardar una empleado")
	@Test
	void testGuardarEmpleado() {
		given(empleadoRepositorio.save(empleado)).willReturn(empleado);

		EmpleadoDTO empleadoGuardado = empleadoServicioImpl.crearEmpleado(empleadoDTO);
		
		assertThat(empleadoGuardado).isNotNull();
	}
	
	@DisplayName("test para listar un empleado")
	@Test
	void testListarEmpleado() {
		EmpleadoModel empleado2 = new EmpleadoModel("name generico2","direccion generica2","trabajo generico2","empresa generica2");

		List<EmpleadoModel> listaEmpleado = Arrays.asList(empleado, empleado2);
		Page<EmpleadoModel> paginaEmpleado = new PageImpl<>(listaEmpleado);

		given(empleadoRepositorio.findAll(any(Pageable.class))).willReturn(paginaEmpleado);

		EmpleadoRespuestaDTO empleadoBD = empleadoServicioImpl.listarempleadoDTO(0, 5);

		assertThat(empleadoBD.getContenido()).isNotNull();
		assertThat(empleadoBD.getContenido().size()).isEqualTo(2);

	}
	@DisplayName("Test para obtener una publicacion por id")
	@Test
	void testObtenerPublicacionPorId() {

		given(empleadoRepositorio.findById(empleado.getId())).willReturn(Optional.of(empleado));

		EmpleadoDTO publicacionDtoBD = empleadoServicioImpl.buscarEmpleadoId(empleado.getId());

		assertThat(publicacionDtoBD).isNotNull();
	}
	@DisplayName("Test para actualizar una publicacion")
	@Test
	void testActualizarPublicacion() {

		given(empleadoRepositorio.findById(empleado.getId())).willReturn(Optional.of(empleado));

		empleado.setDireccion("update direccion");
		empleado.setEmpresa("update empresa");
		empleado.setName("upadte nombre");
		empleado.setTrabajo("update trabajo");
		
		given(empleadoRepositorio.save(empleado)).willReturn(empleado);

		empleadoDTO.setDireccion(empleado.getDireccion());
		empleadoDTO.setEmpresa(empleado.getEmpresa());
		empleadoDTO.setName(empleado.getName());
		empleadoDTO.setTrabajo(empleado.getTrabajo());

		EmpleadoDTO resultado = empleadoServicioImpl.actualizarEmpleado(empleadoDTO, empleado.getId());

		assertThat(resultado.getDireccion()).isEqualTo("update direccion");
		assertThat(resultado.getEmpresa()).isEqualTo("update empresa");
		assertThat(resultado.getName()).isEqualTo("upadte nombre");
		assertThat(resultado.getTrabajo()).isEqualTo("update empresa");

	}
	@DisplayName("Test para eliminar una publicacion")
	@Test
	void eliminarPublicacion() {
		given(empleadoRepositorio.save(empleado)).willReturn(empleado);
		Integer publicacionId = empleado.getId();
		given(empleadoRepositorio.findById(publicacionId)).willReturn(Optional.of(empleado));
		
		willDoNothing().given(empleadoRepositorio).delete(empleado);
		
		empleadoServicioImpl.eliminarEmpleado(publicacionId);
		
		verify(empleadoRepositorio,times(1)).delete(empleado);
	}
	
	

}
