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
		TestBean bean = new TestBean();
		bean.setHiddenField3("hiddenValue3fromBean");
		
		TestBean sessionBean = new TestBean();
		bean.setHiddenField6("hiddenValue6fromBean");
		
		request.setAttribute("hiddenField1", "hiddenValue1");
		request.setAttribute("hiddenField2", "hiddenValue2");
		request.setAttribute("fromBean", bean);
		request.setAttribute("hiddenField4", "ignorerthis");

		request.getSession().setAttribute("fromSessionBean", sessionBean);

		return mapping.findForward(SUCCESS);
	}
}
