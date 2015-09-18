<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.corealism.cozsample3.common.models.*"%>
<html>
<script type="text/javascript" src="/js/common.js"></script>
<body>
<h2>pagingTest</h2>
<br/>
<table border="1" style="width:800;">
	<colgroup>
		<col width="15%" />
		<col width="*" />
		<col width="25%" />
		<col width="15%" />
		<col width="15%" />
	</colgroup>
	<thead>
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>전화번호</th>
			<th>상태</th>
			<th>등록일</th>
		</tr>
	</thead>
	<tbody>
	<c:choose>
		<c:when test="${0 < fn:length(pagingTestList)}">
			<c:forEach var="tbPagingTest" items="${pagingTestList}">
				<tr>
					<td>${tbPagingTest.idx}</td>
					<td>${tbPagingTest.userName}</td>
					<td><script>document.write(phone_format('${tbPagingTest.phoneNumber}'));</script></td>
					<td>${tbPagingTest.statusCode}</td>
					<td><fmt:formatDate type="date" pattern="yy/MM/dd"  value="${tbPagingTest.regDate}" /></td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<c:if test="${0 == fn:length(tbPagingTest.list)}">
				<tr>
					<td colspan="6">게시물이 존재하지 않습니다.</td>
				</tr>
			</c:if>
		</c:otherwise>
	</c:choose>
	</tbody>
</table>

</body>
</html>
