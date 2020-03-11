<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
		xmlns:c="http://java.sun.com/jsp/jstl/core"
		xmlns:f="http://java.sun.com/jsp/jstl/fmt" version="2.0">
	<jsp:directive.page contentType="text/html; charset=ISO-8859-1" 
		pageEncoding="ISO-8859-1" session="true"/>
	<jsp:output doctype-root-element="html"
		doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
		omit-xml-declaration="true" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Result</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/res/mc.css" type="text/css" title="cse4413" media="screen, print" />

</head>
<body>
	<header>Student Loan Application</header>
	<nav>
		<ul>
			<li><a href="/OsapCalc-v3/Results.jsp">Home</a> </li>
			<li><a href="/OsapCalc-v3/Results.jsp">About</a></li>
		</ul>
	</nav>
	
	<form action="Osap" method="POST" class="resultForm">
		<fieldset>
		<LEGEND>${appName}</LEGEND>
		<article>
			<strong>Grace Period Interest: </strong>
			<f:formatNumber type="currency">${graceInterest}</f:formatNumber>
			<br/><BR />
			<strong>Monthly payments: </strong>
			<f:formatNumber type="currency">${payment}</f:formatNumber>
			<br/><br/>
			<small>Calculations are based on a fixed rate based on Prime Rate + 5%</small> 
			<BR /><BR />
			<button action="restart" name="restart" value="true">Re-compute</button>
		</article>	
		</fieldset>
	</form>
	<footer>
		<img src="logo.jpg" alt="lassonde" height="100" width="100"></img>
			York University
	</footer>

</body>
</html>
</jsp:root>