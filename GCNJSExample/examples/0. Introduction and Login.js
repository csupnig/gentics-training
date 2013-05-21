//GCN JS API is a library that allows CRUD operations on Gentics Content.Node operations.

//It is an abstraction to the REST-API and provide chainable methods/objects for Gentics Content.Node objects.

//Almost all chainable functions take optional success and error callbacks as the last two arguments, the error method 
//can be used to override the default error handling.



//CHAINING
//GCN is always the start of the chain. Not all methods are chainable (exceptions: sub, login,...). Check the API Documentation
//for thair return objects

//Everything (except createTag()) is local until we call save()


//A complicated chain:
GCN.page(1).folder().tag('object.foldername_de', function(tag){
	var page = tag.parent().parent();
	page.prop('name', tag.part('text'));
	page.save().publish();
});

//Task 1: Try to write this with common jQuery ajax functions and the REST-API

//.prop / .part are not chainable they immediately return their value



//Default Error Handler
// subscribing to error messages
GCN.sub('error-encountered', function (error) {
    console.log('an error occured: ' + error.toString());
});



//Authentication
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