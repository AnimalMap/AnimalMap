<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="heibernate_com.park.model.*"%>
<%
ParkVO parkVO = (ParkVO) request.getAttribute("parkVO");
%>
<html>
<head>
<title>公園新增 - addPark.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Heibernate_back-end/css/calendar.css">
<%@include file="/Heibernate_back-end/js/calendarcode.jsp"%>
	<!-- 共用HEAD -->
	<!-- BS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="https://code.jquery.com/jquery.js"></script>
</head>
<body bgcolor='white'>
<!--  -->
<div id="popupcalendar" class="text"></div>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>公園新增 - addPark.jsp</h3>
		</td>
		<td>
		   <a href="<%=request.getContextPath()%>/Heibernate_back-end/park/select_page.jsp"><img src="<%=request.getContextPath()%>/images/tomcat.gif" width="100" height="100" border="1">回首頁</a>
	    </td>
	</tr>
</table>
<h3>公園:</h3>
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
<FORM METHOD="post" ACTION="park.do" name="form1">
<table border="0">
	<jsp:useBean id="empSvc" scope="page" class="heibernate_com.emp.model.EmpService" />
	<tr>
		<td>員工編號:<font color=red><b>*</b></font></td>
		<td><select size="1" name="emp_No">
			<c:forEach var="empVO" items="${empSvc.all}">
				<option value="${empVO.emp_No}" ${(parkVO.empVO.emp_No==empVO.emp_No)? 'selected':'' } >${empVO.emp_No}
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td>公園標題:</td>
		<td><input type="TEXT" name="park_title" size="45"
			value="<%= (parkVO==null)? "1" : parkVO.getPark_title()%>" /></td>
	</tr>	
	<tr>
		<td>公園內容:</td>
		<td><input type="TEXT" name="park_content" size="45"
			value="<%= (parkVO==null)? "1" : parkVO.getPark_content()%>" /></td>
	</tr>	
	<tr>
		<td>公園照片:</td>
		<td><input type="TEXT" name="park_pic" size="45"
			value="<%= (parkVO==null)? "1" : parkVO.getPark_pic()%>" /></td>
	</tr>	
	<tr>
		<%java.sql.Timestamp date_park_start_date = new java.sql.Timestamp(System.currentTimeMillis());%>
		<td>公園發布時間:</td>
		<td bgcolor="#CCCCFF">
		    <input class="cal-TextBox"
			onFocus="this.blur()" size="9" readonly type="text" name="park_start_date" value="<%= (parkVO==null)? date_park_start_date : parkVO.getPark_start_date()%>">
			<a class="so-BtnLink"
			href="javascript:calClick();return false;"
			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','park_start_date','BTN_date');return false;">
		    <img align="middle" border="0" name="BTN_date"	src="/Excel2MVC/Heibernate_back-end/images/btn_date_up.gif" width="22" height="17" alt="公園發布時間"></a>
		</td>
	</tr>
	<tr>
		<%java.sql.Timestamp date_park_upDate = new java.sql.Timestamp(System.currentTimeMillis());%>
		<td>公園更新時間:</td>
		<td bgcolor="#CCCCFF">
		    <input class="cal-TextBox"
			onFocus="this.blur()" size="9" readonly type="text" name="park_upDate" value="<%= (parkVO==null)? date_park_upDate : parkVO.getPark_upDate()%>">
			<a class="so-BtnLink"
			href="javascript:calClick();return false;"
			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','park_upDate','BTN_date');return false;">
		    <img align="middle" border="0" name="BTN_date"	src="/Excel2MVC/Heibernate_back-end/images/btn_date_up.gif" width="22" height="17" alt="公園更新時間"></a>
		</td>
	</tr>
	<tr>
		<td>縣市:</td>
		<td><input type="TEXT" name="park_city" size="45"
			value="<%= (parkVO==null)? "1" : parkVO.getPark_city()%>" /></td>
	</tr>	
	<tr>
		<td>鄉鎮市區:</td>
		<td><input type="TEXT" name="park_town" size="45"
			value="<%= (parkVO==null)? "1" : parkVO.getPark_town()%>" /></td>
	</tr>	
	<tr>
		<td>道路街名村里:</td>
		<td><input type="TEXT" name="park_road" size="45"
			value="<%= (parkVO==null)? "1" : parkVO.getPark_road()%>" /></td>
	</tr>	
	<tr>
		<td>公園經度座標:</td>
		<td><input type="TEXT" name="park_lon" size="45"
			value="<%= (parkVO==null)? "1" : parkVO.getPark_lon()%>" /></td>
	</tr>	
	<tr>
		<td>緯度座標緯度座標:</td>
		<td><input type="TEXT" name="park_lat" size="45"
			value="<%= (parkVO==null)? "1" : parkVO.getPark_lat()%>" /></td>
	</tr>	
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>
</html>
