$(function() {
    $('#hrefOptions').css('text-decoration','underline');

    $.ajax({
        type: "GET",
        url: "/typeTask",
        dataType: "json",
        success: function(tasks){

            var html = "";

            for(var i=1; i<tasks.length; i++){
                if(tasks[i].id == tasks[0].id)
                    html += "<p><label><input name='type' type='radio' value='"+tasks[i].id+"' onchange='saveParameters()' checked />"+tasks[i].name+"</label></p>" ;
                else
                    html += "<p><label><input name='type' type='radio' value='"+tasks[i].id+"' onchange='saveParameters()' />"+tasks[i].name+"</label></p>" ;
            }

            $('#typeTask').html(html);

        }
    });

    saveParameters = function(){

        var taskId = $('input[name=type]:checked').val();

        $.ajax({
            type: "PUT",
            url: "/choseTypeTask",
            data: taskId,
            contentType: "application/json",
            dataType: "json",
            success: function(data){
                var html = "";
                html += "Saved("+data+").</br>";
                $('#outputResults').html(html);
            }});

    }

});