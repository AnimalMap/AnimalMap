<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>

<%-- �����m�߱ĥ� EL ���g�k���� --%>

<jsp:useBean id="listComments_ByGrpId" scope="request" type="java.util.Set" />
<jsp:useBean id="GrpSvc" scope="page" class="com.grp.model.GrpService" />
<html>
<head>
	<style>
	  
    </style>
    
    
	<script src="http://code.jquery.com/jquery-1.10.1.min.js">  </script>
	<title>��|�d�� - listComments_ByGrpId.jsp</title>
	
</head>
<body bgcolor='white'>

<b><font color=red>�����m�߱ĥ� EL ���g�k����:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>��|�d�� - listComments_ByGrpId.jsp</h3>
		<a href="<%=request.getContextPath()%>/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a>
		</td>
	</tr>
</table>

<%-- ���~���C --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>�Эץ��H�U���~:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/grp/grpComm.do" name="form1">
			<textarea style="height:2em" rows="2" cols="100" 
			id="text"  maxlength="300" name="grpComment_content" placeholder="leave a message..."></textarea>

		    <input type="submit" value="�s�W�d��">
			<input type="hidden" name="grp_Id" value="<%= request.getParameter("grp_Id") %>">
			<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
			<input type="hidden" name="action"value="insert">
		</FORM>
			
			

<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>�d���s��</th>
		<th>��|�s��-��|�W��</th>
		<th>�o�e�|���s��</th>
		
		${(param.updateComment=='') ? '<th>�ק鷺�e</th>' : '<th>�o�e���e</th>'} 
		
		<th>�o�e�ɶ�</th>
	</tr>
	
	<c:forEach var="grpCommVO" items="${listComments_ByGrpId}" varStatus="s">
		
		<tr align='center' valign='middle'>
			<td>${grpCommVO.grpComment_Id}</td>
			
			<td>
				<c:forEach var="grpVO" items="${GrpSvc.all}">
                    <c:if test="${grpCommVO.grpComment_GrpId==grpVO.grp_Id}">
	                    ${grpCommVO.grpComment_GrpId}-${grpVO.grp_name}
                    </c:if>
                </c:forEach>
			</td>
			 
			<td>${grpCommVO.grpComment_MemId}</td>
			
			<c:if test="${grpComment_content!=''&&(grpCommVO.grpComment_Id!=param.grpComment_Id)}" var="showComment">
				<td>${grpCommVO.grpComment_content}</td>
			</c:if>
			
			<c:if test="${!showComment}">
				<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/grp/grpComm.do" name="confirmUpdateComment${s.index}">
					<textarea style="height:2em" rows="2" cols="50" 
					id="text"  maxlength="300" name="grpComment_content" placeholder="change your comment...">${grpCommVO.grpComment_content}</textarea>
					<input type="hidden" name="forUpdateGrpComment_Id" value="${grpCommVO.grpComment_Id}">
			  		<input type="hidden" name="grpComment_GrpId" value="${grpCommVO.grpComment_GrpId}">
					<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
					<input type="hidden" name="action" value="confirmUpdateComment">
				</FORM>
				</td>
			</c:if>
			
			<td>
			    <fmt:formatDate type="both" dateStyle="medium" timeStyle="medium" 
	            value="${grpCommVO.grpComment_SendTime}" />
			</td>			
						
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/grp/grpComm.do">
			    <input type="submit" value="�R��">
			    <input type="hidden" name="grpComment_Id" value="${grpCommVO.grpComment_Id}">
			    <input type="hidden" name="grpComment_GrpId" value="${grpCommVO.grpComment_GrpId}">
			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
			    <input type="hidden" name="action" value="delete"></FORM>
			</td>
			
			<td>
			
			<c:if test="${showComment}">
				<button onclick="changeComment${s.index}()">�ק�d��</button>
			</c:if>
			
			<c:if test="${!showComment}">
				<input type="button" value="�T�{�ק�d��" onclick="confirmChangeComment${s.index}()">
			</c:if>
			
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/grp/grpComm.do" name="updateComment${s.index}">
			    <input type="hidden" name="grpComment_Id" value="${grpCommVO.grpComment_Id}">
			    <input type="hidden" name="grpComment_GrpId" value="${grpCommVO.grpComment_GrpId}">
			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
			    <input type="hidden" name="action" value="updateComment"></FORM>
			</td>
		</tr>
		
		
		
		<script>
		function changeComment${s.index}(){
			$("form[name='updateComment"+${s.index}+"']").submit();
		}
	
		
 		function confirmChangeComment${s.index}(){
			$("form[name='confirmUpdateComment"+${s.index}+"']").submit();
		}
		
		</script>
		
		
		
					
	</c:forEach>
</table>

<br>�����������|:<br><b>
   <font color=blue>request.getServletPath():</font> <%= request.getServletPath()%><br>
   <font color=blue>request.getRequestURI(): </font> <%= request.getRequestURI()%> </b>
</body>

	

</html>