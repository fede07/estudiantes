package test.estudiantes.servicio;

import java.util.List;

import test.estudiantes.modelo.Estudiante;

public interface IEstudianteSevicio {
    public List<Estudiante> listarEstudiantes();

    public Estudiante buscarEstudiante(Integer id_estudiante);

    public void guardarEstudiante(Estudiante estudiante);

    public void eliminarEstudiante(Integer id_estudiante);
}
