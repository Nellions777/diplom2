<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<t:template>
    <script src="js/showResults.js"></script>

    <h2 class="text-center">${params[0]['typeTask']['name']}</h2>

    <div class="blockItem">
        <select class="choiceViewResult center-block" onchange="viewResult(this.value)" >
            <option id="Table" selected>Table</option>
            <option id="Graph">Graph</option>
        </select>

        <div id="resultTable" class="resultTable" >
            <table class="table table-bordered table-condensed text-center">
                <thead>
                    <tr>
                        <c:forEach items="${params}" var="parameter">
                            <c:if test="${parameter['isInput']}">
                                <th class='text-center info tdHeader'>${parameter["name"]}</th>
                            </c:if>
                        </c:forEach>
                        <c:forEach items="${params}" var="parameter">
                            <c:if test="${parameter['isInput'] == false}">
                                <th class='text-center warning'>${parameter["name"]}</th>
                            </c:if>
                        </c:forEach>
                        <th class='text-center success'>client</th>
                        <th class='text-center success'>time (ms)</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${resultsOfValues}" var="result">
                    <tr>
                        <c:forEach items="${params}" var="parameter">
                            <c:if test="${parameter['isInput']}">
                                <td><c:out value="${result[parameter['id']]['value']}"/></td>
                            </c:if>
                        </c:forEach>
                        <c:forEach items="${params}" var="parameter">
                            <c:if test="${parameter['isInput'] == false}">
                                <td><c:out value="${result[parameter['id']]['value']}"/></td>
                            </c:if>
                        </c:forEach>
                        <td><c:out value="${result[params[0]['id']]['result']['client']}"/></td>
                        <td><c:out value="${result[params[0]['id']]['result']['timeTask']}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <!-- График -->
        <div id="resultGraph" class="resultGraph hidden">
            <table class="table table-bordered table-condensed">
                <thead>
                    <tr><th>changed</th><th>name</th><th>values</th></tr>
                </thead>
                <tbody>
                <c:forEach items="${paramsWithValues}" var="parameter">
                    <c:if test="${parameter['key']['isInput']}">
                        <tr>
                            <td>
                                <c:if test="${fn:length(parameter['value']) > 1}">
                                    <input name="changed" type="radio" onchange="changed('${parameter["key"]["id"]}','${parameter['value']}')"/>
                                </c:if>
                            </td>
                            <td>
                                ${parameter["key"]["name"]}
                            </td>
                            <td>
                                <select id="paramValueForGraphic_${parameter["key"]["id"]}" name="${parameter["key"]["name"]}">
                                    <c:if test="${fn:length(parameter['value']) == 1}">
                                        <option>${parameter['value'][0]}</option>
                                    </c:if>
                                    <c:if test="${fn:length(parameter['value']) > 1}">
                                        <option selected value="-1" disabled>Выберите параметр</option>
                                        <c:forEach items="${parameter['value']}" var="value">
                                            <option>${value}</option>
                                        </c:forEach>
                                    </c:if>
                                </select>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>

            <select id="outParamValueForGrafic">
                <option selected value="-1" disabled>Выберите выходной параметр</option>
                <c:forEach items="${paramsWithValues}" var="parameter">
                    <c:if test="${parameter['key']['isInput'] == false}">
                        <option>${parameter['key']['name']}</option>
                    </c:if>
                </c:forEach>
            </select>

            <input type="button" onclick="drawGraph()" value="draw">

            <div class="container-chart">
                <div class="chart1 ct-chart ct-golden-section"></div>
            </div>
        </div>

    </div>

</t:template>
