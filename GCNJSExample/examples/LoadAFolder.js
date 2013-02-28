GCN.folder(4, function (folder) {
    var name = folder.prop('name');
    
    alert("Foldername: " + name);
    
    var name_de = folder.tag('object.name_de').part('text');
    
    alert("Objectproperty: " + name_de);
});