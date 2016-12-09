package fortifytest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.struts.util.RequestUtils;

public class TestBodyTag extends BodyTagSupport{
	private String property;
	private String value;
	private String name;
	
	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int doStartTag() throws JspException {
		Map<String, String> hiddenFields;

		try {
			String hiddenFieldMarker = (String)pageContext.getAttribute("hiddenFieldMarker", PageContext.PAGE_SCOPE);
			
			if(hiddenFieldMarker == null){
				hiddenFieldMarker = UUID.randomUUID().toString();
				String hash = HashUtils.sha256(hiddenFieldMarker);
				JspWriter out =pageContext.getOut();

				out.write("<input type='hidden' value='" + hash + "' name='field_marker' />");
				hiddenFields = new HashMap<String, String>();
				hiddenFields.put("FIELDMARKER", hiddenFieldMarker);
				
				pageContext.setAttribute("hiddenFieldMarker", hiddenFieldMarker, PageContext.PAGE_SCOPE);
				pageContext.setAttribute("hiddenFileds", hiddenFields, PageContext.SESSION_SCOPE);
			} else {
				hiddenFields = (Map<String, String>)pageContext.getAttribute("hiddenFileds", PageContext.SESSION_SCOPE);
			}
			
			hiddenFields.put(getProperty(), lookupValue(name, property));
		} catch (NoSuchAlgorithmException e) {
			throw new JspException(e);
		} catch (UnsupportedEncodingException e) {
			throw new JspException(e);
		} catch (IOException e) {
			throw new JspException(e);
		}
		
		return super.doStartTag();
	}
	
	protected String lookupValue(String name, String property) throws JspException{
        String results = null;
        if (value != null) {
            results = value;
        } else {
            Object value = null;
            if(name != null){
            	value = RequestUtils.lookup(pageContext, name, property, null);
            } else {
            	value = RequestUtils.lookup(pageContext, property, null);
            }
            if (value == null) {
                results = "";
            } else {
                results = value.toString();
            }
        }
        
        return results;
	}

}
