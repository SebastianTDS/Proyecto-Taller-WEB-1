package ar.edu.unlam.tallerweb1;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public abstract class HttpSessionTest {
	private static HttpServletRequest httpServlet = mock(HttpServletRequest.class);
	private static RedirectAttributes modelo = mock(RedirectAttributes.class);
	private static HttpSession session = mock(HttpSession.class);
	
	protected HttpServletRequest request() {
		when(httpServlet.getSession()).thenReturn(session);
		return httpServlet;
	}
	
	protected RedirectAttributes modelo() {
		return modelo;
	}
	
}
