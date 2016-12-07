package fortifytest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class TestTag extends SimpleTagSupport {
	private String property;
	private String value;
	
	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getValue() {
		if(value != null){
			return value;
		}
		
		return (String)getJspContext().findAttribute(property);
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public void doTag() throws JspException, IOException {
		Map<String, String> hiddenFields;

		JspWriter out = getJspContext().getOut();
		out.println("Captured: " + property + "<br/>");
		String hiddenFieldMarker = (String)getJspContext().getAttribute("hiddenFieldMarker", PageContext.PAGE_SCOPE);
		
		if(hiddenFieldMarker == null){
			hiddenFieldMarker = UUID.randomUUID().toString();
			hiddenFields = new HashMap<String, String>();
			getJspContext().setAttribute("hiddenFieldMarker", hiddenFieldMarker, PageContext.PAGE_SCOPE);
			getJspContext().setAttribute("hiddenFileds", hiddenFields, PageContext.SESSION_SCOPE);
		} else {
			hiddenFields = (Map<String, String>)getJspContext().getAttribute("hiddenFileds", PageContext.SESSION_SCOPE);
		}
		
		hiddenFields.put(getProperty(), getValue());
	}
}
