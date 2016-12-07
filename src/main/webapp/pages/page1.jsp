<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib prefix="sfltap" uri="sfltap-custom"%>

<html>
<head>
<title>I Have Hidden Fields</title>
</head>
<body>
	<h1>hidden removed</h1>

	<html:form action="/page2">
		<sfltap:hidden property="hiddenField1" />
		<sfltap:hidden property="hiddenField2" />
		<sfltap:hidden property="hiddenField3" />
		<sfltap:hidden property="hiddenField4" value="hardcoded1" />
		<sfltap:hidden property="hiddenField5" value="hardcoded2" />

		<html:submit>Submit</html:submit>
	</html:form>


</body>
</html>