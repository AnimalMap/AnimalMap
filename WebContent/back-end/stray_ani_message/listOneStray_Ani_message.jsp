	
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.stray_ani_message.model.*"%>
<%-- 此頁練習採用 Script 的寫法取值 --%>

<%-- 取出 Concroller Stray_Ani_messageServlet.java已存入request的Stray_Ani_messageVO物件--%>
<%Stray_Ani_messageVO stray_ani_messageVO = (Stray_Ani_messageVO) request.getAttribute("stray_ani_messageVO");%>


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
<title>社區流浪動物留言資料 - listOneStray_Ani_message.jsp</title>

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
        <h3>社區流浪動物留言資料 - ListOneStray_Ani_message.jsp</h3>
        <a href="<%=request.getContextPath()%>/back-end/stray_ani_message/select_page.jsp">
        	<img src="<%=request.getContextPath()%>/back-end/images/back1.gif" width="100" height="32" border="0">
        	回首頁
        </a>
        </td>
    </tr>
</table>

<!--  -->
<table border='1' bordercolor='#CCCCFF'>
    <tr>
		<th>流浪動物留言編號</th>		<th>社區動物編號</th>		<th>發布者會員編號</th>		<th>發布時間</th>		<th>流浪動物留言</th>
    </tr>
    <tr align='center' valign='middle'>    
			<td>${stray_ani_messageVO.str_Ani_Mes_No}</td>			<td>${stray_ani_messageVO.stray_Ani_Id}</td>			<td>${stray_ani_messageVO.mem_Id}</td>			<td>${stray_ani_messageVO.str_Ani_Mes_time}</td>			<td>${stray_ani_messageVO.str_Ani_Mes}</td> 
    </tr>
</table>

</body>
</html>        