var myPage = GCN.page(1, function (page) {
    var name = page.prop('name');
    
    alert("Pagename: " + name);
    
    
    //Specify a selector where to edit the content
    page.tag('content').edit("#GENTICS_EDITABLE_2132");
    
    
});


//save the page;
myPage.save();


//save the page;
myPage.save().publish();