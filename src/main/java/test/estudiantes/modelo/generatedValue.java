package test.estudiantes.modelo;

import jakarta.persistence.GenerationType;

public @interface generatedValue {

    GenerationType strategy();

}
