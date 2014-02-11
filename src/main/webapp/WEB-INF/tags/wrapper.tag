<%@tag description="Simple Wrapper Tag" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css" />"/>
</head>
<body>
	<div class="main-wrapp">
	    <div class="main-manu">
	        <ul class="manu">
	            <li class="menu-item"><a href="<c:url value="/" />">Feeds</a></li>
	            <li class="menu-item"><a href="<c:url value="/news" />">News</a></li>
	        </ul>
	    </div>
 	    <jsp:doBody/>
	</div>
</body>
</html>