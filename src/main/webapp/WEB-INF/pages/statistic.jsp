<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<t:template>
    <script src="js/statistic.js"></script>

    <h3>Статистика</h3>
    Server status is started: ${started}
    <br>
    Client count: ${clientCount}
    <br>
    <a href="/startServer.html">Start server</a>
    <a href="/stopServer.html">Stop server</a>
</t:template>
