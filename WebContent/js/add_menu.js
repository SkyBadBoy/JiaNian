view_tmp = "{\"type\":\"view\",\"name\":\"{name}\",\"url\":\"{url}\"}";
click_tmp = "{\"type\":\"click\",\"name\":\"{name}\",\"key\":\"{key}\"}";
//sub_button_tmp=" {\"name\": \"{sub_name}\", \"sub_button\": [{items}]}";
sub_button_tmp=" {\"name\": \"{sub_name}\", \"sub_button\": [";

btn_total=0;
sub_btn_total=0;
sub_btn=0;
sql=0;
i=1;
zi=1;
result="";

json_head=" {\"button\":[";

json_end="]}";

sub_button_end = json_end;

end_state=0;//编辑状态 若编辑完成则替换为1

sub_menu_edit=1//子菜单编辑状态 若编辑完成则置为1 未编辑完成置为0

menu_head = "<div class=\"menu_head\"><input type=\"text\" name=\"sub_name\" id=\"sub_name\" value=\"\" /></div>";
$(document).ready(function(){
	$("input[name=sub_button_event]").css("display","none");
	
	$("input[name=button_event]").css("display","none");
	
	$("#add_conan").click(function(){
		$("div.menu_detail").append(menu_head);
	});
	//左边菜单详情栏目
	$(".menu_head").on("click",function(){
		$(".menu_detail").append(menu_head);
	});
	
	$("#button_type").change(function(){
		var type=$(this).val();
		if(type=="click"){
			$("#sub_menu_set").css("display","none");
			$("input[name=button_event]").css("display","");
			$("input[name=button_event]").attr("placeholder","请输入key");
			$("div[name=table]").css("display","");
		}else if(type=="view"){
			$("#sub_menu_set").css("display","none");
			$("input[name=button_event]").css("display","");
			$("input[name=button_event]").attr("placeholder","请输入跳转URL");
			$("div[name=table]").css("display","none");
		}else{
			$("input[name=button_event]").css("display","none");
			$("#sub_menu_set").css("display","");
			$("div[name=table]").css("display","none");
		}
		sub_menu_edit=1;
	});
	
	$("#sub_button_type").change(function(){
		var type=$(this).val();
		if(type=="click"){
			$("input[name=sub_button_event]").css("display","");
			$("div[name=table]").css("display","");
			$("input[name=sub_button_event]").attr("placeholder","请输入key");
		}else if(type=="view"){
			$("input[name=sub_button_event]").css("display","");
			$("input[name=sub_button_event]").attr("placeholder","请输入跳转URL");
			$("div[name=table]").css("display","none");
		}else{
			$("input[name=sub_button_event]").css("display","none");
			$("div[name=table]").css("display","none");
		}
	});
//	//结束当前编辑
//	$("#end_curr").click(function(){
//		end_state=1
//		result = result.substring(0,result.length-1);
//		result+=json_end;
//		console.log(result);
//	});
	//保存当前
	$("#save_curr").click(function(){
		var type = $("#button_type").val();
		var type1 = $("#sub_button_type").val();
		var Event = $("input[name=button_event]").val();
		var name = $("input[name=button_name]").val();
		if(result==""){
			result+=json_head;
		}
		if((type=="click"||type1=="click"))
		{
			addWeChatContent();
		}
		if(type=="click"){
			var tmp = click_tmp;
			tmp = tmp.replace("{name}",name);
			tmp = tmp.replace("{key}",Event);	
			result+=tmp+",";
			end_state=1
			result = result.substring(0,result.length-1);
			result+=json_end;
		}else if(type=="view"){
			var tmp = view_tmp;
			tmp = tmp.replace("{name}",name);
			tmp = tmp.replace("{url}",Event);	
			result+=tmp+","
			end_state=1
			result = result.substring(0,result.length-1);
			result+=json_end;
		}else if(type=="sub_menu"){
			result = result.substring(0,result.length-1);
			result+=sub_button_end+"]}";
			
		}
		$.post("WeChatCustomList",{
			json_code:result
		},function(data,status){
			alert(data+"\n"+status);
		});
		console.log(result);
	}
	);
	
	//下一个按钮
	$("#btnnext").click(function(){
		sub_btn=0;
		i++;
		if (i==3) {
			$("a[name=next]").css("display","none");
		}
		var type = $("#button_type").val();
		var type1=$("#sub_button_type").val();
		
		var Event = $("input[name=button_event]").val();
		var name = $("input[name=button_name]").val();
		console.info(type);
		if(type=="click"||type1=="click")
		{
			addWeChatContent();
			
		}
		if(result==""){
			result+=json_head;
		}
		
		if(type=="click"){
			var tmp = click_tmp;
			tmp = tmp.replace("{name}",name);
			tmp = tmp.replace("{key}",Event);	
			result+=tmp+",";
		}else if(type=="view"){
			var tmp = view_tmp;
			tmp = tmp.replace("{name}",name);
			tmp = tmp.replace("{url}",Event);	
			result+=tmp+","
		
		}else if(type=="sub_menu"){
			result = result.substring(0,result.length-1);
			result+=sub_button_end+",";
		}

		console.log(result);
		z++;
		$("#subbut").attr("value",'');
		$("#zisub").attr("value",'');
		$("#caidan").attr("value",'');
		$("#key").val('');
		$("#text1").attr("value",'');
		$("#Title").attr("value",'');
		$("#Description").attr("value",'');
		$("#PicUrl").attr("value",'');
		$("#Url").attr("value",'');

		$("#tishi").html("第"+z+"个按钮");
	})
	
	//添加子菜单按钮
	$("#add_sub_menu_btn").click(function(){
	
		zi++;
		if(zi==5)
		{
			$("a[name=zi]").css("display","none");
		}
		if(sub_btn!=1)
		{
			tiejia();
		}
		addWeChatContent();
		var type = $("#sub_button_type").val();
		var sub_menu_name=$("input[name=sub_menu_name]").val();
		var sub_menu_key=$("input[name=sub_button_event]").val();
		
		if(type=="click"){
			var tmp = click_tmp;
			tmp = tmp.replace("{name}",sub_menu_name);
			tmp = tmp.replace("{key}",sub_menu_key);	
			result+=tmp+",";
		}else if(type=="view"){
			var tmp = view_tmp;
			tmp = tmp.replace("{name}",sub_menu_name);
			tmp = tmp.replace("{url}",sub_menu_key);	
			result+=tmp+",";
		}
		console.log(result);
		$("#subbut").attr("value",'');
		$("#zisub").attr("value",'');
		$("#key").val('');
		$("#text1").attr("value",'');
		$("#Title").attr("value",'');
		$("#Description").attr("value",'');
		$("#PicUrl").attr("value",'');
		$("#Url").attr("value",'');
	});

	//添加子菜单
	$("#add_sub_menu").click(function(){
		var sub_name=$("input[name=button_name]").val();
		if(result!=""){
			result+=sub_button_tmp;	
		}else{
			result+=json_head+sub_button_tmp;
		}
		
		var tmp = result;
		result = tmp.replace("{sub_name}",sub_name);
		console.log(result);
	});
	
	function tiejia() {
		console.info("sub_btn");
		console.info(sub_btn);
		var sub_name=$("input[name=button_name]").val();
		if(result!=""){
			result+=sub_button_tmp;	
		}else{
			result+=json_head+sub_button_tmp;
		}
		
		var tmp = result;
		result = tmp.replace("{sub_name}",sub_name);
		sub_btn=1;
		console.info("sub_btn");
		console.info(sub_btn);
	}
});


