<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<c:if test="${login == 1 }">
	<script type="text/javascript">
		alert("���̵�/�н����带 Ȯ���ϼ���");
	</script>
</c:if>

<center>
	<h2><font color="yellow"> ȸ�� �α��� </font> </h2>
	<form action="loginproc.do" method="post" >
	<table width="500">
	<tr height="40">
		<td width="200" align="center"><font size="2" color="white">���̵�</font></td>	
		<td width="200" align="center"><input type="text" name="memid" size="30"></td>	
	</tr>	
	<tr height="40">
		<td width="200" align="center"><font size="2" color="white">�н�����</font></td>	
		<td width="200" align="center"><input type="password" name="mempasswd1" size="30"></td>	
	</tr>
	<tr height="40">
		<td colspan="2" align="center">
		<input type="submit" value="�α���"> &nbsp;&nbsp;&nbsp;&nbsp;
		<font size="2" color="white"> ���̵� / �н�����ã��</font>
		 </td>	
	</tr>
	</table>		
	</form>	
</center>
</body>
</html>