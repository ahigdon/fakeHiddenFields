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
		<sfltap:hdnalt property="hiddenField1" />
		<sfltap:hdnalt property="hiddenField2" />
		<sfltap:hdnalt name="fromBean" property="hiddenField3" />
		<sfltap:hdnalt property="hiddenField4" value="hardcoded1" />
		<sfltap:hdnalt property="hiddenField5" value="hardcoded2" />
		<sfltap:hdnalt name="fromSessionBean" property="hiddenField6" />

		<html:submit>Submit</html:submit>
	</html:form>


</body>
</html>