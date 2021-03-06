<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="heibernate_com.anihome_photos.model.*"%>
<%
    AniHome_PhotosVO anihome_photosVO = (AniHome_PhotosVO) request.getAttribute("anihome_photosVO"); //AniHome_PhotosServlet.java (Concroller), 存入req的anihome_photosVO物件 (包括幫忙取出的anihome_photosVO, 也包括輸入資料錯誤時的anihome_photosVO物件)
%>
<!--  -->
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
	<!-- 共用HEAD -->
	<!-- BS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="https://code.jquery.com/jquery.js"></script>
	<title>動物之家相簿資料修改 - update_anihome_photos_input.jsp</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Heibernate_back-end/css/calendar.css">
	<%@include file="/Heibernate_back-end/js/calendarcode.jsp"%>
	<div id="popupcalendar" class="text"></div>
</head>
<body bgcolor='white'>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
    <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
        <td>
        <h3>動物之家相簿資料 - ListOneAniHome_Photos.jsp</h3>
        <a href="<%=request.getContextPath()%>/Heibernate_back-end/anihome_photos/select_page.jsp">
        	<img src="<%=request.getContextPath()%>/Heibernate_back-end/images/back1.gif" width="100" height="32" border="0">
        	回首頁
        </a>
        </td>
    </tr>
</table>
<h3>資料修改:</h3>
<FORM METHOD="post" ACTION="anihome_photos.do" name="form1">
    <table border="0">
    <!--  -->
    <tr>
        <td>相片編號編號:<font color=red><b>*</b></font></td>
        <td><%=anihome_photosVO.getAniHome_Photos_Id()%></td>
    </tr>
<jsp:useBean id="aniHomeSvc" scope="page" class="heibernate_com.anihome.model.AniHomeService" />
	<tr>
		<td>動物之家編號:<font color=red><b>*</b></font></td>
		<td>
	       <select size="1" name="aniHome_Id">
	         <c:forEach var="aniHomeVO" items="${aniHomeSvc.all}" > 
				<option value="${aniHomeVO.aniHome_Id}" ${(anihome_photosVO.aniHomeVO.aniHome_Id==aniHomeVO.aniHome_Id)?'selected':'' } >
${aniHomeVO.aniHome_Id}				
	         </c:forEach>   
	       </select>			
		</td>
	</tr>
    <tr>
        <td>動物之家照片:</td>
        <td><input type="TEXT" name="aniHome_Photos_pic" size="45" value="<%=anihome_photosVO.getAniHome_Photos_pic()%>" /></td>
    </tr>
    <!--  -->
    </table>
    <br>
    <input type="hidden" name="action" value="update">
			<input type="hidden" name="aniHome_Photos_Id" value="<%=anihome_photosVO.getAniHome_Photos_Id()%>">	
    <input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--接收原送出修改的來源網頁path後,再送給Controller準備轉交之用-->
    <input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">  <!--用於:istAllAniHome_Photos.jsp 與 複合查詢 listAniHome_Photoss_ByCompositeQuery.jsp-->
    <input type="submit" value="送出修改">
</FORM>
<br>送出修改的來源網頁path:<br><b>
   <font color=blue>request.getParameter("requestURL"):</font> <%= request.getParameter("requestURL")%><br>
</body>
</html>
