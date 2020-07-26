<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<center>
	<h2><font color="white"> �Խñ� ����  </font> </h2>
	<table width="800" border="1" bordercolor="white" bgcolor="black">
	<tr height="40">
		<td colspan="5" align="right">
		<a href="boardwrite.do" style="text-decoration:none">
		<font size="3" color="white"> �����۾��� </font>
		</a></td>
	</tr>
	<tr height="40">
		<th width="50"><font size="2" color="white"> ��ȣ </font></th>
		<th width="300"><font size="2" color="white"> ���� </font></th>
		<th width="100"><font size="2" color="white"> �ۼ��� </font></th>
		<th width="150"><font size="2" color="white"> �ۼ��� </font></th>
		<th width="100"><font size="2" color="white"> ��ȸ�� </font></th>
	</tr>	
	<c:if test="${vbean !=null }">
	<c:forEach var="vbean" items="${vbean }">
	<tr height="40">
		<td align="center"><font size="2" color="white"> ${number} </font></td>
		<td align="center"><font size="2" color="white">
			<c:forEach var="j" begin="1" end="${vbean.re_step }" step="${j+1 }">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			
			</c:forEach>
			<a href="boardinfo.do?num=${vbean.num }"> ${vbean.subject }   </a>
		</font></td>
		<td align="center"><font size="2" color="white"> ${vbean.writer }</font></td>
		<td align="center"><font size="2" color="white"> ${vbean.reg_date } </font></td>
		<td align="center"><font size="2" color="white"> ${vbean.readcount } </font></td>
		<c:set  var="number" value="${number-1 }" />
	</tr>	
	</c:forEach>	
	</c:if>	
	</table>
	<p>
	<!-- ������ Ÿ���͸� �ҽ� �ۼ� -->
	<c:if test="${count >0 }" >
	<c:set var="pageCount" value="${count/pageSize+(count%pageSize==0 ? 0 : 1) }" />
	<!-- ���� ������ ���ڸ� ���� -->
	<c:set var="startPage" value="${1 }" />
	<c:if test="${currentPage %10 != 0 }">
		<fmt:parseNumber var="result" value="${currentPage/10 }"
		integerOnly="true"	/>
		
		<c:set var="startPage" value="${result*10 +1 }" />
		
	</c:if>
	<c:if test="${currentPage %10 == 0 }">
		<c:set var="startPage" value="${(result-1)*10 +1 }" />	
	</c:if>
	
	<!-- ȭ�鿡 ������ ����¡ ó�� ���ڸ� ǥ�� [1][2]... -->
	<c:set var="pageBlock" value="${10 }" />
	<c:set var="endPage"  value="${startPage+pageBlock-1 }" />
	
	<c:if test="${endPage > pageCount }">
		<c:set var="endPage" value="${pageCount }" />	
	</c:if>
	<!-- �����̶�� ��ũ�� ���� �ľ�  -->
	<c:if test="${startPage > 10 }">
		<a href="board.do?pageNum=${startPage-10 }"> [����] </a>
	</c:if>
	
	<!-- ����¡ ó�� -->
	<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<a href="board.do?pageNum=${i }"> [${i }] </a>
	</c:forEach>	
	
	<!-- ���� �̶�� ��ũ�� ���� �ľ� -->	
	<c:if test="${endPage < pageCount }">
		<a href="board.do?pageNum=${startPage+10 }"> [����] </a>
	</c:if>	
	</c:if>	
</center>
</body>
</html>




