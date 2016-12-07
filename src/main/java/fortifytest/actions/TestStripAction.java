package fortifytest.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class TestStripAction extends Action {
	private static final String SUCCESS = "success";

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setAttribute("hiddenField1", "hiddenValue1");
		request.setAttribute("hiddenField2", "hiddenValue2");
		request.setAttribute("hiddenField3", "hiddenValue3");
		request.setAttribute("hiddenField4", "ignorerthis");

		return mapping.findForward(SUCCESS);
	}
}
