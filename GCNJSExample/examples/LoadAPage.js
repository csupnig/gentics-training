GCN.page(1, function (page) {
    var name = page.prop('name');
    
    alert("Pagename: " + name);
    
    var content = page.tag('content').part('text');
    
    alert("content: " + content);
});