$(function() {
    $('#hrefOptions').css('text-decoration','underline');

    $.ajax({
        type: "GET",
        url: "/typeTask",
        dataType: "json",
        success: function(tasks){

            var html = "";

            for(var i=1; i<tasks.length; i++){
                if(tasks[i] == tasks[0])
                    html += "<p><label><input name='type' type='radio' value='"+tasks[i]+"' onchange='saveParameters()' checked />"+tasks[i]+"</label></p>" ;
                else
                    html += "<p><label><input name='type' type='radio' value='"+tasks[i]+"' onchange='saveParameters()' />"+tasks[i]+"</label></p>" ;
            }

            $('#typeTask').html(html);

        }
    });

    saveParameters = function(){

        var radio = $('input[name=type]:checked').val();

        $.ajax({
            type: "PUT",
            url: "/choseTypeTask",
            data: radio,
            contentType: "application/json",
            dataType: "json",
            success: function(data){
                var html = "";
                html += "Saved("+data+").</br>";
                $('#outputResults').html(html);
            }});

    }

});