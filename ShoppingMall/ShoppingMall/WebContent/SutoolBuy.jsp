<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<center>
<h2><font color="yellow"> ���� �Ϸ� ������  </font> </h2>
<table width="800">
<tr height="40">
	<td align="center" width="200"><font size="2" color="white">��ǰ�̹���</font></td>
	<td align="center" width="200"><font size="2" color="white">��ǰ��</font></td>
	<td align="center" width="100"><font size="2" color="white">��ǰ����</font></td>
	<td align="center" width="100"><font size="2" color="white">��ǰ����</font></td>
	<td align="center" width="200"><font size="2" color="white">��ǰ�ѱݾ�</font></td>
</tr>
<c:set var="total" value="${0 }" />

<!-- ��ñ����ϱ�  -->
<c:if test="${cart == null }">
<tr height="80">
	<td align="center" ><img  src="img/${subean.suimg }" width="180" height="70"> </td>
	<td align="center" ><font size="2" color="white">${subean.suname }</font></td>
	<td align="center" ><font size="2" color="white">${subean.suprice }��</font></td>
	<td align="center" ><font size="2" color="white">${subean.suqty }��</font></td>
	<td align="center" ><font size="2" color="white">${subean.suprice*subean.suqty }��</font></td>
</tr>
</c:if>
<c:set var="total" value="${subean.suprice*subean.suqty }" />
<tr height="70">
	<td align="center" colspan="5"> <font size="5" color="red">
		�� ���� �ݾ��� = ${total }�� �Դϴ�.</font></td>
</tr>
<tr height="70">
	<td align="center" colspan="5">
		<input type="button" onclick="location.href='index.do'" value="��� �Ϸ��ϱ�">
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" onclick="location.href='index.do'" value="����ϱ�">
	</td>
</tr>
</table>



</center>

</body>
</html>