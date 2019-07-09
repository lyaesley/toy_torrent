<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script>
$(document).ready(function(){
	window.vue  = new Vue({
		el: '#torrent',
		data : {
			result : '',
		},

		created : function(){
		},

		methods : {
		}

	});
});
</script>

<style>
</style>


<!DOCTYPE html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="description" content="">
		<meta name="author" content="">
		<title></title>
	</head>
	<body id="page-top">
		<div id="torrent">
			here
		</div>
	</body>
</html>
