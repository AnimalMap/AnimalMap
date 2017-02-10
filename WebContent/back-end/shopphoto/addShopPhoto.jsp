	
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shopphoto.model.*"%>
<%
ShopPhotoVO shopphotoVO = (ShopPhotoVO) request.getAttribute("shopphotoVO");
%>

<html>
<head>
<title>商家相片新增 - addShopPhoto.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/css/calendar.css">
<%@include file="/back-end/js/calendarcode.jsp"%>

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
		<h3>商家相片新增 - addShopPhoto.jsp</h3>
		</td>
		<td>
		   <a href="<%=request.getContextPath()%>/back-end/shopphoto/select_page.jsp"><img src="<%=request.getContextPath()%>/images/tomcat.gif" width="100" height="100" border="1">回首頁</a>
	    </td>
	</tr>
</table>

<h3>商家相片:</h3>
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

<FORM METHOD="post" ACTION="shopphoto.do" name="form1">
<table border="0">


	<tr>
		<td>商家編號(相片擁有商家):</td>
		<td><input type="TEXT" name="shopPhoto_ShopId" size="45"
			value="<%= (shopphotoVO==null)? "1" : shopphotoVO.getShopPhoto_ShopId()%>" /></td>
	</tr>	

	
	<tr>
		<td>相片:</td>
		<td><input type="file" name="shopPhoto_photo" size=45></td>
	</tr>


	<tr>
		<td>是否為大頭貼相片:</td>
		<td><input type="TEXT" name="isDisp_shopPhoto" size="45"
			value="<%= (shopphotoVO==null)? "1" : shopphotoVO.getIsDisp_shopPhoto()%>" /></td>
	</tr>	


	<tr>
		<td>相片名稱:</td>
		<td><input type="TEXT" name="shopPhoto_name" size="45"
			value="<%= (shopphotoVO==null)? "1" : shopphotoVO.getShopPhoto_name()%>" /></td>
	</tr>	


	<tr>
		<td>相片副檔名:</td>
		<td><input type="TEXT" name="shopPhoto_extent" size="45"
			value="<%= (shopphotoVO==null)? "1" : shopphotoVO.getShopPhoto_extent()%>" /></td>
	</tr>	


</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

</html>