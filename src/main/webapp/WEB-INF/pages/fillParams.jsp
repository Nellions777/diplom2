<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<t:template>

        <script src="js/fillParams.js"></script>

        <h2 class="text-center"></h2>
        <div id="params" name="params"></div>

    <div style="margin-left: 30px">
        <input type="button" value="Send parameters" onclick="postParams()"/>
        <br/>
        <br/>
        <div id="outputResults"></div>
    </div>

</t:template>