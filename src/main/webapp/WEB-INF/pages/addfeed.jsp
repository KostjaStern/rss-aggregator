<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<t:wrapper>
	<h3>Add feed</h3>
	<div id="add-feed">
	    <c:url value="/feed/add" var="formLink" />
	    <form:form method="POST" action="${formLink}" modelAttribute="rssFeed">
	        <p class="row">
			    <label class="field-label" for="nameInput">Name: </label>
			    <form:input path="name" id="nameInput" />
			    <form:errors path="name" cssClass="error" />
			</p>
			
			<p class="row">
			    <label for="urlInput">URL: </label>
			    <form:input path="url" id="urlInput" />
			    <form:errors path="url" cssClass="error" />
			</p>
			
			<p class="row">
			    <input class="button" type="submit" value="Save" />
			</p>    
		</form:form>
	</div>
</t:wrapper>