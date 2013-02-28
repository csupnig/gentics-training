GCN.sub('authentication-required', function (proceed, cancel) {
    var user = prompt('Username:');
    var pass = prompt('Password:');
 
    GCN.login(user, pass, function(success, data) {
        if (success) {
            proceed();
        } else {
            cancel();
        }
    });
});