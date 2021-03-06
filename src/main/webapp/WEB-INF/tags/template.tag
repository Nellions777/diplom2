<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Handing parameters</title>
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="css/style.css" rel="stylesheet" type="text/css" />

        <script src="js/jQuery/jquery.1.10.2.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>

        <link rel="stylesheet" href="js/chartist/chartist.min.css">
        <script src="js/chartist/chartist.min.js"></script>
    </head>
    <body>
        <div align="center" style="font-size:x-large">
            <a id="hrefFillParams" href="/fillParams" style="text-decoration: none">Filling Parameters</a> |
            <a id="hrefShowResults" href="/showResults" style="text-decoration: none">Results</a> |
            <a id="hrefStatistic" href="/statistic" style="text-decoration: none">Statistic</a> |
            <a id="hrefOptions" href="/option" style="text-decoration: none">Options</a>
        </div>
        <jsp:doBody />
    </body>

</html>