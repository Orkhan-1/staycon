<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="og"%>

<c:url var="url" value="/displayStatus"/>

<div class="row">
  <div class="col-md-8 col-md-offset-2">

    <og:pagination url="${url}" page="${page}" size="5"/>

    <div class="panel panel-default">
      <c:forEach var="status" items="${page.content}">
        <c:set var="editlink" value="/editstatus?id=${status.id}"/>
        <c:set var="deletelink" value="/deletestatus?id=${status.id}"/>
        <div class="panel-heading">
          <div class="panel-title"> <fmt:formatDate pattern="EEEE d MMMM y 'at' H:mm:s" value="${status.addedDate}"/> </div>
        </div>
        <div class="panel-body">
          ${status.text}
          <div edit-links pull-right>
            <a href="${editlink}">edit</a> |
            <a href="${deletelink}">delete</a>
          </div>
        </div>
      </c:forEach>
    </div>
  </div>
</div>