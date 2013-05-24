(function(window, undefined) {
    if (window.Aloha === undefined || window.Aloha === null) {
        var Aloha = window.Aloha = {};
    }
    
    window.Aloha.settings.plugins.schulung={
        config: [ 'schulung default' ],
        editables: {
            '#GENTICS_METAEDITABLE_page_name': ['schulung header']
        }
    };
})(window);