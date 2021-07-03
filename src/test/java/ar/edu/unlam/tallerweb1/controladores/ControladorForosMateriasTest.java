package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.HttpSessionTest;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioGrupo;
import ar.edu.unlam.tallerweb1.servicios.ServicioGrupoImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioMensajesImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorForosMateriasTest extends HttpSessionTest {

	private ControladorForoMateria controladorForoMateria;
	private ServicioGrupo servicioGrupo;

	@Before
	public void init() {
		servicioGrupo = mock(ServicioGrupoImpl.class);
		controladorForoMateria = new ControladorForoMateria(servicioGrupo, mock(ServicioMensajesImpl.class));
	}

	@Test
	public void queAlIngresarAlForoMeEnvieALaVistaDelForo() {
		ModelAndView mvc = whenIngresoAlForoDeLaMateria();
		thenVerificoLaVista(mvc);
	}

	private void thenVerificoLaVista(ModelAndView mvc) {
		assertThat(mvc.getViewName()).isEqualTo("vistaForoMateria");
	}

	private ModelAndView whenIngresoAlForoDeLaMateria() {
		when(request().getSession().getAttribute("USUARIO")).thenReturn(new Usuario());
		return controladorForoMateria.ingresarAForoMateria(request(), 1L);
	}

}
