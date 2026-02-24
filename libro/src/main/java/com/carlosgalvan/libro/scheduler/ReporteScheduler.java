package com.carlosgalvan.libro.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.carlosgalvan.libro.repository.LibroRepository;

@Component
public class ReporteScheduler {

    private final LibroRepository libroRepository;

    @Autowired
    public ReporteScheduler(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Scheduled(fixedRate = 60000)
    public void generarReporte() {

        long totalLibros = libroRepository.count();
        
        if(totalLibros == 1) {
        	System.out.println(
                    "REPORTE PROGRAMADO: Actualmente hay " 
                    + totalLibros 
                    + " libro en la base de datos."
                );
        }
        else {
        	System.out.println(
                    "REPORTE PROGRAMADO: Actualmente hay " 
                    + totalLibros 
                    + " libros en la base de datos."
                );
        }
   
    }
}
