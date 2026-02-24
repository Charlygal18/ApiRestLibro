package com.carlosgalvan.libro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carlosgalvan.libro.entity.Libro;
import com.carlosgalvan.libro.service.LibroService;

@RestController
@RequestMapping("/api/v1/books")
public class LibroController {
	
	private final LibroService libroService;

	@Autowired
	public LibroController(LibroService libroService) {
		this.libroService = libroService;
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<Libro>> getLibros() {
		
		return ResponseEntity.ok(libroService.getLibros());
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/{id}")
    public ResponseEntity<Libro> getLibroById(@PathVariable("id") Long id) {
		
        return ResponseEntity.ok(libroService.getLibroById(id));
    }
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
    public ResponseEntity<Libro> saveLibro(@RequestBody Libro libro) {
		
        Libro savedLibro = libroService.saveLibro(libro);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLibro);
    }
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
    public ResponseEntity<Libro> updateLibro(@PathVariable("id") Long id, @RequestBody Libro libro) {

        libro.setId(id);
        Libro updatedLibro = libroService.updateLibro(libro);
        
        return ResponseEntity.ok(updatedLibro);
    }
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteLibro(@PathVariable("id") Long id){
		
		libroService.deleteLibro(id);
		
		return ResponseEntity.ok().build();
	}
	
}
