package test.estudiantes;

import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import test.estudiantes.modelo.Estudiante;
import test.estudiantes.servicio.EstudianteServicio;

@SpringBootApplication
public class EstudiantesApplication implements CommandLineRunner{

	@Autowired
	private EstudianteServicio estudianteServicio;

	private static final Logger logger = LoggerFactory.getLogger(EstudiantesApplication.class);

	String nl = System.lineSeparator();

	public static void main(String[] args) {
		logger.info("Iniciando la aplicación...");
		SpringApplication.run(EstudiantesApplication.class, args);
		logger.info("Aplicación finalizada.");
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info(nl + "Ejecutando metodo run de Spring..." + nl);
		var salir = false;
		var consola = new Scanner(System.in);

		while(!salir){
			mostrarMenu();
			salir = ejecutarOpcion(consola);
			logger.info(nl);
			}
		}

	private void mostrarMenu(){
		logger.info("""
			*** SISTEMA DE GESTION DE ESTUDIANTES ***
			1. Listar estudiantes
			2. Buscar estudiante
			3. Agregar estudiante
			4. Modificar estudiante
			5. Eliminar estudiante
			6. Salir
			Ingrese una opción: """);
	}

	private boolean ejecutarOpcion(Scanner consola){
		var opcion = Integer.parseInt(consola.nextLine());
		var salir = false;

		switch(opcion){
			case 1:
				logger.info("Listado de estudiantes:" + nl);
				List<Estudiante> estudiantes = estudianteServicio.listarEstudiantes();
				estudiantes.forEach(estudiante -> logger.info(estudiante.toString() + nl));
				break;
			case 2:
				logger.info("Ingrese el id del estudiante a buscar: ");
				var idEstudiante = Integer.parseInt(consola.nextLine());
				Estudiante estudiante = estudianteServicio.buscarEstudiante(idEstudiante);
				if(estudiante != null){
					logger.info("Estudiante encontrado: " + estudiante + nl);
				}else{
					logger.info("Estudiante no encontrado" + nl);
				}
				break;
			case 3:
				logger.info(nl + "Agregar un nuevo estudiante:" + nl);
				logger.info("Ingrese el nombre del estudiante: " );
				var nombre = consola.nextLine();
				logger.info("Ingrese el apellido del estudiante: ");
				var apellido = consola.nextLine();
				logger.info("Ingrese el teléfono del estudiante: ");
				var telefono = consola.nextLine();
				logger.info("Ingrese el email del estudiante: ");
				var email = consola.nextLine();
				var nuevoEstudiante = new Estudiante();
				nuevoEstudiante.setNombre(nombre);
				nuevoEstudiante.setApellido(apellido);
				nuevoEstudiante.setTelefono(telefono);
				nuevoEstudiante.setEmail(email);
				estudianteServicio.guardarEstudiante(nuevoEstudiante);
				logger.info("Estudiante guardado con éxito" + nl);
				break;
			case 4:
				logger.info("Modificar un estudiante:" + nl);
				logger.info("Ingrese el id del estudiante a modificar: ");
				var idEstudianteModificar = Integer.parseInt(consola.nextLine());
				Estudiante estudianteModificar = estudianteServicio.buscarEstudiante(idEstudianteModificar);
				if(estudianteModificar != null){
					logger.info("Estudiante encontrado: " + estudianteModificar + nl);
					logger.info("Ingrese el nuevo nombre del estudiante: ");
					estudianteModificar.setNombre(consola.nextLine());
					logger.info("Ingrese el nuevo apellido del estudiante: ");
					estudianteModificar.setApellido(consola.nextLine());
					logger.info("Ingrese el nuevo teléfono del estudiante: ");
					estudianteModificar.setTelefono(consola.nextLine());
					logger.info("Ingrese el nuevo email del estudiante: ");
					estudianteModificar.setEmail(consola.nextLine());
					estudianteServicio.guardarEstudiante(estudianteModificar);
					logger.info("Estudiante modificado con éxito" + nl);
				}else{
					logger.info("Estudiante no encontrado" + nl);
				}
				break;
			case 5:
				logger.info("Eliminar un estudiante:" + nl);
				logger.info("Ingrese el id del estudiante a eliminar: ");
				var idEstudianteEliminar = Integer.parseInt(consola.nextLine());
				Estudiante estudianteEliminar = estudianteServicio.buscarEstudiante(idEstudianteEliminar);
				if(estudianteEliminar != null){
					logger.info("Estudiante encontrado: " + estudianteEliminar + nl);
					estudianteServicio.eliminarEstudiante(idEstudianteEliminar);
					logger.info("Estudiante eliminado con éxito" + nl);
				}else{
					logger.info("Estudiante no encontrado" + nl);
				}
				break;
			case 6:
				salir = true;
				break;
			default:
				logger.info("Opción no válida");
		}
		return salir;
	}
}