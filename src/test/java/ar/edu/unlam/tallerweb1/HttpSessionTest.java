package ar.edu.unlam.tallerweb1;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class HttpSessionTest {
	private static HttpServletRequest httpServlet = mock(HttpServletRequest.class);
	private static HttpSession session = mock(HttpSession.class);
	
	protected HttpServletRequest request() {
		when(httpServlet.getSession()).thenReturn(session);
		return httpServlet;
	}
	
}
