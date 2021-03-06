<%@page import="heibernate_com.mem.model.MemVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.strayani_photo.model.*"%>


<%
	StrayaniPhotoVO strayaniPhotoVO = (StrayaniPhotoVO) request.getAttribute("strayaniPhotoVO");	
	//預防錯誤輸入，而保留user所輸入的所有內容，送出後若錯誤不用全部重打。
	
	MemVO memVO = (MemVO)session.getAttribute("account");
	String mem_Id = memVO.getMem_Id();
%>



<html>
<head>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>送養動物照片新增 - addStrayaniPhoto.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
	
	<script><!--for預覽--->
		function doFirst(){	
			//與HTML畫面產生關聯，再建事件聆聽的功能
			document.getElementById('theFile').onchange = fileChange;
		}
	
		function fileChange(){
			var file = document.getElementById('theFile').files[0];

			var readFile = new FileReader();
			readFile.readAsDataURL(file);
			readFile.addEventListener('load',function(){
				var image = document.getElementById('image');
				image.src = readFile.result;
				image.style.maxWidth = '300px';
				image.style.maxHeight = '300px';
			},false);
		}
		window.addEventListener('load',doFirst,false);
	</script>
	
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

	
	
	
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

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/strayani_photo/strayani_photo.do" name="form1" enctype="multipart/form-data">
	<table border="0">
	
		<tr>
<!-- 			<td>送養動物編號:</td> -->
			<td><input type="hidden" name="stray_Ani_Id" size="20" 	placeholder="8碼"
				value="<%= request.getParameter("stray_Ani_Id")%>"  readonly/></td>
		</tr>
<!-- 		<tr> -->
<!-- 			<td>發布者會員編號:</td> -->
<!-- 					<td> -->
						<input type="hidden" name="mem_Id" size="20" placeholder="8碼"
						value="<%= (strayaniPhotoVO==null)? mem_Id : strayaniPhotoVO.getMem_Id()%>" />
<!-- 					</td> -->
<!-- 		</tr> -->
		<tr>
			<td>送養動物動物照片名稱:</td>
			<td><input type="TEXT" name="str_Pic_name" size="20" placeholder=""
				value="<%= (strayaniPhotoVO==null)? "picture" : strayaniPhotoVO.getStray_Pic_name()%>" /></td>
		</tr>
		<tr>
			<td>送養動物動物照片附檔名:</td>
			<td><input type="TEXT" name="str_Pic_nameEX" size="20" placeholder=""
				value="<%= (strayaniPhotoVO==null)? "" : strayaniPhotoVO.getStray_Pic_nameEX()%>" /></td>
		</tr>
<!-- 		<tr> -->
<!-- 			<td>送養動物動物照片類型:</td> -->
<!-- 			<td><input type="TEXT" name="str_Pic_type" size="20" placeholder="貓、狗...." -->
<%-- 				value="<%= (strayaniPhotoVO==null)? "" : strayaniPhotoVO.getStr_Pic_type()%>" /></td> --%>
<!-- 		</tr> -->
		<tr>
			<td>送養動物動物大頭貼照片:</td>
			<td><input type="file" name="str_Ani_Pic_head" size="20" id="theFile" /></td>
		</tr>
		<tr>
			<td>送養動物動物相簿照片:</td>
			<td><input type="file" name="str_Ani_Pic" size="20"  multiple/></td>
		</tr>
		<tr>
			<td>送養動物動物相簿照片預覽:</td>
			<td><img id="image"></td>
		</tr>
		
	</table>
	<br>
	<input type="hidden" name="action" value="insert_FromView">
	<input type="submit" value="送出新增">
	</FORM>
</body>

</html>
