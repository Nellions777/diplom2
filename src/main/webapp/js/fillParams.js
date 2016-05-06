
$(function() {
    $('#hrefFillParams').css('text-decoration','underline');

    var parameterForServer = {
        simpleParams: [],
        rangeParams: [],
        paramsIdForDivOnPackages:[]
    };
    var parameters = [];
    var divisors = [];

    $.ajax({
        type: "GET",
        url: "/defaultParams",
        dataType: "json",
        success: function(params){
            //console.log(params);
            $('h2').html(params[0].typeTask.name);
            for(var i in params){
                parameters.push({
                    param:params[i],
                    value:0.0,
                    start:0.0,
                    finish:0.0,
                    step:0.0,
                    isDivisor:false
                });
            }
            generationParams();
        }
    });

    generationParams = function(){
        var sumCounts = 1;
        var html = "";
        html += "<table class='table table-condensed text-center'>";
        html += "<thead><tr><th class='text-center'>Fixed</th><th class='text-center'>isDivisor</th><th class='text-center'>Param value</th></tr></thead>";
        html += "<tbody>";
        for(var i=0; i<parameters.length; i++){
            html += "<tr>";
            if(parameters[i].step === 0){

                html += "<td><input type='checkbox' checked onchange='setFixed(" + i + ",false)' /></td>";
                html += "<td><input type='checkbox' disabled /></td>"
                html += '<td><label>' + parameters[i].param.name + ": ";
                html += "<input id='" + parameters[i].param.id + "' type='text' value='";
                html += parameters[i].value;
                html += "'/></td>";
            }else{
                html += "<td><input type='checkbox' onchange='setFixed(" + i + ",true)' /></td>";
                html += "<td><input type='checkbox' ";
                    if(parameters[i].isDivisor) {
                        html += "checked ";
                        sumCounts *= divisors[i];
                    }
                html += "onchange='setDivisor("+ i +")' /></td>";
                html += '<td>' + parameters[i].param.name + ": ";
                html += "<label>start: <input id='" + parameters[i].param.id + "_START' type='text' value='" + parameters[i].start + "' onblur='updateParameters("+i+",-1);generationParams()' /></label>&nbsp&nbsp";
                html += "<label>finish: <input id='" + parameters[i].param.id + "_FINISH' type='text' value='" + parameters[i].finish + "' onblur='updateParameters("+i+",-1);generationParams()' /></label>&nbsp&nbsp";
                html += "<label>step: <input id='" + parameters[i].param.id + "_STEP' type='text' value='" + parameters[i].step + "' onblur='updateParameters("+i+",-1);generationParams()' /></label>&nbsp&nbsp";
                html += "count: <span id='" + parameters[i].param.id + "_COUNT'>" + divisors[i] + "</span></td>";
            }
            html += "<tr>";
        }
        html += "</tbody>";
        html += "</table>";
        html += "count of packages: <span>" + sumCounts + "</span></td>";
        $('#params').html(html);
    };

    setDivisor = function(index){
        if(parameters[index].isDivisor === false){
            parameters[index].isDivisor = true;
        }
        else parameters[index].isDivisor = false;
        divisors[index] = Math.ceil(((parameters[index].finish - parameters[index].start)/parameters[index].step)+1);
        generationParams();
    };

    setFixed = function(index,isFixed){
        if(isFixed) {
            parameters[index].step = 0;
            parameters[index].value = 0;
            parameters[index].isDivisor = false;
        }
        else {
            parameters[index].step = 1;
            parameters[index].start = 0;
            parameters[index].finish = 0;
        }
        updateParameters(index,index);
        generationParams();
    };

    updateParameters = function(index, executing) {
        for(var i=0; i<parameters.length; i++){
            if(i != executing){
                if(parameters[i].step == 0){
                    parameters[i].value = $('#' + parameters[i].param.id).val();
                }else{
                    parameters[i].start = $('#' + parameters[i].param.id + "_START").val();
                    parameters[i].finish = $('#' + parameters[i].param.id+ "_FINISH").val();
                    parameters[i].step = $('#' + parameters[i].param.id+ "_STEP").val();
                }
            }
        }
        if(index != -1) divisors[index] = Math.ceil(((parameters[index].finish - parameters[index].start)/parameters[index].step)+1);
    };

    postParams = function(){

        updateParameters(-1,-1);

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
                if(parameters[i].isDivisor) parameterForServer.paramsIdForDivOnPackages.push(parameters[i].param.id);
            }
        }

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

        parameterForServer.simpleParams.length = 0;
        parameterForServer.rangeParams.length = 0;
        parameterForServer.paramsIdForDivOnPackages.length = 0;
    }
});