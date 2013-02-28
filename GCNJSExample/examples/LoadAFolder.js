//http://www.gentics.com/Content.Node/guides/gcn_js_api_folder.html

GCN.folder(4, function (folder) {
    var name = folder.prop('name');
    
    alert("Foldername: " + name);
    
    var name_de = folder.tag('object.name_de').part('text');
    
    alert("Objectproperty: " + name_de);
});


//TASK: Write a JavaScript snippet, that prints the name of every page in the folder with the id 21