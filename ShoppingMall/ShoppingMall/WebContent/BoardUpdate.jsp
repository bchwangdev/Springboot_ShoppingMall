<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<center>
	<h2><font color="white"> �Խñ� �����ϱ� </font> </h2>
	<form action="boardupdateproc.do" method="post">
	<input type="hidden" name="num" value="${bean.num }">
	<table width="800" border="1" bordercolor="white">
	<tr	height="40">
		<td width="200"><font size="2" color="white">�ۼ���</font></td>
		<td width="600"><font size="2" color="white">${bean.writer } </font></td>
	</tr>
	<tr	height="40">
		<td width="200"><font size="2" color="white">����</font></td>
		<td width="600"><font size="2" color="white">${bean.subject } </font></td>
	</tr>
	<tr	height="40">
		<td width="200"><font size="2" color="white">�Խñ� ����</font></td>
		<td width="600"><textarea rows="4" cols="50" name="content">${bean.content }  </textarea>  </td>
	</tr>
	<tr	height="40">
		<td width="200"><font size="2" color="white">�н�����</font></td>
		<td width="600"><input type="password" name="passwd"> </td>
	</tr>
	 <tr height="40">
	 	<td colspan="2">
	 	<input type="submit" value="�����Ϸ�"> &nbsp;&nbsp;&nbsp;
	 	<input type="reset" value="�ٽþ���"></td>
	 </tr>	
	</table>	
	</form>	
	</center>
</body>
</html>




