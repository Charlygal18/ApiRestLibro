package com.carlosgalvan.libro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carlosgalvan.libro.entity.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long>{
	
}
