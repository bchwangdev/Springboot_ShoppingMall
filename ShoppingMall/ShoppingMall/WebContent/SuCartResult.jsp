<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<center>
<h2><font color="yellow"> īƮ ���� Ȯ��  </font> </h2>
<table width="800" >
<tr height="40">
	<td align="center" width="200"><font size="2" color="white">��ǰ�̹���</font></td>
	<td align="center" width="150"><font size="2" color="white">��ǰ��</font></td>
	<td align="center" width="100"><font size="2" color="white">��ǰ����</font></td>
	<td align="center" width="100"><font size="2" color="white">��ǰ����</font></td>
	<td align="center" width="150"><font size="2" color="white">��ǰ�ѱݾ�</font></td>
	<td align="center" width="100"><font size="2" color="white">��ǰ���</font></td>
</tr>	
<c:forEach items="${cart.itemlist }" var="item">
<tr height="80">
	<td align="center"><img src="img/${item.suimg }" width="150" height="80"></td>
	<td align="center" ><font size="2" color="white">${item.suname }</font></td>
	<td align="center" ><font size="2" color="white">${item.suprice }��</font></td>
	<td align="center" ><font size="2" color="white">${item.suqty }</font></td>
	<td align="center" ><font size="2" color="white">${item.suprice*item.suqty }��</font></td>
	<td align="center"> <input type="button" value="īƮ����" 
	onclick="location.href='sucartdel.do?suno=${item.suno}'"></td>
</tr>
</c:forEach>
<tr height="70">
	<td align="center" colspan="6"> <font size="3" color="white"> ${msg }</font></td>
</tr>
<tr height="50">
	<td align="center" colspan="6">	
	<input type="button" onclick="location.href='index.do'" value="��Ϻ���">
	&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" onclick="location.href='sucartbuy.do'" value="����ϱ�">
	</td>
</tr>	
</table>
</center>

</body>
</html>