
$(function() {
    /*var jsonParams = '[{"name":"a","default":100},' +
                '{"name":"b","default":{"start":0,"finish":1,"step":1}},' +
                '{"name":"c","default":{"start":10,"finish":40,"step":10}},' +
                '{"name":"d","default":{"start":1,"finish":7,"step":3}}]';*/
    var parameters;

    $.ajax({
        type: "GET",
        url: "/defaultParams",
        dataType: "json",
        success: function(params){

            parameters = params;

            var html = "";
            //dynamic formation the params
            for(var i in params){

                html += '<label>' + params[i].name + ": ";
                if(params[i].isOneValue){
                    html += "<input id='"+params[i].name+"' type='text' value='";
                    html += params[i].value;
                    html += "'/>";
                }else{
                    html += "<label>start: <input id='"+params[i].name+"_START' type='text' value='"+params[i].start+"' /></label>&nbsp&nbsp";
                    html += "<label>finish: <input id='"+params[i].name+"_END' type='text' value='"+params[i].end+"' /></label>&nbsp&nbsp";
                    html += "<label>step: <input id='"+params[i].name+"_STEP' type='text' value='"+params[i].step+"' /></label>&nbsp&nbsp";
                }
                html += "</label><br/><br/>";
            }
            $('#params').html(html);
        }
    });

    //params post to server
    postParams = function(){

        for(var i in parameters){
            if(parameters[i].isOneValue){
                parameters[i].value = $('#'+parameters[i].name).val();
            }else{
                parameters[i].start = $('#'+parameters[i].name+"_START").val();
                parameters[i].end = $('#'+parameters[i].name+"_END").val();
                parameters[i].step = $('#'+parameters[i].name+"_STEP").val();
            }
        }

        console.log(parameters);

        //try send
        $.ajax({
            type: "POST",
            url: "/results",
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