<%--
  Created by IntelliJ IDEA.
  User: Евгений
  Date: 20.09.2015
  Time: 21:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Parameters</title>
    <script src="js/jQuery/jquery.1.10.2.min.js"></script>
    <script src="js/init.js"></script>
</head>
<body>

    <h1>Заполните параметры</h1>
    <div id="params" name="params"></div>
    <input type="button" value="Send parameters" onclick="postParams()"/>

    <br/>

    <div id="outputResults"></div>

</body>
</html>