package fortifytest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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

import org.apache.commons.lang.StringUtils;

public class TestFilter implements Filter {

	private FilterConfig filterConfig;
	private static final String HIDDEN_FIELDS = "hiddenFileds";

	@Override
	public void doFilter(ServletRequest request, ServletResponse resp, FilterChain fc)
			throws IOException, ServletException {
		try {
			if (filterConfig == null){
				return;
			}
			
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			String fieldMarker = httpRequest.getParameter("field_marker");
			HttpSession session = httpRequest.getSession();
			Map<String, String> hiddenFields = (Map<String, String>) session.getAttribute(HIDDEN_FIELDS);
			
			if(fieldMarker != null){
				if (hiddenFields != null && !hiddenFields.isEmpty() && isMatchingRequest(fieldMarker, hiddenFields)) {
					HttpServletRequestWrapper wrapper = new TestWrapper(httpRequest, hiddenFields);
					fc.doFilter(wrapper, resp);
				} else {
					//ERROR
				}
			} else {
				fc.doFilter(request, resp);
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	private boolean isMatchingRequest(String fieldMarker, Map<String, String> hiddenFields) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String sessionMarker = hiddenFields.get("FIELDMARKER");
		
		return StringUtils.isNotEmpty(sessionMarker) && HashUtils.md5(sessionMarker).equals(fieldMarker);
	}

}
