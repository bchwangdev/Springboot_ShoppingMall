<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<center>
<h2> <font color="white"> �Խñ� ���� </font> </h2>
	<form action="boarddeleteproc.do" method="post">
	<input type="hidden" name="num" value="${num }">
	<table width="800" border="1" bordercolor="white"> 
	<tr height="40">
		<td width="200"><font size="2" color="white">  ��й�ȣ </font></td>
		<td width="600"><input type="password" name="passwd"> </td>
	</tr>
	<tr height="40">
		<td colspan="2">
		<input type="submit" value="�����ϱ�"> &nbsp;&nbsp;&nbsp;
		<input type="button" value="�Խñ� ����" onclick="location.href='board.do'">		
		</td>
	</tr>
	</table>
	</form>
</center>

</body>
</html>