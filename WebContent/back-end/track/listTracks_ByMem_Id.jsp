
	


<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%-- 此頁練習採用 EL 的寫法取值 --%>

<jsp:useBean id="listTracks_ByMem_Id" scope="request" type="java.util.Set" />

<html>
<head>
<title>會員編號 - listTracks_ByMem_Id.jsp</title>
	<!-- 共用HEAD -->
	
	<!-- BS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="https://code.jquery.com/jquery.js"></script>

</head>
<body bgcolor='white'>

<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
    <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
        <td>
        <h3>會員編號 - listTracks_ByMem_Id.jsp</h3>
        <a href="<%=request.getContextPath()%>/back-end/track/select_page.jsp"><img src="<%=request.getContextPath()%>/back-end/images/tomcat.gif" width="100" height="32" border="0">回首頁</a>
        </td>
    </tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
    <font color='red'>請修正以下錯誤:
    <ul>
        <c:forEach var="message" items="${errorMsgs}">
            <li>${message}</li>
        </c:forEach>
    </ul>
    </font>
</c:if>

<table border='1' bordercolor='#CCCCFF' width='800'>
    <tr>
        <th>收藏編號</th>
        <th>會員編號</th>
        <th>收藏種類</th>
        <th>種類編號</th>

        <th>修改</th>
        <th>刪除</th> 
    </tr>
    <c:forEach var="trackVO" items="${listTracks_ByMem_Id}" >    
        <tr>
			<td>${trackVO.track_Id}</td>
			<td>${trackVO.mem_Id}</td>
			<td>${trackVO.track_record_class}</td>
			<td>${trackVO.track_record_class_Id}</td>

            <td>
              <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/track/track.do">
                <input type="submit" value="修改"> 
                <input type="hidden" name="track_Id" value="${trackVO.track_Id}">
                <input type="hidden" name="requestURL"  value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
                <input type="hidden" name="action"  value="getOne_For_Update"></FORM>
            </td>
            <td>
              <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/track/track.do">
                <input type="submit" value="刪除">
                <input type="hidden" name="track_Id" value="${trackVO.track_Id}">
                <input type="hidden" name="requestURL"  value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
                <input type="hidden" name="action" value="delete"></FORM>
            </td>  
        </tr>
    </c:forEach>
</table>

<br>本網頁的路徑:<br><b>
   <font color=blue>request.getServletPath():</font> <%= request.getServletPath()%><br>
   <font color=blue>request.getRequestURI(): </font> <%= request.getRequestURI()%> </b>
</body>
</html>