package fortifytest;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class TestWrapper extends HttpServletRequestWrapper {
	private Map<String, String> hiddenFields;
	
	public TestWrapper(HttpServletRequest request, Map<String, String> hiddenFields){
		super(request);
		this.hiddenFields = hiddenFields;
	}
	
	@Override
	public String getParameter(String name) {
		if(hiddenFields != null && ! hiddenFields.isEmpty() && hiddenFields.get(name) != null){
			return hiddenFields.get(name);
		}
		
		return super.getParameter(name);
	}
	
	@Override
	public Map getParameterMap() {
		Map reqMap = super.getParameterMap();
		if(hiddenFields != null){
			reqMap.putAll(hiddenFields);
		}
		
		return reqMap;
	}
	
	@Override
	public Enumeration getParameterNames() {
		Set parameterNames = new HashSet();
		if(hiddenFields != null){
			parameterNames = hiddenFields.keySet();
		}
		Enumeration e = super.getParameterNames();
		while(e.hasMoreElements()){
			parameterNames.add(e.nextElement());
		}
		
		return Collections.enumeration(parameterNames);
	}
	
	@Override
	public String[] getParameterValues(String name) {
		if(hiddenFields != null && ! hiddenFields.isEmpty() && hiddenFields.get(name) != null){
			return new String[]{hiddenFields.get(name)};
		}
		
		return super.getParameterValues(name);
	}
	
}
