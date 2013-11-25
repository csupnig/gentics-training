<?php include('proxy/settings.conf.php') ?>
<!doctype html>
<head>
	<title>Gentics Training</title>

	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="description" content="Gentics Training">
	<script src="libs/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<?=$PROXYNAME ?>CNPortletapp/latest/gcnjsapi/bin/gcnjsapi.js"></script>
	<script>
	GCN.settings.BACKEND_PATH="<?=$PROXYNAME ?>CNPortletapp";
	</script>
</head>
<body>
	<h1>Gentics GCN JS Api Training</h1>
	<div id="content"></div>
</body>
</html>
