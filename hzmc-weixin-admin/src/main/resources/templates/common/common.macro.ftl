<#assign base = request.contextPath />
<#macro commonStyle>

<meta charset="utf-8">
<title>管理平台</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<!-- Bootstrap 3.3.5 -->
<link rel="stylesheet" href="${base}/adminlte/bootstrap/css/bootstrap.min.css">
<!-- Font Awesome -->
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css"> -->
<link rel="stylesheet" href="${base}/plugins/font-awesome-4.5.0/css/font-awesome.min.css">
<!-- Ionicons -->
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css"> -->
<link rel="stylesheet" href="${base}/plugins/ionicons-2.0.1/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="${base}/adminlte/dist/css/AdminLTE-local.min.css">
<!-- AdminLTE Skins. Choose a skin from the css/skins folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet" href="${base}/adminlte/dist/css/skins/_all-skins.min.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->

<!-- pace -->
<link rel="stylesheet" href="${base}/plugins/pace/themes/pace-theme-flash.css">
</#macro>

<#macro commonScript>
<!-- jQuery 2.1.4 -->
<script src="${base}/adminlte/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.5 -->
<script src="${base}/adminlte/bootstrap/js/bootstrap.min.js"></script>
<!-- --->
<script src="${base}/adminlte/angular/angular.min.js"></script>
<script src="${base}/adminlte/angular/angular-route.js"></script>
<script src="${base}/adminlte/angular/angular-route.min.js"></script>
<!-- FastClick -->
<script src="${base}/adminlte/plugins/fastclick/fastclick.min.js"></script>
<!-- AdminLTE App -->
<script src="${base}/adminlte/dist/js/app.min.js"></script>
<#-- jquery.slimscroll -->
<script src="${base}/adminlte/plugins/slimScroll/jquery.slimscroll.min.js"></script>

<!-- pace -->
<script src="${base}/plugins/pace/pace.min.js"></script>
<#-- jquery cookie -->
<script src="${base}/plugins/jquery/jquery.cookie.js"></script>

<#-- common -->
<script src="${base}/js/xxl.alert.1.js"></script>
<script src="${base}/js/common.1.js"></script>
<script src="${base}/adminlte/angular/angular.js"></script>
<script src="${base}/adminlte/angular/angular-route.js"></script>
<script src="${base}/js/app.js"></script>

</#macro>

<#macro commonHeader>
<header class="main-header">
    <a href="${base}/" class="logo">
        <span class="logo-mini"><b></b></span>
        <span class="logo-lg"><b></b>管理平台</span>
    </a>
    <nav class="navbar navbar-static-top" role="navigation">
        <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button"><span class="sr-only">切换导航</span></a>
        <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
                <li class="dropdown user user-menu">
                    <a href=";" id="logoutBtn" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                        <span class="hidden-xs">注销</span>
                    </a>
                </li>
            </ul>
        </div>
    </nav>
</header>
</#macro>

<#macro commonFooter >
<footer class="main-footer">
    Powered by <b>API</b> 1.0
    <div class="pull-right hidden-xs">
    </div>
</footer>
</#macro>