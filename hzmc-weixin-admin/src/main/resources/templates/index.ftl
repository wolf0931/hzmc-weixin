<!DOCTYPE html>
<html>
<#import "common/common.macro.ftl" as netCommon>
<@netCommon.commonStyle />
    <link rel="stylesheet" href="${request.contextPath}/adminlte/plugins/iCheck/square/blue.css">
</head>
<body ng-app="myApp">
<div ng-view="">
</div>
<@netCommon.commonScript/>
</body>
</html>