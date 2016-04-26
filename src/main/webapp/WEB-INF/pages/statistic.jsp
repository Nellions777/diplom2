<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<t:template>
    <script src="js/statistic.js"></script>

    <h3>Статистика</h3>
    Server status is started: <span id="isStarted"></span>
    <br>
    Client count: <span id="countClient"></span>
    <br>
    <a href="/startServer.html">Start server</a>
    <a href="/stopServer.html">Stop server</a>
</t:template>
