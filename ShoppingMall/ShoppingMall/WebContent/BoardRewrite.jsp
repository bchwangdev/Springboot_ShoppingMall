<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<center>
	<h2> <font color="white"> ��� ����  </font> </h2>
	<form action="boardrewriteproc.do" method="post">
	<input type="hidden" name="num" value="${bean.num }">
	<input type="hidden" name="ref" value="${bean.ref }">
	<input type="hidden" name="re_step" value="${bean.re_step }">
	<input type="hidden" name="re_level" value="${bean.re_level }">
	
	<table width="800" border="1" bordercolor="white" align="center">
	<tr height="40">
		<td align="center" width="200"><font color="white" size="2"> �̸�  </font></td>
		<td align="left" width="600">
			<input type="text" name="writer" size="40"></td>
	</tr>		
	<tr height="40">
		<td align="center" width="200"><font color="white" size="2"> ���� </font> </td>
		<td align="left" width="600">
			<input type="text" name="subject" size="40" value=" [���] "></td>
	</tr>
	<tr height="40">
		<td align="center" width="200"><font color="white" size="2"> �̸���  </font></td>
		<td align="left" width="600">
			<input type="text" name="email" size="40"></td>
	</tr>
	<tr height="40">
		<td align="center" width="200"> <font color="white" size="2">�۳���  </font></td>
		<td align="left" width="600">
			<textarea rows="13" cols="60" name="content"></textarea>
		</td>
	</tr>
	<tr height="40">
		<td align="center" width="200"><font color="white" size="2"> �н����� </font></td>
		<td align="left" width="600">
			<input type="password" name="passwd" size="40"></td>
	</tr>
	<tr height="40">
		<td align="center" colspan="2"> 
		<input type="submit" value="��۾���Ϸ�">&nbsp;&nbsp;&nbsp;
		<input type="reset" value="�ٽþ���">&nbsp;&nbsp;&nbsp;
		<input type="button" value="��ü�ۺ���" 
		  onclick="location.href='board.do'">		
		</td>
	</tr>	
	</table>
	</form>
	
	
</center>

</body>
</html>