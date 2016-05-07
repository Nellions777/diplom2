<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<t:template>
    <div class="blockItem">
        <script src="js/fillParams.js"></script>

        <h2 class="text-center"></h2>
        <div id="params" name="params"></div>


        <input type="button" value="Send parameters" onclick="postParams()"/>
        <br/>
        <br/>
        <div id="outputResults"></div>
    </div>

</t:template>