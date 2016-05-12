$(function() {
    $('#hrefShowResults').css('text-decoration','underline');

    var values;
    $.ajax({
        type: "GET",
        url: "/results",
        dataType: "json",
        success: function (data) {
            values = data;
        }
    });

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

    drawGraph = function(){
        var inputParam = $('#choiceInputParam').val();
        var outputParam = $('#choiceOutputParam').val();
        if(inputParam != null && outputParam != null){

            var inMas = [];
            var outMas = [];
            for(var i=0; i<values.length; i++){
                if(values[i].param.name == inputParam){
                    inMas.push(values[i].value)
                }else if(values[i].param.name == outputParam){
                    outMas.push(values[i].value);
                }
            }

            new Chartist.Line('.chart1', {
                labels: outMas,
                series: [
                    inMas
                ]
            }, {
                fullWidth: true,
                chartPadding: {
                    right: 50
                }
            });
        }
    };
});