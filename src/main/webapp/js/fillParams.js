
$(function() {
    $('#hrefFillParams').css('text-decoration','underline');

    var parameters;

    $.ajax({
        type: "GET",
        url: "/defaultParams",
        dataType: "json",
        success: function(params){
            $('h1').html(params[0]);
            parameters = params[1];
            generationBeginParams();
        }
    });

    generationBeginParams = function(){
        var html = "";
        for(var i in parameters){
            html += '<label>' + parameters[i].name + ": ";
            html += "<input id='"+parameters[i].name+"' type='text' value='"+ parameters[i].value +"'/>&nbsp&nbsp";
            html += "<input type='checkbox' checked onchange='changeIsFixed("+i+",false)' />";
            html += "</label><br/><br/>";
        }
        $('#params').html(html);
    };

    generationParams = function(){
        var html = "";
        //dynamic formation the params
        for(var i in parameters){

            html += '<label>' + parameters[i].name + ": ";

            if(parameters[i].step == 0){
                html += "<input id='"+parameters[i].name+"' type='text' value='";
                html += parameters[i].value;
                html += "'/>&nbsp&nbsp";

                html += "<input type='checkbox' checked onchange='changeIsFixed("+i+",false)' />";
            }else{
                html += "<label>start: <input id='"+parameters[i].name+"_START' type='text' value='"+parameters[i].start+"' /></label>&nbsp&nbsp";
                html += "<label>finish: <input id='"+parameters[i].name+"_END' type='text' value='"+parameters[i].end+"' /></label>&nbsp&nbsp";
                html += "<label>step: <input id='"+parameters[i].name+"_STEP' type='text' value='"+parameters[i].step+"' /></label>&nbsp&nbsp";

                html += "<input type='checkbox' onchange='changeIsFixed("+i+",true)' />";
            }
            html += "</label><br/><br/>";

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
            parameters[index].end = 0;
        }

        for(var i in parameters){
            if(i != index){
                if(parameters[i].step == 0){
                    parameters[i].value = $('#'+parameters[i].name).val();
                }else{
                    parameters[i].start = $('#'+parameters[i].name+"_START").val();
                    parameters[i].end = $('#'+parameters[i].name+"_END").val();
                    parameters[i].step = $('#'+parameters[i].name+"_STEP").val();
                }
            }
        }

        generationParams();
    };

    //params post to server
    postParams = function(){

        for(var i in parameters){
            if(parameters[i].step == 0){
                parameters[i].value = $('#'+parameters[i].name).val();
            }else{
                parameters[i].start = $('#'+parameters[i].name+"_START").val();
                parameters[i].end = $('#'+parameters[i].name+"_END").val();
                parameters[i].step = $('#'+parameters[i].name+"_STEP").val();
            }
        }

        //console.log(parameters);

        //try send
        $.ajax({
            type: "POST",
            url: "/calculate",
            data: JSON.stringify(parameters),
            contentType: "application/json",
            dataType: "json",
            success: function(data){
                var html = "";
                html += "Обработка параметров закончена.</br>";
                html += data;
                $('#outputResults').html(html);
            }});
    }
});