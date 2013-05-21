(function(window, undefined) {
    if (window.Aloha === undefined || window.Aloha === null) {
        var Aloha = window.Aloha = {};
    }

    window.Aloha.settings = {
        toolbar: {
            tabs: [
                    {
                        label: 'View',
                        showOn: { scope: 'Aloha.continuoustext' },
                        components: [
                            [
                                'toggleSchulung'
                            ]
                        ]
                    }
                ]   
        }
    };
})(window);