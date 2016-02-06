
$(function() {
    var str = '[{"name":"a","default":100},{"name":"c","default":{"start":10,"finish":50,"step":10}},{"name":"b"}]';
    console.log(str);

    var obj = JSON.parse(str);
    console.log(obj);
    console.log(obj[1].name);
});