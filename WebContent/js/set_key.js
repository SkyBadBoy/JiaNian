$(document).ready(function(){
	
	$("#Js_rule_add").click(function(){
		
	});

	$("select").ready(function(){
		var num = $("select").val();
		$("div[id^=\"content_\"]").css("display","none");
		$("div#content_"+num).css("display","block");
		if(num=="mutil"){
			$("#check").css("display","");
			$("#save").css("display","");
		}else{
			$("#check").css("display","none");
			$("#save").css("display","none");
		}
	});
	
	$("select").change(function(){
		var num = $("select").val();
		$("div[id^=\"content_\"]").css("display","none");
		$("div#content_"+num).css("display","block");
		if(num=="mutil"){
			$("#check").css("display","");
			$("#save").css("display","");
		}else{
			$("#check").css("display","none");
			$("#save").css("display","none");
		}
	});
	
	var curr_mutil="";
	
	$("#save").click(function(){
		
		var title = $("input[name=news_title]").val();
		var desp = $("input[name=news_desp]").val();
		var pic_url = $("input[name=news_pic_url]").val();
		var uurl = $("input[name=news_url]").val();
		
		curr_mutil+="@title#"+title+"#desp|"+desp+"#pic|"+pic_url+"#url|"+uurl;
		
		
		alert("保存完成");
		
	});
	
	$("#check").click(function(){
		
		var tmp = curr_mutil;
		var sum = new Array();
		
		sum = tmp.split("@");
		
		for(var i=1;i<sum.length;i++){
			tmp = tmp.replace("@title#","标题:");
			tmp = tmp.replace("#desp|","描述:");
			tmp = tmp.replace("#pic|","图片:");
			tmp = tmp.replace("#url|","Url:");
		}
		alert(tmp);
		
	});
	
	$("#save_db").click(function(){
		var num = $("select").val();
		var content="";
		var key = $("input[name=keyWord]").val();
		var User_id = $("input[name=User_id]").val();
		if(num=="mutil"){
			content = curr_mutil;
			if(content=="") {alert("还未保存图文");return;}
		}else if(num=="music"){
			var music_title = $("input[name=music_title]").val();
			var music_url = $("input[name=music_url]").val();
			var music_desp = $("input[name=music_desp]").val();
			content+="@music|#desp|"+music_desp+"#title|"+music_title+"#murl|"+music_url;
		}else{
			content = $("#normal_content").val();
		}
		$.post("key_submit_key.action",
				  {
					User_id:User_id,
				    KeyWord:key,
				    Response:content
				  },
				  function(data,status){
				    alert("Data: " + data + "\nStatus: " + status);
				  });
	});
	
});
	

