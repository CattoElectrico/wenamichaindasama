package edutechInnovators.proyect.Controller;

import edutechInnovators.proyect.Model.Profesor;
import edutechInnovators.proyect.Service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Esta clase es la encargada de las peticiones REST y el propio funcionamiento de estas
 *
 * La anotacion @RestController indica que esta clase va a actuar como controlador de las
 * peticiones REST
 * La anotacion @RequestMapping indica en que ruta estara dichos servicios para su interaccion con
 * las peticiones REST o tambien endpoint
 */
@RestController
@RequestMapping("/edutechinnovations/api/v1/profesor")
public class ProfesorController {

    /**
     * La anotacion @Autowired permide una instancia de dicho atributo sin necesidad de hacerlo
     * uno mismo
     */
    @Autowired
    private ProfesorService profesorService;

    /**
     * Esta funcion permite obtener todos los componentes de dicha tabla
     * La anotacion @GetMapping permite recibir peticiones GET desde una pagina con
     * un path especifico
     * ResponseEntity permite una escritura y estructuracion de respuesta en formato HTTP
     * @return el retorno depende de la respuesta final estructurandola a gusto.
     */
    @GetMapping
    public ResponseEntity<List<Profesor>> getProfesor() {
        List<Profesor> profesores = profesorService.getProfesores();
        if (profesores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(profesores, HttpStatus.OK);
    }

    /**
     * Esta funcin permite insertar datos dependiendo lo especificado
     * La anotacion @PostMapping permite recibir peticiones POST desde una pagina con
     * un path especifico
     * @RequestBody permite rescatar el body de la peticion
     * ResponseEntity permite una escritura y estructuracion de respuesta en formato HTTP
     * @return el retorno depende de la respuesta final estructurandola a gusto.
     */
    @PostMapping
    public ResponseEntity<Profesor> saveProfesor(@RequestBody Profesor profesor) {
        Profesor newProfesor = profesorService.saveProfesor(profesor);
        return new ResponseEntity<>(newProfesor, HttpStatus.CREATED);
    }

    /**
     * Esta funcion permite obtener un dato con una variable especifica
     * La anotacion @GetMapping permite recibir peticiones GET desde una pagina con
     * un path especifico
     * @PathVariable permite rescatar una variable desde lo que esta escrito en la ruta
     * ResponseEntity permite una escritura y estructuracion de respuesta en formato HTTP
     * @return el retorno depende de la respuesta final estructurandola a gusto.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Profesor> getProfesorById(@PathVariable Long id) {
        System.out.println("getProfesorById");
        try{
            Profesor profesor = profesorService.getProfesorById(id);
            return ResponseEntity.ok(profesor);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Esta funcion permite actualizar dicho dato especificado
     * La anotacion @PutMapping permite recibir peticiones PUT desde una pagina con
     * un path especifico
     * @PathVariable permite rescatar una variable desde lo que esta escrito en la ruta
     * @RequestBody permite rescatar el body de la peticion
     * ResponseEntity permite una escritura y estructuracion de respuesta en formato HTTP
     * @return el retorno depende de la respuesta final estructurandola a gusto.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Profesor> updateProfesor(@PathVariable Long id, @RequestBody Profesor profesor) {
        System.out.println("updateProfesor");
        try{
            Profesor newProfesor = profesorService.getProfesorById(id);
            newProfesor.setId_profesor(profesor.getId_profesor());
            newProfesor.setDv_profesor(profesor.getDv_profesor());
            newProfesor.setPnombre_profesor(profesor.getPnombre_profesor());
            newProfesor.setSnombre_profesor(profesor.getSnombre_profesor());
            newProfesor.setAppaterno_profesor(profesor.getAppaterno_profesor());
            newProfesor.setApmaterno_profesor(profesor.getApmaterno_profesor());
            newProfesor.setCorreo_profesor(profesor.getCorreo_profesor());
            newProfesor.setContrasena_profesor(profesor.getContrasena_profesor());
            newProfesor.setFecha_nacimiento_profesor(profesor.getFecha_nacimiento_profesor());

            profesorService.saveProfesor(newProfesor);

            return ResponseEntity.ok(newProfesor);

        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
