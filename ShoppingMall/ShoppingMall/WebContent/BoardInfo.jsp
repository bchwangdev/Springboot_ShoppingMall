<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<center>
<h2> <font color="white"> �Խñ� ���� </font> </h2>
	<table width="800" border="1" bordercolor="white">
	<tr height="40">
	<td align="center" width="200"> <font color="white" size="2">�۹�ȣ</font></td>
	<td align="center" width="600"><font color="white" size="2">${bean.num }</font></td>
	</tr>
	<tr height="40">
	<td align="center" width="200"> <font color="white" size="2">��ȸ��</font></td>
	<td align="center" width="600"><font color="white" size="2">${bean.readcount }</font></td>
	</tr>
	<tr height="40">
	<td align="center" width="200"> <font color="white" size="2">�ۼ���</font></td>
	<td align="center" width="600"><font color="white" size="2">${bean.writer }</font></td>
	</tr>
	<tr height="40">
	<td align="center" width="200"> <font color="white" size="2">�ۼ���</font></td>
	<td align="center" width="600"><font color="white" size="2">${bean.reg_date }</font></td>
	</tr>
	<tr height="40">
	<td align="center" width="200"> <font color="white" size="2">����</font></td>
	<td align="center" width="600"><font color="white" size="2">${bean.subject }</font></td>
	</tr>
	<tr height="40">
	<td align="center" width="200"> <font color="white" size="2">����</font></td>
	<td align="center" width="600">
	<textarea rows="10" cols="60" >${bean.content }
		</textarea></td>
	</tr>
	<tr height="40">
	<td align="center" colspan="2"> 
	<input type="button" value="��۾���" onclick="location.href='boardrewrite.do?num=${bean.num}&ref=${bean.ref }&re_step=${bean.re_step }&re_level=${bean.re_level }'">
	
	<input type="button" value="�����ϱ�" onclick="location.href='boardupdate.do?num=${bean.num}&subject=${bean.subject }&content=${bean.content }&writer=${bean.writer }'">
	
	<input type="button" value="�����ϱ�" onclick="location.href='boarddelete.do?num=${bean.num}'">
	
	<input type="button" value="��ü�ۺ���" onclick="location.href='board.do'">
	
	</td>
	</tr>
	
	</table>


</center>

</body>
</html>