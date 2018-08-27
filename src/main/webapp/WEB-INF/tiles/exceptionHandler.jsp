<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<div class="row">
  <div class="col-md-6 col-md-offset-3 col-sd-8 col-sd-offset-2 text-center">
     <c:out value="${message}"/>
     <!--
     <c:out value="${url}"/>
     <c:out value="${exception}"/>
     <c:forEach var="trace" items="${exception.stackTrace}">
        <c:out value="${trace}"/>
     </c:forEach>
     -->
  </div>
</div>
