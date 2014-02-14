<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper>
	<h3>Feeds</h3>
	<div id="feeds-list">
	    <c:if test="${!empty rssFeeds}">
		    <table class="data-table feeds-data">
		        <thead>
				    <tr>
						<th class="rssnew">Feed Name</th>
						<th class="rssfeed-name">URL</th>
						<th class="rssnew-delete">&nbsp;</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${rssFeeds}" var="rssFeed">
					<tr>
						<td class="rssfeed-name row-${rssFeed.id}">${rssFeed.name}</td>
						<td class="rssfeed-url row-${rssFeed.id}"><a class="rssfeed-link" href="${rssFeed.url}">${rssFeed.url}</a></td>
						<td class="rssfeed-delete row-${rssFeed.id}"><a data-class="row-${rssFeed.id}" class="rssfeed-delete button" href="<c:url value="/feed/delete/${rssFeed.id}" />">Delete</a></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
	    </c:if>
	</div>
</t:wrapper>
