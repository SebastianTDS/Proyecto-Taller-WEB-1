package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.dto.DatosDeArchivo;
import ar.edu.unlam.tallerweb1.dto.DatosDeMensaje;
import ar.edu.unlam.tallerweb1.modelo.Archivo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.dto.DatosDeGrupo;
import ar.edu.unlam.tallerweb1.modelo.Grupo;
import ar.edu.unlam.tallerweb1.util.enums.Permiso;
import ar.edu.unlam.tallerweb1.util.exceptions.UsuarioNoEncontradoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.TreeSet;

@Controller
@RequestMapping("/grupos")
public class ControladorGrupos {

    private final ServicioGrupo servicioGrupo;
    private final ServicioNotificaciones servicioNotificacion;
    private final ServicioMensajes servicioMensajes;
    private final ServicioCalificacion servicioCalificacion;
    private final ServicioArchivos servicioArchivos;

    @Autowired
    public ControladorGrupos(ServicioGrupo servicioGrupo, ServicioNotificaciones servicioNotificacion,
                             ServicioMensajes servicioMensajes,ServicioCalificacion servicioCaligicacion,ServicioArchivos servicioArchivos) {
        this.servicioGrupo = servicioGrupo;
        this.servicioNotificacion = servicioNotificacion;
        this.servicioMensajes = servicioMensajes;
        this.servicioCalificacion=servicioCaligicacion;
        this.servicioArchivos = servicioArchivos;
    }

    @RequestMapping("/{id}")
    public ModelAndView perfilDeGrupo(@PathVariable Long id, HttpServletRequest request) {
        Usuario usuarioEnSesion = validarSesion(request);
        Grupo buscado = servicioGrupo.buscarGrupoPorID(id);
        ModelMap modelo = new ModelMap();

        servicioGrupo.validarPermiso(usuarioEnSesion.getId(), buscado.getId(), Permiso.VISTA);

        modelo.put("grupo", buscado);
        return new ModelAndView("vistaGrupo", modelo);
    }

    @RequestMapping("/{id}/edicion")
    public ModelAndView perfilDeGrupoEdicion(@PathVariable Long id, HttpServletRequest request) {
        Usuario usuarioEnSesion = validarSesion(request);
        Grupo buscado = servicioGrupo.buscarGrupoPorID(id);
        ModelMap modelo = new ModelMap();

        servicioGrupo.validarPermiso(usuarioEnSesion.getId(), buscado.getId(), Permiso.MODIFICACION);

        modelo.put("grupo", buscado);
        modelo.put("formulario", new Grupo());
        return new ModelAndView("vistaGrupo", modelo);
    }

    @RequestMapping(path = "/modificar", method = RequestMethod.POST)
    public ModelAndView cambiarDatosGrupo(@ModelAttribute("formulario") DatosDeGrupo form, HttpServletRequest request) {
        Usuario usuarioEnSesion = validarSesion(request);
        ModelMap modelo = new ModelMap();

        servicioGrupo.validarPermiso(usuarioEnSesion.getId(), form.getId(), Permiso.MODIFICACION);
        servicioGrupo.modificarGrupo(form);
        modelo.put("mensaje", "Datos actualizados");

        return new ModelAndView("redirect:/grupos/" + form.getId(), modelo);
    }

    @RequestMapping(path = "/eliminar", method = RequestMethod.POST)
    public ModelAndView eliminarGrupo(@RequestParam Long id, HttpServletRequest request) {
        Usuario usuarioEnSesion = validarSesion(request);
        ModelMap modelo = new ModelMap();

        servicioGrupo.validarPermiso(usuarioEnSesion.getId(), id, Permiso.MODIFICACION);
        servicioCalificacion.crearCalificacionPorElinimarGrupo(id);

        servicioNotificacion.notificarEliminacionDeGrupo(id);
        servicioGrupo.eliminarGrupo(id);
        modelo.put("mensaje", "Grupo eliminado con exito!");
        return new ModelAndView("redirect:/ir-a-home", modelo);
    }

    @RequestMapping("/{id}/foro")
    public ModelAndView perfilDeGrupoForo(HttpServletRequest request, @PathVariable Long id) {
    	validarSesion(request);
    	
        ModelMap modelo = new ModelMap();

        modelo.put("msj", new DatosDeMensaje());
        modelo.put("grupo", servicioGrupo.buscarGrupoPorID(id));
        modelo.put("mensajes",servicioMensajes.buscarMensajesDeUnGrupo(id));

        return new ModelAndView("vistaGrupo", modelo);
    }

    @RequestMapping("/{id}/foro/enviar-msj")
    public ModelAndView insertarMensajeEnElForo(HttpServletRequest request,@ModelAttribute("msj") DatosDeMensaje datosDeMensaje) {
        Usuario usuarioLogueado = validarSesion(request);
        servicioMensajes.guardarUnMensaje(usuarioLogueado.getId(), datosDeMensaje);
        return new ModelAndView("redirect:/grupos/" + datosDeMensaje.getId() + "/foro");
    }

    @RequestMapping("/{id}/miembros")
    public ModelAndView mostrarMiembrosDelGrupo(HttpServletRequest request, @PathVariable Long id) {
    	validarSesion(request);
    	
        ModelMap modelo = new ModelMap();

        modelo.put("grupo", servicioGrupo.buscarGrupoPorID(id));
        modelo.put("integrantes", true);
        return new ModelAndView("vistaGrupo", modelo);
    }
  
    @RequestMapping(path = "/salir", method = RequestMethod.POST)
    public ModelAndView salirDelGrupo(@RequestParam Long id, HttpServletRequest request) {
        Usuario usuarioEnSesion = validarSesion(request);
        ModelMap modelo = new ModelMap();

        servicioNotificacion.notificarRetiroDeGrupo(id,usuarioEnSesion);
        servicioCalificacion.crearCalificacion(id,usuarioEnSesion.getId());
        servicioGrupo.borrarUsuarioDelGrupo(id,usuarioEnSesion.getId());

        modelo.put("mensaje", "Se ha salido con exito!");
        return new ModelAndView("redirect:/ir-a-home", modelo);
    }
  
    @RequestMapping("/{id}/calendario")
    public ModelAndView verCalendario(HttpServletRequest request, @PathVariable Long id) {
      validarSesion(request);

      ModelMap modelo = new ModelMap();

      modelo.put("grupo", servicioGrupo.buscarGrupoPorID(id));
      return new ModelAndView("vistaCalendario", modelo);
    }

    private Usuario validarSesion(HttpServletRequest request) {
        Usuario objetivo = (Usuario) request.getSession().getAttribute("USUARIO");

        if (objetivo == null)
            throw new UsuarioNoEncontradoException("No existe un usuario logueado!");

        return objetivo;
    }

    @RequestMapping("/{id}/archivos")
    public ModelAndView perfilDeGrupoArchivos(@PathVariable Long id) {
        Grupo buscado = servicioGrupo.buscarGrupoPorID(id);
        ModelMap modelo = new ModelMap();
        modelo.put("vistaArchivos", true);
        modelo.put("grupo", buscado);
        TreeSet<Archivo> archivos=servicioArchivos.buscarArchivosPorGrupo(id);
        modelo.put("archivos", archivos);
        modelo.put("datosDeArchivo", new DatosDeArchivo());
        return new ModelAndView("vistaGrupo", modelo);
    }

    @RequestMapping(value = "/{id}/subir-archivo", method = RequestMethod.POST)
    public ModelAndView subirArchivos(@ModelAttribute("datosDeArchivo") DatosDeArchivo archivo, HttpSession session)throws IOException {
        String path = session.getServletContext().getRealPath("/archivos/");
        servicioArchivos.subirArchivoACarpeta(archivo,path);
        return new ModelAndView("redirect:/grupos/"+archivo.getIdGrupo()+ "/archivos");
    }

    @RequestMapping(value = "/{id}/descargar-archivo", method=RequestMethod.POST)
    public ModelAndView descargarArchivo(@RequestParam("idGrupo")Long idGrupo,@RequestParam("id_archivo")Long idArchivo, HttpSession session, HttpServletResponse response) throws IOException {
        String downloadFolder = session.getServletContext().getRealPath("/archivos/");
        servicioArchivos.descargarArchivo(downloadFolder,idArchivo,response);
        return new ModelAndView("redirect:/grupos/"+idGrupo+ "/archivos");
    }

    @RequestMapping(value="/{id}/borrar-archivo",method=RequestMethod.POST)
    public ModelAndView borrarArchivo(@RequestParam("idGrupo")Long idGrupo,@RequestParam("id_archivo") Long idArchivo,HttpSession session){
        String path = session.getServletContext().getRealPath("/archivos/"+idArchivo);
        servicioArchivos.borrarArchivo(idArchivo,path);
        return new ModelAndView("redirect:/grupos/"+idGrupo+ "/archivos");
    }
}
