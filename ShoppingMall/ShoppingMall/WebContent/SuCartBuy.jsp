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
<c:if test="${cart != null }">
<c:forEach items="${cart.itemlist }" var="item">
<tr height="80">
	<td align="center" ><img  src="img/${item.suimg }" width="180" height="70"> </td>
	<td align="center" ><font size="2" color="white">${item.suname }</font></td>
	<td align="center" ><font size="2" color="white">${item.suprice }��</font></td>
	<td align="center" ><font size="2" color="white">${item.suqty }��</font></td>
	<td align="center" ><font size="2" color="white">${item.suprice*item.suqty }��</font></td>
</tr>
<c:set var="total" value="${total + (item.suprice*item.suqty) }" />
</c:forEach>
</c:if>

<tr height="70">
	<td align="center" colspan="5"> <font size="5" color="red">
		�� ���� �ݾ��� = ${total }�� �Դϴ�.</font></td>
</tr>
<tr height="70">
	<td align="center" colspan="5">
		<input type="button" onclick="location.href='cartalldel.do'" value="��� �Ϸ��ϱ�">
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" onclick="location.href='index.do'" value="����ϱ�">
	</td>
</tr>
</table>



</center>

</body>
</html>