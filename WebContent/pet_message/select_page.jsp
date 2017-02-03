<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>表格 Pet_Message: Home</title></head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/calendar.css">
<%@include file="/js/calendarcode.jsp"%>
<div id="popupcalendar" class="text"></div>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>表格 Pet_Message: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for Pet_Message: Home</p>

<hr>

<h3>自家寵物留言資料查詢:</h3>
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

<!--  -->
<ul>
  <li><a href='<%=request.getContextPath()%>/pet_message/listAllPet_Message.jsp'>List</a> all Pet_Messages. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pet_message/pet_message.do" >
        <b>輸入自家寵物留言編號 (如7001):</b>
        <input type="text" name="pet_Mes_No">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="pet_messageSvc" scope="page" class="com.pet_message.model.Pet_MessageService" />

  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pet_message/pet_message.do" >
       <b>選擇自家寵物留言編號:</b>
       <select size="1" name="pet_Mes_No">
         <c:forEach var="pet_messageVO" items="${pet_messageSvc.all}" > 
          <option value="${pet_messageVO.pet_Mes_No}">${pet_messageVO.pet_Mes_No}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

</ul>

<hr>

<!--  -->
<h3>自家寵物留言管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/pet_message/addPet_Message.jsp'>Add</a> a new Pet_Message.</li>
</ul>

<!--  -->

</body>

</html>