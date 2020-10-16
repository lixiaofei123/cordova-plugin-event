var exec = cordova.require('cordova/exec');



function event(type,data){
    exec(null,null,'eventPlugin','event',[type,data])
}


module.exports = {
    event: event,
}