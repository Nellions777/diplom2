$(function() {
    $('#hrefOptions').css('text-decoration','underline');

    $.ajax({
        type: "GET",
        url: "/typeTask",
        dataType: "json",
        success: function (data) {

            var html = "";

            for (var i = 0; i < data[1].length; i++) {
                if (data[1][i].id == data[0])
                    html += "<p><label><input name='type' type='radio' value='" + data[1][i].id + "' onchange='saveParameters()' checked />" + data[1][i].name + "</label></p>";
                else
                    html += "<p><label><input name='type' type='radio' value='" + data[1][i].id + "' onchange='saveParameters()' />" + data[1][i].name + "</label></p>";
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