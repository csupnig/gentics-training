//http://www.gentics.com/Content.Node/guides/gcn_js_api_page.html

GCN.page(1, function (page) {
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



//Async Programming
var myPage = GCN.folder(1).creatPage(1,{language:'de'}).save();

myPage.publiush();