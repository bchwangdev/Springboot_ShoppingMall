<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<center>
	<h2>  <font color="white"> �Խñ� ����  </font> </h2>
	<form action="boardwriteproc.do" method="post">
	
	<table width="800" border="1" bordercolor="white">
	<tr height="40" valign="middle">
		<td align="center" width="200" >
		<font color="white" size="2"> �ۼ��� </font></td>
		<td align="left" width="600">
		<input type="text" name="writer" size="30"></td>
	</tr>
	<tr height="40" valign="middle">
		<td align="center" width="200" >
		<font color="white" size="2"> ���� </font></td>
		<td align="left" width="600">
		<input type="text" name="subject" size="30"></td>
	</tr>
	<tr height="40" valign="middle">
		<td align="center" width="200" >
		<font color="white" size="2"> �̸��� </font></td>
		<td align="left" width="600">
		<input type="text" name="email" size="30"></td>
	</tr>
	<tr height="40" valign="middle">
		<td align="center" width="200" >
		<font color="white" size="2"> ����</font></td>
		<td align="left" width="600">
		<textarea rows="13" cols="60" name="content"></textarea></td>
	</tr>
	<tr height="40" valign="middle">
		<td align="center" width="200" >
		<font color="white" size="2"> �н����� </font></td>
		<td align="left" width="600">
		<input type="password" name="passwd" size="30"></td>
	</tr>
	<tr height="40" valign="middle">
		<td align="center" colspan="2">
		<input type="submit" value="�۾���">&nbsp;&nbsp;&nbsp;
		<input type="reset" value="�ٽþ���">&nbsp;&nbsp;&nbsp;
		<input type="button" onclick="Location.href='board.do'" value="�۸��">
		
		</td>
	</tr>			
	</table>
	</form>
</center>

</body>
</html>