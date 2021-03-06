<?php
// The URL of the backend server that browser requests should be proxied to
$CMS_SERVERHOST = 'http://schulung.gentics.com/';
//$CMS_SERVERHOST = 'http://demo-cms.gentics.com/';

// The path to the proxy script on the frontend server
$PROXYNAME = '/gentics-training/GCNJSAPI/examples/proxy/';

// This is needed for Gentics Content.Node to set the URL's to this proxy.
// You can add multiple parameters by just appending them with a &
$HTTP_URL_ADD_QUERY_PARAMETERS = 'proxyprefix=' . $PROXYNAME;

// Max times to follow a HTTP redirection response to a new URL
$HTTP_MAX_REDIRECTS = 10;
