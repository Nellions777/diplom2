$(function() {
    $('#hrefStatistic').css('text-decoration','underline');

    updateStatistic = function(){
        $.ajax({
            type: "GET",
            url: "/statisticData",
            dataType: "json",
            success: function(data){
                //alert(data[0] + " " + data[1]);
                if(data[0]) $('#isStarted').html("true");
                else $('#isStarted').html("false");
                $('#countClient').html(data[1]);
            }
        });
    }
    updateStatistic();
    setInterval(updateStatistic, 5000);

});