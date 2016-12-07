package fortifytest.actions;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

public class TestRepopulateAction extends Action {
	private static final String SUCCESS = "success";
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		DynaActionForm  dynaForm = (DynaActionForm)form;
		Map formMap = dynaForm.getMap();
		return mapping.findForward(SUCCESS);
	}
}
