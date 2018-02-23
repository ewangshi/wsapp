$(document).ready(function() {
	$("#btn").on('click',function(){
		$("#upload").trigger('click');
	});
	
	
	$("#upload").on("change", function() {
		var file = document.getElementById("upload").files[0];
		var url = "http://127.0.0.1:8080/upload/upload.json";
		ws_image_upload.upload(file, url, {width:1240,height:1754,quality:0.7, filename:'file',onSuccess:function(e){alert(e);},onSendError:function(e){alert(e);},notImage:function(){alert("notImage");}});
	});
});