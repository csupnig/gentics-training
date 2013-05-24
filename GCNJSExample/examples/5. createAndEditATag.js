//http://www.gentics.com/Content.Node/guides/gcnjsapi/jsdoc/symbols/PageAPI.html

//Via Aloha Integration Plugin
Aloha.ready(function () {
    Aloha.require(['gcn/gcn-plugin'], function (plugin) {
        plugin.page.createTag('image').edit(function (html, tag, data) {
            plugin.appendBlock('.aloha-editable:first', tag, data);
        });
    });
});


//Own implementation
var mytag = Aloha.GCN.page.createTag('image', function(tag){
	console.log('success');
	tag.edit('div.contentfooter');
});



//Remove a tag.
Aloha.GCN.page.tag('image4', function(tag){
	tag.remove();
	tag.parent().save();
});

