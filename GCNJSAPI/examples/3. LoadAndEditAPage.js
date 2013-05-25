//http://www.gentics.com/Content.Node/guides/gcn_js_api_page.html
//http://www.gentics.com/Content.Node/guides/gcnjsapi/jsdoc/symbols/FolderAPI.html

//.edit() will only work if Aloha is loaded

var myPage = GCN.page(1, function (page) {
    var name = page.prop('name');
    
    alert("Pagename: " + name);
    
    
    //Specify a selector where to edit the content
    page.tag('content').edit("#content");
    
    page.tag('teasers').part('text', 'my content');
});


//save the page;
myPage.save();


//save and publish the page;
myPage.save().publish();



//TASK 1: Implement custom save and publish buttons in a CMS Page

//TASK 2: Create a button that adds a folder containig a page