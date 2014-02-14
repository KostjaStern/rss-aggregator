<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper>
	<h3>News</h3>
	<div id="news-list">
	    <c:if test="${!empty rssNews}">
		    <table class="data-table news-data">
		        <thead>
				    <tr>
						<th class="rssnew">News</th>
						<th class="rssfeed-name">Feed Name</th>
						<th class="rssnew-delete">&nbsp;</th>
					</tr>
				</thead>
				<tfoot>
				    <tr>
				        <td colspan="3">   
				            <a class="get-news button" href="<c:url value="/news/get-news" />">Get News</a>
				            <a class="delete-all button" href="<c:url value="/news/delete-all" />">Delete All</a>
				        </td>
				    </tr>
				</tfoot>
				<tbody>
				<c:forEach items="${rssNews}" var="rssNew">
					<tr>
						<td class="rssnew row-${rssNew.id}"><a class="rssnew-link" href="${rssNew.link}">${rssNew.title}</a></td>
						<td class="rssfeed-name row-${rssNew.id}">${rssNew.rssFeed.name}</td>
						<td class="rssnew-delete row-${rssNew.id}"><a data-class="row-${rssNew.id}" class="rssnew-delete button" href="<c:url value="/news/delete/${rssNew.id}" />">Delete</a></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
	    </c:if>
	</div>
</t:wrapper>
