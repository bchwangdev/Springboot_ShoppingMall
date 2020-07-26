<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<center>
	<h2><font color="white"> 결제 완료 페이지</font></h2>
	<table width="800" style="font-size:10pt; color: white;">
	<tr height="50">
		<td align="center" colspan="5"> </td>
	</tr>
	<tr height="40">
		<td align="center" width="200"><font size="2" color="white">상품이미지</font></td>
		<td align="center" width="200"><font size="2" color="white">상품명</font></td>
		<td align="center" width="100"><font size="2" color="white">상품가격</font></td>
		<td align="center" width="100"><font size="2" color="white">상품수량</font></td>
		<td align="center" width="200"><font size="2" color="white">상품총금액</font></td>
	</tr>
	<c:set var="total" value="${0 }" />
	<c:if test="${cart != null }">
		<c:forEach items="${cart.itemlist }" var="item">
		<tr height="80">
		<td align="center"><img src="img/${item.suimg }" width="150" height="80"></td>
		<td align="center"><font size="2" color="white">${item.suname}</font></td>
		<td align="center"><font size="2" color="white">${item.suprice }원</font></td>
		<td align="center"><font size="2" color="white">${item.qty }개</font></td>
		<td align="center"><font size="2" color="white">${item.suprice*item.qty }원</font></td>
		</tr>	
		<c:set var="total" value="${total + (item.suprice*item.qty) }"	/>
		</c:forEach>	
	</c:if>		
	<c:if test="${cart == null }">
	<tr height="80">
		<td align="center"><img src="img/${bean.suimg }" width="150" height="80"></td>
		<td align="center"><font size="2" color="white">${bean.suname}</font></td>
		<td align="center"><font size="2" color="white">${bean.suprice }원</font></td>
		<td align="center"><font size="2" color="white">${qty }개</font></td>
		<td align="center"><font size="2" color="white">${bean.suprice*qty }원</font></td>
	</tr>
	<c:set var="total" value="${bean.suprice*qty }"	/>
	</c:if>
	<tr height="70">
		<td align="center" colspan="5"><font size="5" color="red">
				총 결제 금액은 = ${total }원 입니다.</font></td>
	</tr>
	<tr height="50">
		<td align="center" colspan="5">
			<input type="button" onclick="location.href='index.do'" value="결제완료하기">
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" onclick="location.href='index.do'" value="취소하기">
		</td>
	</tr>		
	</table>
	
</center>

</body>
</html>