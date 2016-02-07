
$(function() {
    var jsonParams = '[{"name":"a","default":100},' +
                '{"name":"c","default":{"start":10,"finish":50,"step":10}},' +
                '{"name":"b"},' +
                '{"name":"h","default":30},' +
                '{"name":"t","default":{"start":1,"finish":56,"step":3}}]';
    var params = JSON.parse(jsonParams);

    var html = "";
    //dynamic formation the params
    for(var i=0; i<params.length; i++){
        html += '<label>' + params[i].name + ": ";
            if(params[i].hasOwnProperty('default')){
                if(params[i].default.hasOwnProperty('start')){
                    html += "<label>start: <input id='"+params[i].name+"_START' type='text' value='"+params[i].default.start+"' /></label>";
                    html += "<label>finish: <input id='"+params[i].name+"_FINISH' type='text' value='"+params[i].default.finish+"' /></label>";
                    html += "<label>step: <input id='"+params[i].name+"_STEP' type='text' value='"+params[i].default.step+"' /></label>";
                }else{
                    html += "<input id='"+params[i].name+"' type='text' value='";
                    html += params[i].default;
                    html += "'/>";
                }
            }else{
                html += "<input id='"+params[i].name+"' type='text' value=' ' />";
            }
            html += "</label><br/>";
    }
    $('#params').html(html);

    //params post to server
    postParams = function(){

        var paramsResult = new Array();

        for(var i=0; i<params.length; i++){
            if(params[i].hasOwnProperty('default')){
                if(params[i].default.hasOwnProperty('start')){
                    paramsResult.push({
                        name: params[i].name,
                        value: {
                            start: $('#'+params[i].name+"_START").val(),
                            finish: $('#'+params[i].name+"_FINISH").val(),
                            step: $('#'+params[i].name+"_STEP").val()
                        }});
                }else{
                    paramsResult.push({
                            name: params[i].name,
                            value: $('#'+params[i].name).val()
                        });
                }
            }else{
                paramsResult.push({
                        name: params[i].name,
                        value: $('#'+params[i].name).val()
                    });
            }
        }

        //try send
        $.ajax({
            type: "POST",
            url: "/params",
            data: paramsResult,
            dataType: "json",
            success: function(data){
                alert(data);
            }});
    }
});