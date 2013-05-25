//http://www.gentics.com/Content.Node/guides/gcn_js_api_folder.html
//http://www.gentics.com/Content.Node/guides/gcnjsapi/jsdoc/symbols/FileAPI.html


//List Files
GCN.folder(4).files(function(files){$.each(files, function(){console.log(this.id());});});


//List Images
GCN.folder(4).images(function(files){$.each(files, function(){console.log(this.id());});});


//Reas properties of image/file
GCN.image(191, function(image){console.log(image.prop('name'));});

//Load and manipulate image object properties
GCN.image(191).tag('object.copyright', function(tag){
	tag.part('text', 'testcopy');
	tag.parent().save();
});


//And some more sugar on chaining: The callback will always be called before we advance to the next step of the chain
//so this is doing the same thing as the snippet above
GCN.image(191).tag('object.copyright', function(tag){
	tag.part('text', 'testcopy');
}).parent().save();