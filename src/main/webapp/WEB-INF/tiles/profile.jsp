<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url var="img" value="/img" />
<c:url var="profilePhoto" value="/profilePhoto" />
<c:url var="editProfile" value="/editprofile" />

<div class="row">
    <div class="col-md-10 col-md-offset-1 col-sm-8 col-sm-offset-2">

        <div class="profile-about">
            <div class="profile-image">
                <img src="${profilePhoto}" />
            </div>

            <div class="profile-text">
                <c:choose>
                    <c:when test="${profile.about==null}">
                        Click 'edit' to add information about yourself
                    </c:when>
                    <c:otherwise>
                        ${profile.about}
                        Hey
                    </c:otherwise>
                </c:choose>
            </div>

        </div>

        <p>&nbsp;</p>

        <c:url var="photoLink" value="/upload-profile-photo" />
        <form method="post" action="${photoLink}" enctype="multipart/form-data">

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

            Choose Your Profile Photo: <input type="file" name="file" accept="image/*" />
            <input type="submit" value="upload"/>

        </form>

        <div class="profile-about-edit">
            <a href="${editProfile}">edit</a>
        </div>

    </div>
</div>