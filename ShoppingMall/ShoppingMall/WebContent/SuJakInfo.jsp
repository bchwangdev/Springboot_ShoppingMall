<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<script type="text/javascript">
	function cart() {
		//���翡 ������ �Ƶ� ���ؼ� �����͸� �о�帲
		var qtyid= document.getElementById("qtyid").value;
		var sunoid= document.getElementById("sunoid").value;
		var suimgid= document.getElementById("suimgid").value;
		var supriceid= document.getElementById("supriceid").value;
		var sunameid= document.getElementById("sunameid").value;
		//�ϳ���url�� ���� �ش��ϴ� īƮ��� sutoolcart.doȣ��
		var url ="sutoolcart.do?suno="+sunoid+"&suimg="+suimgid+"&suprice="+
				supriceid+"&suname="+sunameid+"&suqty="+qtyid;
		location.href=url;
	}
	

</script>

<body bgcolor="white">
<center>
	<h2> <font color="yellow" > ���۾� ���� ���� </font> </h2>
	<form action="sutoolbuy.do" method="post">
	<table width="800" border="0" align="center">
	<tr align="center">
		<td rowspan="4" width="300">
		<img alt="" src="img/${sbean.suimg }" width="300">
		</td>
		<td align="center" width="100"><font size="2" color="white">���� �̸�</font></td>
		<td align="center" width="150"><font size="2" color="white">${sbean.suname}</font></td>
	</tr>
	<tr >
		<td align="center"><font size="2" color="white">���ż���</font></td>
		<td align="center"><select name="suqty" id="qtyid">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
		</select>	</td>
	</tr>
	<tr align="center">		
		<td align="center" width="100"><font size="2" color="white">�Ǹűݾ�</font></td>
		<td align="center" width="150"><font size="2" color="white">${sbean.suprice}��</font></td>
	</tr>	
	<tr align="center">		
		<td align="center" colspan="2">
		<input type="hidden" name="suno" id="sunoid" value="${sbean.suno }">
		<input type="hidden" name="suimg" id="suimgid" value="${sbean.suimg }">
		<input type="hidden" name="suprice" id="supriceid" value="${sbean.suprice }">
		<input type="hidden" name="suname"  id="sunameid"value="${sbean.suname }">
		<input type="button" onclick="location.href='sujak.do'" value="��Ϻ���">
		<input type="button" onclick="cart()" value="īƮ���">
		<input type="submit" value="�����ϱ�">
		
		</td>
	</tr>		
	</table>	
	</form>
	<p> <hr color="yellow">
	<font size="2" color="white">
	<b>��ǰ �󼼺���</b>
	<p>${sbean.suinfo }</p>
	</font>	
</center>
</body>
</html>