<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="heibernate_com.adopt_ani_sponsor.model.*"%>
<%
Adopt_Ani_sponsorVO adopt_ani_sponsorVO = (Adopt_Ani_sponsorVO) request.getAttribute("adopt_ani_sponsorVO");
%>
<html>
<head>
<title>送養動物贊助新增 - addAdopt_Ani_sponsor.jsp</title>
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
		<h3>送養動物贊助新增 - addAdopt_Ani_sponsor.jsp</h3>
		</td>
		<td>
		   <a href="<%=request.getContextPath()%>/Heibernate_back-end/adopt_ani_sponsor/select_page.jsp"><img src="<%=request.getContextPath()%>/images/tomcat.gif" width="100" height="100" border="1">回首頁</a>
	    </td>
	</tr>
</table>
<h3>送養動物贊助:</h3>
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
<FORM METHOD="post" ACTION="adopt_ani_sponsor.do" name="form1">
<table border="0">
	<jsp:useBean id="adopt_aniSvc" scope="page" class="heibernate_com.adopt_ani.model.Adopt_AniService" />
	<tr>
		<td>送養動物編號:<font color=red><b>*</b></font></td>
		<td><select size="1" name="adopt_Ani_Id">
			<c:forEach var="adopt_aniVO" items="${adopt_aniSvc.all}">
				<option value="${adopt_aniVO.adopt_Ani_Id}" ${(adopt_ani_sponsorVO.adopt_aniVO.adopt_Ani_Id==adopt_aniVO.adopt_Ani_Id)? 'selected':'' } >${adopt_aniVO.adopt_Ani_Id}
			</c:forEach>
		</select></td>
	</tr>
	<jsp:useBean id="memSvc" scope="page" class="heibernate_com.mem.model.MemService" />
	<tr>
		<td>贊助者會員編號:<font color=red><b>*</b></font></td>
		<td><select size="1" name="mem_Id">
			<c:forEach var="memVO" items="${memSvc.all}">
				<option value="${memVO.mem_Id}" ${(adopt_ani_sponsorVO.memVO.mem_Id==memVO.mem_Id)? 'selected':'' } >${memVO.mem_Id}
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td>贊助送養動物金額:</td>
		<td><input type="TEXT" name="ado_Ani_Spo_money" size="45"
			value="<%= (adopt_ani_sponsorVO==null)? "1" : adopt_ani_sponsorVO.getAdo_Ani_Spo_money()%>" /></td>
	</tr>	
	<tr>
		<td>贊助送養動物物資:</td>
		<td><input type="TEXT" name="ado_Ani_Spo_thing" size="45"
			value="<%= (adopt_ani_sponsorVO==null)? "1" : adopt_ani_sponsorVO.getAdo_Ani_Spo_thing()%>" /></td>
	</tr>	
	<tr>
		<%java.sql.Timestamp date_ado_Ani_Spo_time = new java.sql.Timestamp(System.currentTimeMillis());%>
		<td>贊助送養動物時間:</td>
		<td bgcolor="#CCCCFF">
		    <input class="cal-TextBox"
			onFocus="this.blur()" size="9" readonly type="text" name="ado_Ani_Spo_time" value="<%= (adopt_ani_sponsorVO==null)? date_ado_Ani_Spo_time : adopt_ani_sponsorVO.getAdo_Ani_Spo_time()%>">
			<a class="so-BtnLink"
			href="javascript:calClick();return false;"
			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','ado_Ani_Spo_time','BTN_date');return false;">
		    <img align="middle" border="0" name="BTN_date"	src="/Excel2MVC/Heibernate_back-end/images/btn_date_up.gif" width="22" height="17" alt="贊助送養動物時間"></a>
		</td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>
</html>
