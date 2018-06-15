<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<script src="${base}/js/jquery.js" type="text/javascript"></script>  
</head>  
<body>  
  <form id="form" method="post" action="insert" enctype="multipart/form-data">   
  
  	id<input type="text" name="id" /><br/>
  	name<input type="text" name="name"/><br/>
  	email<input type="text" name="email"/><br/>
  	phone<input type="text" name="phone"/><br/>
    file<input type="file" name="file" id="file"/><br/>   
    <input type="submit" value="确定" /><br/>   
  </form>   
  <div id="tip"></div>  
  
 <script type="text/javascript">  
 
function insert(){
	upload(function(){
		var data = $("#form").serialize();
		$.ajax({    
	        url:"insert",    
	        type:"POST",  
	        data:data,  
	        dataType:"text",  
	        success: function(data){  
	            console.log("insert success!")
	        }  
	    });
	});
}

function upload(fun) {  
    formData = new FormData();  
    formData.append("file",file.files[0]);  
    $.ajax({    
        contentType:"multipart/form-data",  
        url:"upload",    
        type:"POST",  
        data:formData,  
        dataType:"text",  
        processData: false,  // 告诉jQuery不要去处理发送的数据  
        contentType: false,   // 告诉jQuery不要去设置Content-Type请求头  
        success: function(data){  
        	console.log("upload success!");
        	fun();
        }  
    });  
}  
</script> 
</body> 
</html>  