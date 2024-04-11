package test.estudiantes.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import test.estudiantes.modelo.Estudiante;

public interface EstudianteRepositorio extends JpaRepository<Estudiante, Integer>{
    
}
