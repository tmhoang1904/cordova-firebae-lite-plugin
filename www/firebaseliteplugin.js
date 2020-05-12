var exec = require('cordova/exec');

var FirebaseLite = function(){};

FirebaseLite.prototype.getToken = function (arg0, success, error) {
    exec(success, error, 'firebaseliteplugin', 'get_fcm_token', [arg0]);
};

FirebaseLite.install = function () {
    if (!window.plugins) {
        window.plugins = {};
    }
    window.plugins.FirebaseLite = new FirebaseLite();
    return window.plugins;
};

cordova.addConstructor(FirebaseLite.install);
