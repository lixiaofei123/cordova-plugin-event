var exec = cordova.require('cordova/exec');



function event(type,data){
    exec(null,null,'Event','event',[type,data])
}


module.exports = {
    event: event,
}