//Solution to the previous task

GCN.folder(4).createFolder('cs',function(folder){
	folder.createPage(1, {language:'en'}, function(page){
		page.prop('name', 'new page');
		page.save().publish();
		console.log('success!');
	}, function() {
		console.log('Could not create page!');
	});
}, function(){console.log('Could not create folder!')});