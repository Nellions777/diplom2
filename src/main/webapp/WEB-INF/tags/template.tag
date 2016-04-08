<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Handing parameters</title>
        <script src="js/jQuery/jquery.1.10.2.min.js"></script>
    </head>
    <body>
        <div align="center" style="font-size:x-large">
            <a id="hrefFillParams" href="/fillParams" style="text-decoration: none">Filling Parameters</a> |
            <a id="hrefOptions" href="/option" style="text-decoration: none">Options</a> |
            <a id="hrefStatistic" href="/statistic" style="text-decoration: none">Statistic</a>
        </div>
        <jsp:doBody />
    </body>

</html>