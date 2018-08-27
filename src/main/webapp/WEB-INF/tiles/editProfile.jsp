<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="row" xmlns:form="http://www.w3.org/1999/html">
  <div class="col-md-8 col-md-offset-2">

    <div class="errors">
        <form:errors path="profileModel.*"/>
    </div>

    <div class="panel panel-default">
      <div class="panel-heading">
        <div class="panel-title"> Edit Profile </div>
      </div>
      <form:form method="POST" modelAttribute="profileModel">
        <div class="form-group">
          <form:textarea path="about" name="about" rows="10" col="50"></form:textarea>
          <input type="submit" name="submit" value="Save Profile">
        </div>
      </form:form>
    </div>
  </div>
</div>

<script src='https://cloud.tinymce.com/stable/tinymce.min.js'></script>
<script>
  tinymce.init({
    selector: 'textarea'
  });
</script>