//http://www.gentics.com/Content.Node/guides/gcn_js_api_page.html

var page = GCN.page(1, function (page) {
    var name = page.prop('name');
    
    alert("Pagename: " + name);
    
    var content = page.tag('content').part('text');
    
    alert("content: " + content);
});


//load page 5 from the server without locking it 
GCN.page(5, { update: false }, function (page) {
	var name = page.prop('name');
    
    alert("Pagename: " + name);
    
    var content = page.tag('content').part('text');
    
    alert("content: " + content);
});

//TASK: Write a JavaScript snippet, that prints the name of every page in the folder with the id 21