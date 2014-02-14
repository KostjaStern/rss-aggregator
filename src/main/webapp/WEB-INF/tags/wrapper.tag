<%@tag description="Simple Wrapper Tag" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Rss Agregator</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/reset.css" />"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css" />"/>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-1.11.0.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/rss.js" />"></script>
</head>
<body>
	<div class="main-wrapp">
	    <div class="main-manu">
	        <ul class="manu">
	            <li class="menu-item<c:if test="${activeMenu==1}"> active</c:if>"><a href="<c:url value="/feed/list" />">Feeds</a></li>
                <li class="menu-item<c:if test="${activeMenu==2}"> active</c:if>"><a href="<c:url value="/feed/add" />">Add feed</a></li>
	            <li class="menu-item<c:if test="${activeMenu==3}"> active</c:if>"><a href="<c:url value="/news" />">News</a></li>
	        </ul>
	    </div>
	    
 	    <jsp:doBody/>
	</div>
</body>
</html>