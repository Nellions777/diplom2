
$(function() {
    $('#hrefFillParams').css('text-decoration','underline');

    var parameterForServer = {
        simpleParams: [],
        rangeParams: [],
        paramsIdForDivOnPackages:[]
    };
    var parameters = [];

    $.ajax({
        type: "GET",
        url: "/defaultParams",
        dataType: "json",
        success: function(params){
            //console.log(params);
            $('h1').html(params[0].typeTask.name);
            for(var i in params){
                parameters.push({
                    param:params[i],
                    value:0.0,
                    start:0.0,
                    finish:0.0,
                    step:0.0
                });
            }
            generationParams();
        }
    });

    generationParams = function(){
        var html = "";
        html += "<table>";
        html += "<thead><tr><th>Fixed</th><th>Param value</th></tr></thead>";
        html += "<tbody>";
        for(var i=0; i<parameters.length; i++){
            html += "<tr>";
            if(parameters[i].step === 0){

                html += "<td><input type='checkbox' checked onchange='changeIsFixed(" + i + ",false)' /></td>";
                html += '<td><label>' + parameters[i].param.name + ": ";
                html += "<input id='" + parameters[i].param.id + "' type='text' value='";
                html += parameters[i].value;
                html += "'/></td>";

            }else{
                html += "<td><input type='checkbox' onchange='changeIsFixed(" + i + ",true)' /></td>";
                html += '<td><label>' + parameters[i].param.name + ": ";
                html += "<label>start: <input id='" + parameters[i].param.id + "_START' type='text' value='" + parameters[i].start + "' /></label>&nbsp&nbsp";
                html += "<label>finish: <input id='" + parameters[i].param.id + "_FINISH' type='text' value='" + parameters[i].finish + "' /></label>&nbsp&nbsp";
                html += "<label>step: <input id='" + parameters[i].param.id + "_STEP' type='text' value='" + parameters[i].step + "' /></label></td>";
            }
            html += "<tr>";
        }
        $('#params').html(html);
    };

    changeIsFixed = function(index,isFixed){
        if(isFixed) {
            parameters[index].step = 0;
            parameters[index].value = 0;
        }
        else {
            parameters[index].step = 1;
            parameters[index].start = 0;
            parameters[index].finish = 0;
        }
        updateParameters(index);
        generationParams();
    };

    updateParameters = function(index) {
        for(var i=0; i<parameters.length; i++){
            if(i != index){
                if(parameters[i].step == 0){
                    parameters[i].value = $('#' + parameters[i].param.id).val();
                }else{
                    parameters[i].start = $('#' + parameters[i].param.id + "_START").val();
                    parameters[i].finish = $('#' + parameters[i].param.id+ "_FINISH").val();
                    parameters[i].step = $('#' + parameters[i].param.id+ "_STEP").val();
                }
            }
        }
    }
    //params post to server
    postParams = function(){

        updateParameters(-1);

        for(var i=0; i<parameters.length; i++){
            if(parameters[i].step == 0){
                parameterForServer.simpleParams.push({
                    param: parameters[i].param,
                    value: parameters[i].value
                });
            }else{
                parameterForServer.rangeParams.push({
                    param: parameters[i].param,
                    start: parameters[i].start,
                    finish: parameters[i].finish,
                    step: parameters[i].step
                });
            }
        }
        parameterForServer.paramsIdForDivOnPackages.push(1);
        parameterForServer.paramsIdForDivOnPackages.push(2);

        //try send
        $.ajax({
            type: "POST",
            url: "/calculate",
            data: JSON.stringify(parameterForServer),
            contentType: "application/json",
            dataType: "json",
            success: function(){
                var html = "";
                html += "Обработка параметров закончена.</br>";
                $('#outputResults').html(html);
            }});
    }
});