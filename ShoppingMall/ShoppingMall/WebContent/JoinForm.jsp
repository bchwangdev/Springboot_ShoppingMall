<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>

<c:if test="${result==1 }">
	<script type="text/javascript">
	
		alert("�ش� ���̵�� ���� �մϴ�. �ٸ� ���̵� �־��ּ���");
	</script>

</c:if>
<c:if test="${result==2 }">
	<script type="text/javascript">
	
		alert("�н����尡 Ʋ���ϴ�. �� Ȯ�����ּ���");
	</script>

</c:if>


<center>
<h2> <font color="white"> ȸ�� ���� </font> </h2>
<form action="joinproc.do" method="post">
<table width="400" border="0" cellspacing="1" cellpadding="4">
<tr height="40">
	<td align="right" width="180"> <font color="white" size="3" >���̵�</font></td>
	<td width="220" > <input type="text" name="memid" size="20"></td>
</tr>
<tr height="40">
	<td align="right" width="180"> <font color="white" size="3" >�н�����</font></td>
	<td width="220"> <input type="password" name="mempasswd1" size="20"></td>
</tr>
<tr height="40">
	<td align="right" width="180"> <font color="white" size="3" >�н����� Ȯ��</font></td>
	<td width="220"> <input type="password" name="mempasswd2" size="20"></td>
</tr>
<tr height="40">
	<td align="right" width="180"> <font color="white" size="3" >�̸�</font></td>
	<td width="220"> <input type="text" name="memname" size="20"></td>
</tr>
<tr height="40">
	<td align="right" width="180"> <font color="white" size="3" >��ȭ��ȣ</font></td>
	<td width="220"> <input type="text" name="memphone" size="20"></td>
</tr>
<tr height="40">
	<td align="right" width="180"> <font color="white" size="3" >�����</font></td>
	<td width="220"> <input type="date" name="memdate" size="20"></td>
</tr>
<tr height="40">
	<td colspan="2" align="center">
		<input type="submit" value="ȸ������"> &nbsp;&nbsp;&nbsp;&nbsp;
		<input type="reset" value="�ٽ��ۼ�">
	</td>
</tr>		
</table>
</form>
</center>
</body>
</html>