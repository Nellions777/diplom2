$(function() {
    $('#hrefShowResults').css('text-decoration','underline');
    var idParam = -1;
    var parameterValues = null;

    viewResult = function(type){
        if(type === "Table"){
            $('#resultTable').removeClass("hidden");
            $('#resultTable').addClass("show");
            $('#resultGraph').removeClass("show");
            $('#resultGraph').addClass("hidden");

        }else{
            $('#resultTable').removeClass("show");
            $('#resultTable').addClass("hidden");
            $('#resultGraph').removeClass("hidden");
            $('#resultGraph').addClass("show");
        }
    };

    changed = function(select,values){
        parameterValues = values;
        $('select[id^="paramValueForGraphic_"]').css('display', 'block');
        $('#paramValueForGraphic_'+select).css('display', 'none');

        idParam = select;
    };

    drawGraph = function(){

        var params = {};
        var paramValues = $('select[id^="paramValueForGraphic_"]');
        for(var i=0; i<paramValues.length; i++){
            var id = $('select[id^="paramValueForGraphic_"]')[i].id;
            var name = $('select[id^="paramValueForGraphic_"]')[i].name;
            var value = $('select[id^="paramValueForGraphic_"]')[i].value;
            if(id != "paramValueForGraphic_"+idParam) params[name] = value;
        }
        params[$('#outParamValueForGrafic').val()] = '-1';

        $.ajax({
            type: "POST",
            url: "/outValues",
            data: JSON.stringify(params),
            contentType: "application/json",
            dataType: "json",
            success: function (data) {
                var masValues = parameterValues.substr(1,parameterValues.length-2).split(',');

                new Chartist.Line('.chart1', {
                    labels: masValues,
                    series: [
                        data
                    ]
                }, {
                    fullWidth: true,
                    chartPadding: {
                        right: 50
                    }
                });
            }
        });

    };
});