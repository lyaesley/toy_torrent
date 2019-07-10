<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!DOCTYPE html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="description" content="">
		<meta name="author" content="">
		<title></title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://unpkg.com/vue"></script>

        <script src="http://localhost:35729/livereload.js"></script>
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
                    handleFilesUpload : function() {
                        console.log("실행 func handleFilesUpload()");
                    }
                }

            });
        });
        </script>

        <style>
        </style>

	</head>
	<body id="page-top">
		<div id="torrent">
			heress  ${test}
			aaaaa
			<input type="file" name="files" id="files" ref="files" multiple v-on:change="handleFilesUpload()"/>
		</div>
	</body>
</html>
