<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title><tiles:getAsString name="title"/></title>
    <link href="<c:url value='/static/css/bootstrap.min.css' />" rel="stylesheet">

    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet">

    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<tiles:insertAttribute name="header"/>

<div class="container-fluid">
    <div class="row">
        <tiles:insertAttribute name="menu"/>
        <tiles:insertAttribute name="body"/>
    </div>


    <tiles:insertAttribute name="footer"/>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="<c:url value='/static/js/bootstrap.min.js' />"></script>
</body>
</html>