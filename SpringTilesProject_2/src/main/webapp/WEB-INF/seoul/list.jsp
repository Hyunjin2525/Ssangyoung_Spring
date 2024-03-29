<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="wrapper row3">
  <main class="container clear"> 
    <div class="content"> 
      <div id="gallery">
        <figure>
          <header class="heading">${name }</header>
          <ul class="nospace clear">
          <c:forEach var="vo" items="${list }" varStatus="s">
	          <c:choose>
		          	<c:when test="${s.index%4==0 }">
		            <li class="one_quarter first"><a href="../seoul/detail.do?type=${no }&no=${vo.no}"><img src="${vo.poster }" title="${vo.title }"></a></li>
		            </c:when>
		            <c:otherwise>
		            <li class="one_quarter"><a href="../seoul/detail.do?type=${no }&no=${vo.no}"><img src="${vo.poster }" title="${vo.title }"></a></li>
		            </c:otherwise>
	           </c:choose>
           </c:forEach>
          </ul>
        </figure>
      </div>
      <nav class="pagination">
        <ul>
        <c:if test="${startpage>1 }">
          <li><a href="../seoul/list.do?page=${startpage-1 }&no=${no}">&laquo; Previous</a></li>
          </c:if>
          <c:forEach var="i" begin="${startpage }" end="${endpage }">
          <li ${i==curpage?"class=current":"" }><a href="../seoul/list.do?page=${i }&no=${no}">${i }</a></li>
          </c:forEach>
        <c:if test="${endpage<totalpage }">
    		 <li><a href="../seoul/list.do?page=${endpage+1 }&no=${no}">Next &raquo;</a></li>
        </c:if>
        </ul>
      </nav>
    </div>
    <div class="clear"></div>
  </main>
</div>
</body>
</html>