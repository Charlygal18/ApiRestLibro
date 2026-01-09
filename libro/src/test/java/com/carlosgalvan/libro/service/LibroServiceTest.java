package com.carlosgalvan.libro.service;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.carlosgalvan.libro.entity.Libro;
import com.carlosgalvan.libro.repository.LibroRepository;

@ExtendWith(MockitoExtension.class)
public class LibroServiceTest {

	@Mock
    private LibroRepository libroRepository;

    @InjectMocks
    private LibroService libroService;
    
    @Test
    void getLibroById() {
        Libro libro = new Libro(1L, "Clean Code", "Robert Martin");

        when(libroRepository.findById(1L)).thenReturn(Optional.of(libro));

        Libro resultado = libroService.getLibroById(1L);

        assertEquals("Clean Code", resultado.getTitulo());
    }
}
