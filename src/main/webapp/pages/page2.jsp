<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<html>
<head>
<title>I Still Have Hidden Fields</title>
</head>
<body>
<h1>hidden repopulated</h1>
<li/><bean:write name="testRepopulateForm" property="hiddenField1" />
<li/><bean:write name="testRepopulateForm" property="hiddenField2" />
<li/><bean:write name="testRepopulateForm" property="hiddenField3" />
<li/><bean:write name="testRepopulateForm" property="hiddenField4" />
<li/><bean:write name="testRepopulateForm" property="hiddenField5" />
<li/><bean:write name="testRepopulateForm" property="hiddenField6" />

</body>
</html>