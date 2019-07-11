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
        <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
        <script>
        $(document).ready(function(){
            window.vue  = new Vue({
                el: '#torrent',
                data : {
                    result : '',
                    upFiles : '',
                    fileRes : '',
                },

                created : function(){
                },

                methods : {
                    handleFilesUpload : function() {
                        console.log("실행 func handleFilesUpload()");
                         this.upFiles = this.$refs.upFiles.files;
                            if(this.upFiles.length > 0){
                                this.submitFiles();
                            }
                    },

                    submitFiles : function() {
                        var formData = new FormData();
                        /* var vue = this; 아래 .then 함수에서 function 안에서는 포인터가 함수자신이기때문에 이렇게 설정 */
                        var vue = this;
                        for( var i = 0; i < this.upFiles.length; i++ ){
                              let file = this.upFiles[i];
                              formData.append('files', file);
                        }
                        console.log('--- 파일전송 시작 ---');
                        /* this.mask = true; */
                        axios.post('/file/img/upload', formData, {
                            headers: {
                            'Content-Type': 'multipart/form-data'
                            }
                        })
                        /* .then( function(response) {
                            console.log('--- 파일전송 종료 --- : '+response.data.length);
                            vue.fileRes = response.data;
                            vue.imgList = vue.imgList.concat(vue.fileRes);
                        }) */
                        .then( response => {
                            console.log('--- 파일전송 종료 --- : '+response.data.length);
                            this.fileRes = response.data;
                            /* this.imgList = this.imgList.concat(this.fileRes); */
                            /* this.imgList = (this.imgList.length == 0) ? this.fileRes : this.imgList.concat(this.fileRes); */
                        })
                        .catch( function(error) {
                            console.log(error);
                        })
                        //.finally( () => /*this.mask = false */);

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

			<input type="file" name="upFiles" id="upFiles" ref="upFiles" multiple v-on:change="handleFilesUpload()"/>
		</div>
	</body>
</html>
