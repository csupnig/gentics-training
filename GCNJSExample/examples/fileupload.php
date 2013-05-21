<?php include('proxy/settings.conf.php') ?>
<!doctype html>
<head>
	<title>Gentics Training</title>

	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="description" content="Gentics Training">
	<script src="libs/jquery-1.9.1.min.js"></script>
	<script src="<?=$PROXYNAME ?>CNPortletapp/latest/gcnjsapi/bin/gcnjsapi.js"></script>
	<script>
	GCN.settings.BACKEND_PATH="<?=$PROXYNAME ?>CNPortletapp";
	</script>
</head>
<body>
	<input id="fileupload" type="file" name="files[]" multiple>
    <a href="#" id="startUpload">Upload</a>
 
    <!-- 
        The jQuery UI widget factory, can be omitted if jQuery UI is already
        included 
    -->
    <script src="libs/vendor/jquery.ui.widget.js"></script>
 
    <!-- 
        The Iframe Transport is required for browsers without 
         support for XHR file uploads 
    -->
    <script src="libs/jquery.iframe-transport.js"></script>
    <!-- The basic File Upload plugin -->
    <script src="libs/jquery.fileupload.js"></script>
 
    <!-- 
        The XDomainRequest Transport is included for cross-domain file 
        deletion for IE8+ 
    -->
    <!--[if gte IE 8]>
        <script src="libs/cors/jquery.xdr-transport.js"></script>
    <![endif]-->
 
    <script>
 
        GCN.sub('authentication-required', function(proceed, cancel) {
            // Login using node/node
            GCN.login('node', 'node', function(success) {
                success ? proceed() : cancel();
            });
        });
         
        GCN.sub('error-encountered' ,function (error) {
            alert(error.toString());
        });
 
        /**
         * Success handler for the upload
         */
        function onSuccess(file, messages) {
            console.log('Upload successful:');
            console.log("File Id: " + file.id());
            console.log("Filename: " + file.prop('name'));
        }
         
        /**
         * Completion handler for the upload
         */
        function onComplete(result, textStatus, jqXHR) {
            console.log('Upload completed');
        }
         
        /**
         * Error handler for the upload
         */
        function onError(responseCode, responseMessage, messages) {
            alert('Error during upload - code:' + responseCode + 
                " ,message: " + responseMessage);
            console.dir(messages);
        }
         
        /**
         * This method can be used to prepare the uploader to handle the
         * folder specific callback methods.
         */
        function prepareHandleUpload(folder) {
             
            // Automatically submit data  
            $('#startUpload').fileupload({
                add: function(e, data) {
                    // Start the upload process
                    var jqXHR = data.submit();
                     
                    jqXHR.success(function (result, textStatus, jqXHR) {
                        /*
                         * Pass the result to the folder handleUpload
                         * response method. This method will inspect the
                         * result and invoke the given success or error
                         * callback. 
                         */
                        folder.handleUploadResponse(result, 
                                                    onSuccess, 
                                                    onError);
                    });
                     
                    jqXHR.error(function (jqXHR, textStatus, errorThrown) { 
                        onError(textStatus);
                    });
 
                    jqXHR.complete(onComplete);
                }
            });
        }
         
       
         
        $(document).ready(function() {
        	             
            // Handle the upload link click event
            $('#startUpload').click( function() {
                 
                var filesList = $('#fileupload').prop('files');
 
                // Check whether a file was already selected
                if("object" == jQuery.type(filesList)) {
                     
                    /*  
                     * Load the folder and get an upload URL for that folder.
                     * This URL can be used to add the chosen file to the
                     * fileuploader. The 'add' action will automatically
                     * start the upload process.
                     */
                    GCN.folder(4, function(folder) {
                        prepareHandleUpload(folder);
                        var restUrl = folder.uploadURL();
                        var files = { files: filesList, url: restUrl };
                        $('#startUpload').fileupload('add', files);
                    });
                     
                } else {
                    alert('Please choose a file first');
                }
                     
            });
         
        });
    </script>
</body>
</html>
