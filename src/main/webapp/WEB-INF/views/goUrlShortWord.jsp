<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
if(request.getAttribute("url")==null){
  %>
  <script>
    alert('URL이 존재하지 않습니다.');
    history.back();
  </script>
  <%
}else{
  %>
  <script>
    location.href='${url}';
  </script>
  <%
}
%>