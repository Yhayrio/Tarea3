package com.tarea.mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

import com.tarea.mock.entidades.Perro;
import com.tarea.mock.excepciones.PerroNoEncontradoException;
import com.tarea.mock.repositorios.PerroRepository;
import com.tarea.mock.servicios.PerroComunitarioService;

public class PerroComunitarioServiceTest {

    PerroRepository mockRepository;
    PerroComunitarioService service;

    @BeforeEach
    public void inicializarPrueba(){
        // Mock del repositorio
        mockRepository = Mockito.mock(PerroRepository.class);
        // Servicio a probar
        service = new PerroComunitarioService(mockRepository);
    }

    @Test
    public void deberiaDevolverPerroCuandoElPerroExiste() {
        // Simula que el repositorio devuelve un perro con nombre "Fido" y edad 4
        Perro perroMock = new Perro("Fido", 4);
        when(mockRepository.buscarPorNombre("Fido")).thenReturn(perroMock);
        
        // Ejecutar el servicio
        Perro resultado = service.obtenerPerroPorNombre("Fido");
        
        // Verificar el resultado
        assertEquals("Fido", resultado.getNombre());
        assertEquals(4, resultado.getEdad());
    }
    
    @Test
    public void deberiaLanzarPerroNoEncontradoExceptionCuandoElPerroNoExiste() {
        // Simula que el repositorio no encuentra un perro con el nombre "Rex"
        when(mockRepository.buscarPorNombre("Rex")).thenReturn(null);
        
        // Ejecutar y verificar que se lance la excepción
        assertThrows(PerroNoEncontradoException.class,() -> {
            service.obtenerPerroPorNombre("Rex");
        });
    }
    
    @Test
    public void deberiaLanzarIllegalArgumentExceptionCuandoElNombreEsNull() {
        // Ejecutar y verificar que se lance la excepción
        assertThrows(IllegalArgumentException.class,() -> {
            service.obtenerPerroPorNombre(null);
        });
    }

    @Test
    public void deberiaLanzarIllegalArgumentExceptionCuandoElNombreEsVacio() {
        // Ejecutar y verificar que se lance la excepción
        assertThrows(IllegalArgumentException.class,() -> {
            service.obtenerPerroPorNombre("");
        });
    }
    //no pude resolver esto profe:(
    /*@Test
    public void deberiaConsultarRepositorioUnaSolaVezCuandoElPerroExiste() {
        // Simula que el repositorio devuelve un perro con nombre "Fido"
        
        // Ejecutar el servicio
        
        // Verificar que se haya llamado una sola vez
        v
    }*/
}
