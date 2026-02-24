package com.carlosgalvan.libro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.carlosgalvan.libro.entity.Libro;
import com.carlosgalvan.libro.exception.LibroNoEncontradoException;
import com.carlosgalvan.libro.repository.LibroRepository;

@Service
public class LibroService {

    private final LibroRepository libroRepository;

    @Autowired
    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public List<Libro> getLibros() {

        List<Libro> listLibros = libroRepository.findAll();

        if (listLibros.isEmpty()) {
            throw new LibroNoEncontradoException("No existen libros registrados");
        }

        return listLibros;
    }

    @Cacheable(value = "libros", key = "#id")
    public Libro getLibroById(Long id) {

        System.out.println("CONSULTANDO BASE DE DATOS");

        return libroRepository.findById(id)
            .orElseThrow(() ->
                new LibroNoEncontradoException("No se encontró el libro con el Id: " + id));
    }

    public Libro saveLibro(Libro libro) {

        if (libro.getId() != null && libroRepository.existsById(libro.getId())) {
            throw new LibroNoEncontradoException("Ya existe el libro con Id: " + libro.getId());
        }

        return libroRepository.save(libro);
    }

    @CacheEvict(value = "libros", key = "#id")
    public void deleteLibro(Long id) {

        if (!libroRepository.existsById(id)) {
            throw new LibroNoEncontradoException("No existe el libro con el Id: " + id);
        }

        libroRepository.deleteById(id);
    }

    @CacheEvict(value = "libros", key = "#libro.id")
    public Libro updateLibro(Libro libro) {

        Libro existente = libroRepository.findById(libro.getId())
            .orElseThrow(() ->
                new LibroNoEncontradoException("No existe el libro con el Id: " + libro.getId()));

        existente.setTitulo(libro.getTitulo());
        existente.setAutor(libro.getAutor());

        return libroRepository.save(existente);
    }
}