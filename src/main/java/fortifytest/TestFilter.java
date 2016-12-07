package fortifytest;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

public class TestFilter implements Filter {

	private FilterConfig filterConfig;
	private static final String HIDDEN_FIELDS = "hiddenFileds";

	@Override
	public void doFilter(ServletRequest request, ServletResponse resp, FilterChain fc)
			throws IOException, ServletException {
		if (filterConfig == null){
			return;
		}
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		HttpSession session = httpRequest.getSession();
		Map<String, String> hiddenFields = (Map<String, String>) session.getAttribute(HIDDEN_FIELDS);
		if (hiddenFields != null && !hiddenFields.isEmpty()) {
			HttpServletRequestWrapper wrapper = new TestWrapper(httpRequest, hiddenFields);
			fc.doFilter(wrapper, resp);
		} else {
			fc.doFilter(request, resp);
		}
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

}
