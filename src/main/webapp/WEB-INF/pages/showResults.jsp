<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<t:template>
    <script src="js/showResults.js"></script>

    <h2 class="text-center">${params[0]['typeTask']['name']}</h2>

    <table class="table table-bordered table-condensed text-center">
        <thead>
            <tr>
                <c:forEach items="${params}" var="parameter">
                    <c:if test="${parameter['isInput']}">
                        <th class='text-center info'>${parameter["name"]}</th>
                    </c:if>
                </c:forEach>
                <c:forEach items="${params}" var="parameter">
                    <c:if test="${parameter['isInput'] == false}">
                        <th class='text-center warning'>${parameter["name"]}</th>
                    </c:if>
                </c:forEach>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${results}" var="result">
            <tr>
                <c:forEach items="${result['values']}" var="value">
                    <c:if test="${value['param']['isInput']}">
                        <td><c:out value="${value['value']}"/></td>
                    </c:if>
                </c:forEach>
                <c:forEach items="${result['values']}" var="value">
                    <c:if test="${value['param']['isInput'] == false}">
                        <td><c:out value="${value['value']}"/></td>
                    </c:if>
                </c:forEach>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</t:template>
