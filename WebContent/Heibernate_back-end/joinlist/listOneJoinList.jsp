<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="heibernate_com.joinlist.model.*"%>
<%-- 此頁練習採用 Script 的寫法取值 --%>
<%-- 取出 Concroller JoinListServlet.java已存入request的JoinListVO物件--%>
<%JoinListVO joinlistVO = (JoinListVO) request.getAttribute("joinlistVO");%>
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
<html>
<head>
<title>揪團參加名單資料 - listOneJoinList.jsp</title>
	<!-- 共用HEAD -->
	<!-- BS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="https://code.jquery.com/jquery.js"></script>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 Script 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
    <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
        <td>
        <h3>揪團參加名單資料 - ListOneJoinList.jsp</h3>
        <a href="<%=request.getContextPath()%>/Heibernate_back-end/joinlist/select_page.jsp">
        	<img src="<%=request.getContextPath()%>/Heibernate_back-end/images/back1.gif" width="100" height="32" border="0">
        	回首頁
        </a>
        </td>
    </tr>
</table>
<!--  -->
<table border='1' bordercolor='#CCCCFF'>
    <tr align='center' valign='middle'>
		<td><b>活動</b></td>		<td><b>會員(參加者)</b></td>		<td><b>是否被邀請</b></td>
    </tr>
    <tr align='center' valign='middle'>    
	<td>
		<font color=orange>${joinlistVO.petGroupVO.grp_Id}</font>
	</td>
	<td>
		<font color=orange>${joinlistVO.memVO.mem_Id}</font>
	</td>
			<td>${joinlistVO.JOINLIST_ISINVITED}</td>
    </tr>
</table>
</body>
</html>        
