define("tpl/cardticket/select_sub_merchant_table.html.js",[],function(){
return'{if loading}<i class="icon_loading_small white"></i>\n{else}\n<div class="sub_title_bar">\n    <span class="frm_input_box search append l">\n        <a href="javascript:void(0);" class="js_search_btn frm_input_append">\n            <i class="icon16_common search_gray">\n                搜索\n            </i>\n            &nbsp;\n        </a>\n        <input type="text" placeholder="请输入商户名" value="{param.keyword}" class="frm_input js_search_input">\n    </span>\n    <div class="tr">\n        <a class="btn btn_primary r" href="{wx_url \'/merchant/cardhelpmakesend?action=addpage\'}" target="_blank"><i class="icon14_common add_white"></i>添加子商户</a>\n    </div>\n</div>\n<div class="in_bd">\n	{if !data.length}\n	<div class="account_list empty js_empty">\n		{if param.keyword}\n		你输入的名称未搜索到，请确认否输入正确或未添加该子商户。		{else}\n		您还没有添加子商户，请点击右上角按钮添加子商户		{/if}\n		<!-- 抱歉，未找到符合公众号 -->\n	</div>\n	{else}\n	<ul class="account_list js_merchant_item_p">\n		{each data as sub i}\n		<li class="list_item js_merchant_item{if check_remain_quota && (sub.remain_quota==0||sub.can_not_use_sns_card)} js_merchant_disabled disabled{/if}" data-id="{sub.Id}">\n	        <div class="inner_list_item">\n	            <img class="pic" src="{http2https sub.Logo}" width="100px">\n				<div class="item_txt">\n					<p class="nick_name">{sub.BrandName}</p>\n                    {if check_remain_quota}{if max_card===0}<p>账号违规，暂停制券</p>{else}{if sub.remain_quota==0}<p>已超出制券量</p>{else if sub.can_not_use_sns_card}<p>该商户类目不可创建朋友的券</p>{/if}{/if}{/if}\n				</div>\n			</div>\n			<a href="javascript:;" class="account_selected"></a>\n			<div class="list_mask"></div>\n	    </li>\n	    {/each}\n	</ul>\n	<div class="js_pager"></div>\n	{/if}\n	<!-- <div class="loading_box empty dn" id="js_loading">\n		<img src="<%@GetResFullName($images_comm_path$icon/common/icon32_loading_light.gif)%>">\n		<p>加载中，请稍候</p>\n	</div> -->\n</div>\n{/if}\n';
});define("tpl/cardticket/card_quantity.html.js",[],function(){
return'<div class="pop_store">\n	{if !data.is_sns_card}\n	{if data.quantity==0}\n	<p class="frm_msg fail" style="display:block;">库存为0，请先增加库存</p>\n	{/if}\n	<!-- 普通卡券增减库存 -->\n	<div class="pop_card_normal">\n		<!--增减库存-->\n		{if setquantity}\n		<!-- 这一部分貌似要废弃掉 -->\n		<div class="frm_control_group">\n			<div class="frm_controls">\n				<label class="frm_radio_label selected">\n					<i class="icon_radio"></i>\n					<span class="lbl_content">增加</span>\n					<input type="radio" name="isadd" checked value="1" class="frm_radio js_quantity_type">\n				</label>\n				<label class="frm_radio_label">\n					<i class="icon_radio"></i>\n					<span class="lbl_content">减少</span>\n					<input type="radio" name="isadd" value="0" class="frm_radio js_quantity_type">\n				</label>\n			</div>\n		</div>\n		{/if}\n		<div class="frm_control_group">                        \n			<div class="frm_controls">\n				<div class="frm_controls_hint group">\n					<span class="frm_input_box"><input type="text" class="frm_input js_value"></span>\n					<span class="frm_hint">份</span>\n				</div>\n				<p class="frm_tips fail">库存不能少于1</p>\n			</div>\n		</div>\n		<!--增减库存 end-->\n	</div>\n	{else}\n	<!-- 朋友券增加库存 -->\n	<!-- message fail-->\n	<div class="js_state_5 js_state_quantity pop_card_quantity page_msg small default" style="display:none">\n        <div class="inner group">\n            <span class="msg_icon_wrp">\n                <i class="icon_msg info"></i>\n            </span>\n            <div class="msg_content">\n                <h4> 当前未开通券点账户，暂时无法添加库存 </h4>\n            </div>\n        </div>\n        <div class="popover_bar tc">\n			<a href="javascript:;" class="btn btn_primary js_go_activate">去开通</a>\n        </div>\n    </div>\n	<i class="loading js_satate_0 js_state_quantity" style="display:none"></i>\n	<div class="js_state_1 js_state_quantity pop_card_quantity" style="display:none">\n		{if data.quantity==0}\n		<p class="frm_msg fail" style="display:block;">库存为0，请先增加库存</p>\n		{/if}\n		<div class="pop_hd">\n			<div class="frm_control_group frm_card_extend">                        \n				<label for="" class="frm_label">\n					<span class="title">库存单价</span>\n				</label>\n				<div class="frm_preview"><span class="js_price">0.2</span>券点/张\n				</div>\n			</div>\n			<div class="frm_control_group frm_card_extend">                        \n				<label for="" class="frm_label">\n					<span class="title">增加库存</span>\n				</label>\n				<div class="">\n					<span class="frm_input_box"><input type="text" class="frm_input js_value"></span>\n					<span class="frm_hint">张</span>\n				</div>\n				<!-- <p class="frm_tips fail">库存不能少于1</p> -->\n			</div>\n		</div>\n		<div class="frm_control_group frm_card_extend js_total_price_container">                        \n			<label for="" class="frm_label">\n				所需券点			</label>\n			<div class="frm_preview">\n				<span class="js_total_price card_fee_quantity">0</span>券点			</div>\n		</div>\n		<p class="js_error frm_msg fail"></p>\n		<div class="popover_bar">\n			<a href="javascript:;" class="btn btn_primary js_confirm">确认添加</a>\n			<a href="javascript:;" class="btn btn_default js_cancel">取消</a>\n        </div>\n	</div>\n	<!-- 朋友券 确认预览 -->\n	<div class="js_state_2 js_state_quantity pop_card_quantity" style="display:none">\n		<div class="pop_hd">\n			<div class="frm_control_group frm_card_extend">                        \n				<label for="" class="frm_label">\n					卡券名称				</label>\n                <div class="frm_preview js_cardname">{data.title}\n				</div>\n			</div>\n			<div class="frm_control_group frm_card_extend">                        \n				<label for="" class="frm_label">\n					库存单价				</label>\n                <div class="frm_preview"><span class="js_price"></span>券点/张\n				</div>\n			</div>\n			<div class="frm_control_group frm_card_extend">                        \n				<label for="" class="frm_label">\n					增加库存\n				</label>\n                <div class="frm_preview"><span class="js_quantity"></span>张				</div>\n			</div>\n		</div>\n		<div class="frm_control_group frm_card_extend">                        \n			<label for="" class="frm_label">\n				支出券点\n			</label>\n			<div class="frm_preview">\n				免费券点<span class="js_freecoin"></span> ，付费券点<span class="js_paycoin"></span>\n			</div>\n		</div>\n		<div class="popover_bar">\n			<a href="javascript:;" class="btn btn_primary js_confirm">确定</a>\n			<a href="javascript:;" class="btn btn_default js_cancel">取消</a>\n        </div>\n	</div>\n	<!-- message success-->\n	<div class="js_state_3 js_state_quantity pop_card_quantity page_msg small msg_success default" style="display:none">\n        <div class="inner group">\n            <span class="msg_icon_wrp">\n                <i class="icon_msg success"></i>\n                <!-- loging gif也可以放到这里 -->\n            </span>\n            <div class="msg_content">\n                <h4> 已添加成功 </h4>\n            </div>\n        </div>\n    </div>\n	<!-- message success-->\n	<div class="js_state_9 js_state_quantity pop_card_quantity page_msg small msg_success default" style="display:none">\n        <div class="inner group">\n            <span class="msg_icon_wrp">\n                <i class="icon_msg success"></i>\n                <!-- loging gif也可以放到这里 -->\n            </span>\n            <div class="msg_content">\n                <h4> 已购买成功 </h4>\n                <p class="tl">请通知适用商户登录微信支付平台审核，通过库存生效。若不通过，将退回券点。</p>\n            </div>\n        </div>\n        <div class="plant_msg">\n        	总库存：        	<span class="frm_preview js_cardname"><span class="js_total_price mini_tips weak_text js_current_quantity">{data.quantity}</span>\n			</span>\n			<div class="mini_tips weak_text">(<span class=\'js_quantity\'></span>库存审核通过后生效)</div>\n        </div>\n        <!--\n        <div class="frm_control_group frm_card_extend">                        \n			<label for="" class="frm_label">\n				总库存：			</label>\n            <div class="frm_preview js_cardname"><span class="js_total_price card_fee_quantity mini_tips weak_text js_current_quantity">{data.quantity}</span><span class="mini_tips weak_text">(<span class=\'js_quantity\'></span>库存审核通过后生效)</span>\n			</div>\n			<p class="frm_msg" style="display:block">少于100时，将通过公众号通知核销员 <a href="">修改</a></p>\n		</div>-->\n        <div class="popover_bar tc">\n			<a href="javascript:;" class="btn btn_primary js_close_quantity">完成</a>\n        </div>\n    </div>\n	<!-- message fail-->\n	<div class="js_state_4 js_state_quantity pop_card_quantity page_msg small default" style="display:none">\n        <div class="inner group">\n            <span class="msg_icon_wrp">\n                <i class="icon_msg info"></i>\n            </span>\n            <div class="msg_content">\n                <h4> 库存添加中，可前往流水记录查看本次添加进度 </h4>\n            </div>\n        </div>\n        <div class="popover_bar tc">\n			<a href="javascript:;" class="btn btn_primary js_close_quantity">我知道了</a>\n        </div>\n    </div>\n    <!-- 子商户库存提示-->\n	<div class="js_state_8 js_state_quantity pop_card_quantity page_msg small default" style="display:none">\n        <div class="inner group">\n            <span class="msg_icon_wrp">\n                <i class="icon_msg info"></i>\n            </span>\n            <div class="msg_content js_quantity_exceed_msg">\n                <h4> 子商户每张券累计只可发放10000份 </h4>\n            </div>\n        </div>\n        <div class="popover_bar tc">\n			<a href="javascript:;" class="btn btn_primary js_close_quantity">我知道了</a>\n        </div>\n    </div>\n	<!-- message fail-->\n	<div class="js_state_7 js_state_quantity pop_card_quantity page_msg small default" style="display:none">\n        <div class="inner group">\n            <span class="msg_icon_wrp">\n                <i class="icon_msg warn"></i>\n            </span>\n            <div class="msg_content">\n                <h4> 库存添加失败，请稍后再试 </h4>\n                <p> 所扣币值将退回你的账户，请耐心等待 </p>\n            </div>\n        </div>\n        <div class="popover_bar tc">\n			<a href="javascript:;" class="btn btn_primary js_close_quantity">我知道了</a>\n        </div>\n    </div>\n    {/if}\n</div>\n';
});define("tpl/cardticket/choose_card_type.html.js",[],function(){
return'{if is_sns_card}<div class="proc_put_tick">\n	<div class="choose_card_friend">\n	    <div class="frm_control_group">\n	        <label class="frm_radio_label selected">\n	            <i class="icon_radio"></i>\n	            <span class="lbl_content">创建朋友共享的优惠券</span> <i class="icon_common new" style=""></i>\n	            <input type="radio" value="1" checked class="frm_radio js_is_friend">\n	        </label>\n	        <div class="frm_tips js_is_friend_tips js_is_friend_support_tips">用户领取一张优惠券后，他的好友无需领取即可在优惠券列表看到和使用该张优惠券。这将为你的优惠券带来更多的曝光和使用。</div>\n	        <div style="display:none;" class="frm_tips js_is_friend_tips js_is_friend_view_mode2_tips">所选子商户类目不支持制作朋友的券，<a target="_blank" href="/cgi-bin/readtemplate?t=cardticket/faq_tmpl&type=info&lang=zh_CN#0dot2">查看类目要求</a></div>\n	        <div style="display:none;" class="frm_tips js_is_friend_tips js_is_friend_view_mode1_tips">当前商户类目不支持制作朋友的券，<a target="_blank" href="/cgi-bin/readtemplate?t=cardticket/faq_tmpl&type=info&lang=zh_CN#0dot2">查看类目要求</a></div>\n	    </div>\n	</div>\n    <div class="choose_card_type js_is_friend_type js_is_friend_type_1">\n	    <div class="frm_control_group radio_row frm_tab">\n			<div class="frm_controls frm_vertical_lh">\n				<label class="frm_radio_label selected">\n					<i class="icon_radio"></i>\n					<span class="lbl_content">无使用门槛代金券</span>\n					<input type="radio" value="4" data-not_has_condition="1" class="frm_radio js_card_type" checked="checked">\n	                <p class="frm_tips">可抵扣现金，无使用门槛</p>\n				</label>\n				<label class="frm_radio_label selected">\n					<i class="icon_radio"></i>\n					<span class="lbl_content">满减代金券/指定品类代金券</span>\n					<input type="radio" value="4" class="frm_radio js_card_type">\n	                <p class="frm_tips">可抵扣现金，需消费指定金额或品类</p>\n				</label>\n				<label class="frm_radio_label">\n					<i class="icon_radio"></i>\n					<span class="lbl_content">兑换券</span>\n					<input type="radio" value="3" class="frm_radio js_card_type">\n					<p class="frm_tips">可兑换商品或服务</p>\n				</label>\n			</div>\n		</div>\n		<div class="frm_tab_content">\n			<div class="tab_items">\n				<div class="frm_tab_item js_tabed_item_4">\n					<div class="tab_inner">\n						<ul class="prom_list">\n							<li>消费者体验好</li>\n							<li>使用转化率高</li>\n							<li>引流效果较好</li>\n							{if view_mode==1}\n							<li>支持微信买单</li>\n							{/if}\n						</ul>\n						<p>查看案例：<a href="http://mp.weixin.qq.com/s?__biz=MjM5NDQ5Njk3OA==&mid=407898139&idx=1&sn=f3ea0f070d756d8d2f61d496aeb35286#rd" target="_blank">广州某百货公司无门槛代金券活动 ></a></p>\n					</div>\n				</div>\n				<div class="frm_tab_item js_tabed_item_4">\n					<div class="tab_inner">\n						<ul class="prom_list">\n							<li>成本可控</li>\n							<li>提升客单价</li>\n							<li>多种优惠使用条件组合</li>\n							{if view_mode==1}\n							<li>支持微信买单</li>\n							{/if}\n						</ul>\n					</div>\n				</div>\n				<div class="frm_tab_item js_tabed_item_3">\n					<div class="tab_inner">\n						<ul class="prom_list">\n							<li>成本可控</li>\n							<li>推广新品效果佳</li>\n							<li>引流到店效果好</li>\n							<li>方便组合促销</li>\n						</ul>\n					</div>\n				</div>\n			</div>\n		</div>\n	</div>\n	{if show_all_card}\n	<div class="choose_card_normal">\n	    <div class="frm_control_group frm_card_normal">\n	        <label class="frm_radio_label">\n	            <i class="icon_radio"></i>\n	            <span class="lbl_content">我要创建普通优惠券</span>\n	            <input type="radio" value="2" class="frm_radio js_is_friend">\n	        </label>\n	        <div class="frm_tips">传统优惠券的电子版，可在微信中收纳、传播和使用。只可领取到我的卡券自己使用</div>\n	        <div class="frm_control_group radio_row js_is_friend_type js_is_friend_type_2" style="display:none">\n				<div class="frm_controls frm_vertical_lh">\n					{if flag==0}\n					<label class="frm_radio_label">\n						<i class="icon_radio"></i>\n						<span class="lbl_content">折扣券</span>\n						<input type="radio" value="2" class="frm_radio js_card_type">\n		                <p class="frm_tips">可为用户提供消费折扣{if is_paycard()}，支持优惠抵扣快速买单{/if}</p>\n					</label>\n					<label class="frm_radio_label">\n						<i class="icon_radio"></i>\n						<span class="lbl_content">代金券</span>\n						<input type="radio" value="4" class="frm_radio js_card_type">\n		                <p class="frm_tips">可为用户提供抵扣现金服务。可设置成为“满*元，减*元”{if is_paycard()}，支持优惠抵扣快速买单{/if}</p>\n					</label>\n					<label class="frm_radio_label">\n						<i class="icon_radio"></i>\n						<span class="lbl_content">兑换券</span>\n						<input type="radio" value="3" class="frm_radio js_card_type">\n						<p class="frm_tips">可为用户提供消费送赠品服务</p>\n					</label>\n					{/if}\n					<label class="frm_radio_label selected">\n						<i class="icon_radio"></i>\n						<span class="lbl_content">团购券</span>\n						<input type="radio" value="1" class="frm_radio js_card_type">\n						<p class="frm_tips">可为用户提供团购套餐服务</p>\n					</label>\n					<label class="frm_radio_label">\n						<i class="icon_radio"></i>\n						<span class="lbl_content">优惠券</span>\n						<input type="radio" value="0" class="frm_radio js_card_type">\n						<p class="frm_tips">{if flag==0}即“通用券”，建议当以上四种无法满足需求时采用{else}即“通用券”，建议当团购券无法满足需求时适用{/if}</p>\n					</label>\n				</div>\n			</div>\n	    </div>\n    </div>\n{/if}    \n</div>\n{else}<div class="proc_put_tick js_is_friend_type_2">\n<div class="choose_card_normal">\n	<div class="frm_control_group radio_row frm_card_normal">\n		<label for="" class="frm_label">选择你要创建的卡券类型</label>\n		<div class="frm_controls frm_vertical_lh">\n		{if flag==0}\n			<label class="frm_radio_label selected">\n				<i class="icon_radio"></i>\n				<span class="lbl_content">折扣券</span>\n				<input type="radio" value="2" class="frm_radio js_card_type">\n                <p class="frm_tips">可为用户提供消费折扣{if is_paycard()}，支持优惠抵扣快速买单{/if}</p>\n			</label>\n			<label class="frm_radio_label">\n				<i class="icon_radio"></i>\n				<span class="lbl_content">代金券</span>\n				<input type="radio" value="4" class="frm_radio js_card_type">\n                <p class="frm_tips">可为用户提供抵扣现金服务。可设置成为“满*元，减*元”{if is_paycard()}，支持优惠抵扣快速买单{/if}</p>\n			</label>\n			<label class="frm_radio_label">\n				<i class="icon_radio"></i>\n				<span class="lbl_content">兑换券</span>\n				<input type="radio" value="3" class="frm_radio js_card_type">\n				<p class="frm_tips">可为用户提供消费送赠品服务</p>\n			</label>\n		{/if}\n			<label class="frm_radio_label selected">\n				<i class="icon_radio"></i>\n				<span class="lbl_content">团购券</span>\n				<input type="radio" value="1" class="frm_radio js_card_type">\n				<p class="frm_tips">可为用户提供团购套餐服务</p>\n			</label>\n		\n			<label class="frm_radio_label">\n				<i class="icon_radio"></i>\n				<span class="lbl_content">优惠券</span>\n				<input type="radio" value="0" class="frm_radio js_card_type">\n				<p class="frm_tips">{if flag==0}即“通用券”，建议当以上四种无法满足需求时采用{else}即“通用券”，建议当团购券无法满足需求时适用{/if}</p>\n			</label>\n		</div>\n	</div>\n</div>\n</div>\n{/if}';
});define("cardticket/select_sub_merchant_table.js",["tpl/cardticket/select_sub_merchant_table.html.js","common/wx/popup.js","common/wx/Cgi.js","common/wx/pagebar.js","common/wx/Tips.js","biz_web/ui/checkbox.js","page/cardticket/dialog_choose_sub_store.css","cardticket/common_template_helper.js"],function(t){
"use strict";
function e(t){
var e,a=t.opt;
e=t.$container,e.html(c({
loading:!0,
param:a.param
})),a.resetPosition&&a.resetPosition();
}
function a(t,a){
var o=a.opt,r=$.extend(!0,{
action:"list",
offset:o.pageInfo.begin,
limit:o.pageInfo.count
},o.param);
f=!0,e(a),l.get({
url:o.url||"/merchant/cardhelpmakesend",
data:r,
complete:function(){
f=!1;
}
},function(t){
if(0==t.base_resp.ret||-1==t.base_resp.ret){
var e=wx.parseJSON(t,"bind_list"),r=wx.parseJSON(t,"sub_merchant_remain_quota");
if(o.data=e.List,o.remain_data=r.list,o.is_sns_card)for(var i=0;i<e.List.length;i++){
var s=e.List[i];
s.can_not_use_sns_card=!p.can_category_use_sns_card(s.PrimaryCategoryId,s.SecondaryCategoryId);
}
o.pageInfo.total_count=t.total_count||0,n(o.pageInfo,a);
}else l.show(t);
});
}
function n(t,e){
for(var a,n=e.opt,s=0;s<n.data.length;s++)$.extend(n.data[s],n.remain_data[s]);
return a=e.$container,a.html(c(n)),n.resetPosition&&n.resetPosition(),n.data.length?(e.pagebar=null,
i(n.pageInfo,e),r(e,n.data,a),o(e,a),void(n.getDataComplete&&n.getDataComplete(n.data))):(r(e,n.data,a),
void o(e,a));
}
function o(t,e){
function n(e){
o.param.keyword=e,a(o.pageInfo,t);
}
var o=t.opt,r=$(".js_search_input",e).on("keyup",function(t){
var e=$.trim($(this).val());
wx.isHotkey(t,"enter")&&n(e);
});
$(".js_search_btn",e).click(function(){
var t=$.trim(r.val());
n(t);
});
}
function r(t){
var e=t.opt;
$(".js_merchant_item").click(function(){
var t=$(this).hasClass("js_merchant_disabled");
t||($(".js_merchant_item").removeClass("selected"),$(this).addClass("selected"));
}),e.resetPosition&&e.resetPosition();
}
function i(t,e){
var n=t.total_count,o=e.$container;
if(t.count&&n>t.count){
var r=t.begin/t.count;
e.pagebar=new u({
container:$(".js_pager",o),
first:!1,
last:!1,
midRange:5,
initShowPage:r+1,
perPage:t.count,
totalItemsNum:n,
callback:function(n){
if(f)return!1;
var o=n.currentPage;
return o!=r+1&&(t.begin=(o-1)*t.count,a(t,e)),!0;
}
});
}
}
function s(t,e){
for(var a=0;a<t.length;a++)if(t[a].Id==e)return t[a];
return null;
}
{
var c=t("tpl/cardticket/select_sub_merchant_table.html.js"),l=(t("common/wx/popup.js"),
t("common/wx/Cgi.js")),u=t("common/wx/pagebar.js"),m=t("common/wx/Tips.js");
t("biz_web/ui/checkbox.js");
}
t("page/cardticket/dialog_choose_sub_store.css");
var p=t("cardticket/common_template_helper.js");
c=template.compile(c);
var _={
pageInfo:{
begin:0,
count:12,
total_count:0
},
param:{
status_list:1,
keyword:""
},
url:null,
data:null,
is_sns_card:!1,
selectComplete:$.noop,
onHide:$.noop
},d=function(t){
this.opt=$.extend(!0,{},_,t),this.init();
},f=!1;
return d.prototype={
init:function(){
var t=this.opt,e=this;
e.$container=$(t.container),t.data?n(t.pageInfo,e):a(t.pageInfo,e);
},
get:function(){
return this.$container;
},
selectedValue:function(){
var t=this.opt;
if(!t.data||!t.data.length)return!1;
var e=this.get(),a=e.find(".js_merchant_item.selected");
if(!a.length)return m.err("请选择子商户"),!1;
var n=a.attr("data-id"),o=s(t.data,n);
return o;
}
},d;
});define("cardticket/add/msg_operate_type_html.js",["tpl/media/cardmsg.html.js"],function(a){
"use strict";
var s={
1:'{if msg_operation.appmsg_title}<div class="appmsg single">                <div class="appmsg_content">                    <div class="appmsg_info">                        <em class="appmsg_date">{msg_operation.appmsg_update_time}</em>                    </div>                    <div class="appmsg_item">                        <h4 class="appmsg_title">                            <a href="{msg_operation.url}" target="_blank">{msg_operation.appmsg_title}</a>                        </h4>                        <div class="appmsg_thumb_wrp" style="background-image:url(\'{msg_operation.appmsg_cover}\')"></div>                        <p class="appmsg_desc">{msg_operation.appmsg_digest}</p>                        {if msg_operation.appmsg_type == 10}<a href="" class="edit_mask preview_mask js_preview" data-msgid="{msg_operation.appmsg_appmsgid}" data-idx="{msg_operation.appmsg_itemidx-1}">                            <div class="edit_mask_content">                                <p class="">                                    预览文章                                </p>                            </div>                            <span class="vm_box"></span>                        </a>{/if}                    </div>                </div>             </div>             {else}            <a href="{msg_operation.url}" target="_blank">{msg_operation.text}</a>             {/if}',
2:'<a target="_blank" href="{msg_operation.url}">{msg_operation.url}</a>',
5:a("tpl/media/cardmsg.html.js"),
4:'<a target="_blank" href="{msg_operation.url}">{msg_operation.url}</a>',
0:""
};
return s;
});define("common/wx/upload.js",["widget/upload.css","biz_web/lib/webuploader.js","common/wx/dialog.js","common/wx/Tips.js","tpl/uploader.html.js"],function(e){
"use strict";
function i(e){
f.src="http://isdspeed.qq.com/cgi-bin/r.cgi?flag1=7839&flag2=4&flag3=5&1="+e;
}
e("widget/upload.css");
var n=e("biz_web/lib/webuploader.js"),t=e("common/wx/dialog.js"),a=e("common/wx/Tips.js"),o=e("tpl/uploader.html.js"),r=wx.T,l=wx.path.webuploader,s=~location.hostname.search(/^mp/)?"https://mp.weixin.qq.com":"",c={
2:{
accept:{
extensions:"bmp,png,jpeg,jpg,gif",
mimeTypes:"image/*"
},
fileSingleSizeLimit:5242880
},
3:{
accept:{
extensions:"mp3,wma,wav,amr",
mimeTypes:"audio/*"
},
fileSingleSizeLimit:5242880
},
4:{
accept:{
extensions:"rm,rmvb,wmv,avi,mpg,mpeg,mp4",
mimeTypes:"video/*"
},
fileSingleSizeLimit:20971520
},
5:{
accept:{
extensions:"pdf",
mimeTypes:"application/pdf"
},
fileSingleSizeLimit:10485760
},
6:{
accept:{
extensions:"bmp,png,jpeg,jpg,gif,pdf",
mimeTypes:"image/*,application/pdf"
},
fileSingleSizeLimit:5242880
},
7:{
accept:{
extensions:"bmp,jpeg,jpg,gif",
mimeTypes:"image/*"
},
fileSingleSizeLimit:5242880
},
8:{
accept:{
extensions:"bmp,png,jpeg,jpg",
mimeTypes:"image/*"
},
fileSingleSizeLimit:5242880
},
9:{
accept:{
extensions:"xls",
mimeTypes:"application/vnd.ms-excel"
},
fileSingleSizeLimit:204800
},
10:{
accept:{
extensions:"xls",
mimeTypes:"application/vnd.ms-excel"
},
fileSingleSizeLimit:5242880
},
11:{
accept:{
extensions:"bmp,png,jpeg,jpg",
mimeTypes:"image/*"
},
fileSingleSizeLimit:5242880
},
12:{
accept:{
extensions:"mp3,wma,wav,amr",
mimeTypes:"audio/*"
},
fileSingleSizeLimit:31457280
}
};
c[15]=c[4];
var p=function(e){
t.show({
type:"warn",
msg:"警告|"+e,
mask:!0,
buttons:[{
text:"确定",
click:function(){
this.remove();
}
}]
});
},m=function(e){
var i=n.Uploader;
0==i.support("flash")?p("<p>未安装或未正确配置flash插件，请检查后重试。<br><a href='http://get.adobe.com/cn/flashplayer/' target='_blank'>到adobe去下载flash插件</a></p>"):0==i.support()?p("<p>您的浏览器不支持上传</p>"):e.refresh();
},u=function(e){
e&&wx.jslog({
src:"common/wx/upload.js"
},null,e);
},d={
swf:l,
auto:!0,
pick:{
multiple:!0
},
fileNumLimit:20,
threads:3,
sendAsBinary:!1,
runtimeOrder:"html5,flash",
compress:{
width:1280,
height:1e8,
quality:90,
afterCompressSizeLimit:2097152,
compressSize:0,
resizeSize:2097152,
maxResolution:6e6,
noCompressIfLarger:!0
},
imageSize:!0,
chunked:!1,
duplicate:!0
},f=new Image,g={},h=function(e){
if(!e.url)throw"missing url";
var t,l,s,p=$('<ul class="upload_file_box" style="display:none"></ul>'),f=$(e.container);
f.on("click",function(){
Math.random()<.1&&u(12),m(t);
}).parent().append(p),function(){
0==n.Uploader.support("html5")&&0==n.Uploader.support("flash")&&((new Image).src="/misc/jslog?level=error&id=36&content=[pageurl:"+encodeURIComponent(location.href)+",ua:"+encodeURIComponent(window.navigator.userAgent)+"]");
}(),l={
server:wx.url(e.url+"&ticket_id="+wx.data.user_name+"&ticket="+wx.data.ticket+"&svr_time="+wx.data.time),
pick:{
id:f,
multiple:e.multi
},
fileNumLimit:e.queueSizeLimit
},s=c[e.type]||c[2],e=$.extend(!0,{},d,l,s,e);
e.server;
0==n.Uploader.support("html5")&&e.compress&&(e.compress.quality=70);
try{
t=n.create(e);
}catch(h){
if(!t)return;
}
return p.on("click",".js_cancel",function(){
var e=$(this).data("id");
t.cancelFile(e),$(this).hide();
}),t.on("beforeFileQueued",function(i){
return Math.random()<.1&&u(13),e.canContinueUpload&&!e.canContinueUpload()?!1:!(e.onSelect&&e.onSelect(null,i.id,i)===!1);
}),t.on("fileQueued",function(e){
var i={
id:e.id,
fileName:e.name,
size:n.formatSize(e.size)
};
p.append(r(o,i)).show();
}),t.on("fileDequeued",function(){
Math.random()<.1&&u(14),e.onCancel&&e.onCancel();
}),t.on("uploadProgress",function(i,n){
var t="#uploadItem%s".sprintf(i.id),a=p.find(t).find(".progress_bar_thumb");
a.width("%s%".sprintf(100*n)),1==n&&p.find(t).find(".js_cancel").remove(),e.onProgress&&e.onProgress(null,i.id,i,{
percentage:n
});
}),t.on("uploadStart",function(e){
g[e.id]=+new Date;
}),t.on("uploadSuccess",function(n,t,o){
if(Math.random()<.1&&u(16),t&&t.base_resp){
var r=+t.base_resp.ret;
if(0==r)Math.random()<.1&&(u(17),g[n.id]&&i(+new Date-g[n.id]));else switch(n.setStatus("invalid"),
r){
case-18:
case 200018:
a.err("页面停留时间过久，请刷新页面后重试！");
break;

case-20:
case 200020:
a.err("无法解释该图片，请使用另一图片或截图另存");
break;

case-13:
case 200013:
a.err("上传文件过于频繁，请稍后再试");
break;

case-10:
case 200010:
a.err("上传文件过大");
break;

case-22:
case 200022:
a.err("上传音频文件不能超过60秒");
break;

case-39:
case 200039:
a.err("上传图片高度（像素）与宽度（像素）的乘积不能超过600万");
break;

default:
a.err("上传文件发送出错，请刷新页面后重试！");
}
}
e.onComplete&&e.onComplete(null,n.id,n,t,{
fileCount:o.numOfProgress+o.numOfQueue
});
}),t.on("uploadFinished",function(i){
this.reset(),p.fadeOut().html(""),g={},0==i.numOfInvalid&&i.numOfSuccess>0&&e.onAllComplete&&e.onAllComplete(null,{
errors:i.numOfCancel+i.numOfInvalid+i.numOfUploadFailed+i.numofDeleted+i.numofInterrupt,
filesUploaded:i.numOfSuccess
});
}),t.on("uploadError",function(){
Math.random()<.1&&u(15),e.onError&&e.onError();
}),t.on("error",function(i,t,o){
switch("object"==typeof t&&(o=t),i){
case"Q_EXCEED_NUM_LIMIT":
a.err("一次上传最多只能上传%s个文件".sprintf(t));
break;

case"F_EXCEED_SIZE":
a.err("文件大小不能超过%s".sprintf(n.formatSize(t,"0")));
break;

case"F_EXCEED_COMPRESS_SIZE":
a.err("图片压缩后过大，请缩小图片尺寸再试"),u(42);
break;

case"Q_TYPE_DENIED":
a.err(e.errTypeMsg||"文件必须为以下格式：%s".sprintf(e.accept.extensions).replace(/,/g,", "));
}
}),t;
},w=function(e){
return function(i){
return i.url=e,h(i);
};
},b=function(e){
return function(i){
return wx.url(e+"&ticket_id="+wx.data.user_name+"&ticket="+wx.data.ticket+"&id="+i);
};
};
return{
uploadFile:h,
uploadBizFile:w(s+"/cgi-bin/filetransfer?action=upload_material&f=json"),
uploadTmpFile:w(s+"/cgi-bin/filetransfer?action=preview&f=json"),
uploadCdnFile:w(s+"/cgi-bin/filetransfer?action=upload_cdn&f=json"),
uploadShopFile:w(s+"/merchant/goodsimage?action=uploadimage"),
uploadShopUnsaveFile:w(s+"/merchant/goodsimage?action=uploadimage&save=0"),
uploadVideoCdnFile:w(s+"/cgi-bin/filetransfer?action=upload_video_cdn&f=json"),
uploadRegisterFile:w(s+"/acct/realnamesubmit?type=2&action=file_set"),
uploadUpgradeFile:w(s+"/acct/servicetypeupgrade?type=2&action=file_set"),
uploadPoiFile:w(s+"/misc/setlocation?action=upload"),
mediaFile:w(s+"/cgi-bin/filetransfer?action=bizmedia"),
uploadCdnFileFromAd:function(e){
return w(s+"/cgi-bin/filetransfer?action=upload_cdn_check_size&f=json&width="+e.w+"&height="+e.h+"&limit_size="+e.size);
},
uploadImageLibFile:function(e){
return e.url=s+"/cgi-bin/filetransfer?action=upload_material&f=json","undefined"!=typeof e.scene&&(e.url+="&scene="+e.scene),
1==e.doublewrite&&(e.url+="&writetype=doublewrite&groupid="+(e.groupid||1)),h(e);
},
uploadCdnFileWithCheck:function(e){
var i={
height:0,
width:0,
size:0,
min_height:0,
min_width:0,
min_size:0
};
e=$.extend(!0,i,e);
var n=[];
for(var t in e)n.push(encodeURIComponent(t)+"="+encodeURIComponent(e[t]));
return w(s+"/cgi-bin/filetransfer?action=upload_cdn_check_range&f=json&"+n.join("&"),"tmpfile");
},
uploadTmpFileWithCheck:function(e){
var i={
height:0,
width:0,
size:0,
min_height:0,
min_width:0,
min_size:0
};
e=$.extend(!0,i,e);
var n=[];
for(var t in e)n.push(encodeURIComponent(t)+"="+encodeURIComponent(e[t]));
return w(s+"/cgi-bin/filetransfer?action=preview_check_range&f=json&"+n.join("&"));
},
tmpFileUrl:b(s+"/cgi-bin/filetransfer?action=preview"),
mediaFileUrl:b(s+"/cgi-bin/filetransfer?action=bizmedia"),
multimediaFileUrl:b(s+"/cgi-bin/filetransfer?action=multimedia")
};
});define("common/wx/tooltipsManager.js", [ "common/wx/tooltips.js" ], function(e, t, n) {
try {
var r = +(new Date);
"use strict";
var i = e("common/wx/tooltips.js"), s = {
tooltips: [],
init: function(e, t) {
var n = this;
$(e).each(function() {
t.container = this, n.add(new i(t));
});
},
add: function(e) {
this.tooltips.push(e);
},
hideAll: function() {
for (var e = 0; e < this.tooltips.length; e++) this.tooltips[e].hide();
},
removeItem: function(e) {
for (var t = 0; t < this.tooltips.length; t++) if (this.tooltips[t] === e) return this.tooltips.splice(t, 1), e.$dom.remove(), !0;
return !1;
},
removeIndex: function(e) {
if (e >= this.tooltips.length || e < 0) return;
var t = this.tooltips[e];
this.tooltips.splice(e, 1), t.$dom.remove();
},
current: function() {},
hide: function() {},
removeAll: function() {
for (var e = 0; e < this.tooltips.length; e++) this.tooltips[e].$dom.remove();
this.tooltips = [];
}
};
return s;
} catch (o) {
wx.jslog({
src: "common/wx/tooltipsManager.js"
}, o);
}
});define("tpl/biz_web/ui/dropdown.html.js", [], function(e, t, n) {
return '<a href="javascript:;" class="btn dropdown_switch jsDropdownBt"><label class="jsBtLabel" {if search}contenteditable="true"{/if}>{label}</label><i class="arrow"></i></a>\n<div class="dropdown_data_container jsDropdownList">\n    <ul class="dropdown_data_list">\n        {if renderHtml}\n        {renderHtml}\n        {else}\n            {each data as o index}\n            <li class="dropdown_data_item {if o.className}{o.className}{/if}">  \n                <a onclick="return false;" href="javascript:;" class="jsDropdownItem" data-value="{o.value}" data-index="{index}" data-name="{o.name}">{o.name}</a>\n            </li>\n            {/each}        \n        {/if}\n    </ul>\n</div>\n';
});define("tpl/homepage/appmsglist.html.js",[],function(){
return'<div class="select_list">\n	{if app_msg_list.length > 0}\n    {each app_msg_list as item}\n    {if multi}\n    <label class="select_list_item frm_checkbox_label {if !item.cover||!item.title}disabled {/if} {if item.checkbox}selected {/if}">\n        <span class="lbl_content list_item_date ">{item.update_time} </span>\n        <i class="icon_checkbox"> </i>\n        <span class="list_item_title lbl_content"> \n            {if !item.title}\n                （未命名文章，无法选择）            {else if !item.cover}\n                {if item.title.length>16}\n                    {item.title.substr(0,16)}...&nbsp;&nbsp;&nbsp;（文章未设置封面无法选择）                {else}\n                    {item.title}&nbsp;&nbsp;&nbsp;（文章未设置封面无法选择）                {/if}\n            {else}\n                {item.title}\n            {/if}            \n        </span>\n        <input type="checkbox" name="appmsgid" class="frm_checkbox js_appmsgid" value="{item.aid}" {if !item.cover||!item.title}disabled {/if}  {if item.checkbox}checked="checked" {/if}>\n    </label>\n    {else}\n    <label class="select_list_item frm_radio_label {if !item.cover||!item.title}disabled {/if} {if item.checkbox}selected {/if}">\n        <span class="lbl_content list_item_date ">{item.update_time} </span>\n        <i class="icon_radio"></i>\n        <span class="lbl_content list_item_title ">\n             {if !item.title}\n                （未命名文章，无法选择）            {else if !item.cover}\n                {if item.title.length>16}\n                    {item.title.substr(0,16)}...&nbsp;&nbsp;&nbsp;（文章未设置封面无法选择）                {else}\n                    {item.title}&nbsp;&nbsp;&nbsp;（文章未设置封面无法选择）                {/if}\n            {else}\n                {item.title}\n            {/if}\n        </span>\n        <input type="radio" name="appmsgid" value="{item.aid}" class="frm_radio js_appmsgid" {if !item.cover||!item.title}disabled {/if} {if item.checkbox}checked {/if}>\n	</label>\n    {/if}\n    {/each}\n    {else}\n    <p class="no_appmsg">暂无图文消息</p>\n    {/if}\n</div>\n\n';
});define("tpl/homepage/appmsgdialog.html.js",[],function(){
return'<script type="text/html" id="appmsgdialogtpl" >\n    <div class="title_tab">\n        <ul class="tab_navs title_tab">\n            <li class="tab_nav js_mass_tab selected first">\n                <a href="javascript:;">已发送</a>\n            </li>\n            <li class="tab_nav js_appmsg_tab">\n                <a href="javascript:;">素材库</a>\n            </li>\n        </ul>\n    </div>\n    <div class="select_list_container js_appmsg_container" style="display:none;">\n        <div class="select_list_hd global_mod float_layout">\n            <div class="global_info">\n                <span class="frm_input_box search append">\n                    <a href="javascript:;" class="frm_input_append js_a_search"><i class="icon16_common search_gray">搜索</i>&nbsp; </a>\n                    <input type="text" placeholder="搜索相关文章" value="" class="frm_input js_search">\n                </span>\n            </div>\n            <div class="global_extra">你还可以勾选<span class=\'js_remaincnt\'></span>篇文章                <!--<a href="javascript:;" class="btn btn_add btn_primary">-->\n                    <!--<i class="icon14_common add_white"></i>新建文章-->\n                <!--</a>-->\n            </div>\n        </div>\n        <div class="select_list_bd">\n            <!--BEGIN loading-->\n            <div class="loading_area js_loading" >\n                <span class="vm_box"></span>\n                <i class="icon_loading_small white"></i>\n            </div><!--END loading-->\n            <div class=\'js_listContainer\' style="display: none;">\n            </div>\n        </div>\n        <div class="select_list_ft">\n            <div class="pagination_wrp js_pager">\n\n            </div>\n        </div>\n    </div>\n    <div class="select_list_container js_mass_container">\n        <div class="select_list_hd global_mod float_layout">\n            <div class="global_extra">你还可以勾选<span class=\'js_remaincnt\'></span>篇文章            </div>\n        </div>\n        <div class="select_list_bd">\n            <!--BEGIN loading-->\n            <div class="loading_area js_mass_loading" >\n                <span class="vm_box"></span>\n                <i class="icon_loading_small white"></i>\n            </div><!--END loading-->\n            <div class=\'js_masslistContainer\' style="display: none;">\n            </div>\n        </div>\n        <div class="select_list_ft">\n            <div class="pagination_wrp js_masspager">\n\n            </div>\n        </div>\n    </div>\n\n</script>\n';
});define("cardticket/card_quantity.js",["common/wx/Cgi.js","common/wx/Tips.js","biz_web/ui/checkbox.js","cardticket/common_template_helper.js","tpl/cardticket/card_quantity.html.js","common/wx/tooltips.js","common/wx/tooltipsManager.js"],function(t){
"use strict";
var e=t("common/wx/Cgi.js"),a=t("common/wx/Tips.js"),i=(t("biz_web/ui/checkbox.js"),
t("cardticket/common_template_helper.js")),o=template.compile(t("tpl/cardticket/card_quantity.html.js")),s={
container:"",
quantityChange:$.noop,
max_sku_for_eachcard:1e4,
setquantity:!0
},r=t("common/wx/tooltips.js"),n=t("common/wx/tooltipsManager.js"),c=function(t){
t=$.extend(!0,{},s,t),this.opt=t;
var c=this;
t.data||(t.data={}),$(t.container).on("click",function(s){
function l(i,o){
$(".js_state_quantity",c.tooltip.$dom).hide();
var s=$(".js_state_"+i,c.tooltip.$dom).show(),r=s.attr("isinit");
if(0==i)e.get({
url:"/merchant/cardmoneymgr?action=get_card_price",
data:{
card_id:_
},
success:function(t){
if(0==t.base_resp.ret){
var a=$.parseJSON(t.result_json);
a.items[0].total_coin_balance=t.total_coin_balance,l(1,a.items[0]);
}else e.show(t);
}
});else if(1==i){
var d=o.price,p=o.total_coin_balance;
if(!r){
var f=$(".js_error",s),m=$(".js_total_price",s),h=$(".js_total_price_container",s),y=$(".js_value",s).keyup(function(){
var t=$(this),e=$.trim($(this).val());
if(!/^[0-9]+$/.test(e)||isNaN(e)||0>=e)return f.text("库存必须是不小于1的整数").show().addClass("fail"),
t.focus(),h.hide(),!1;
var a=1e9;
return e>=a?(f.text("库存不能大于10亿").show().addClass("fail"),t.focus(),!1):d*e>p?(f.html('券点余额：%s 余额不足，<a target="_blank" href="%s">去充值</a>'.sprintf(p/100,wx.url("/merchant/cardmoneymgr?action=get_order_flow"))).show().addClass("fail"),
t.focus(),h.show(),m.text(d*e/100),!1):(f.text("券点余额：%s，优先使用免费券点".sprintf(p/100)).show().removeClass("fail"),
h.show(),void m.text(d*e/100));
});
$(".js_confirm",s).click(function(){
var t=$.trim(y.val());
if(!/^[0-9]+$/.test(t)||isNaN(t)||0>=t)return f.text("库存必须是不小于1的整数").show().addClass("fail"),
y.focus(),h.hide(),!1;
var a=1e9;
return t>=a?(f.text("库存不能大于10亿").show().addClass("fail"),y.focus(),!1):d*t>p?(f.html('券点余额：%s 余额不足，<a target="_blank" href="%s">去充值</a>'.sprintf(p/100,wx.url("/merchant/cardmoneymgr?action=get_order_flow"))).show().addClass("fail"),
y.focus(),!1):($(this).btn(!1),t=parseInt(t),void e.get({
url:"/merchant/cardmoneymgr?action=get_card_pay_price",
data:{
card_id:_,
quantity:t
},
success:function(a){
0==a.base_resp.ret?(a.quantity=t,l(2,a)):e.show(a);
}
}));
}),$(".js_cancel",s).click(function(){
c.tooltip.hide(),n.removeAll(),c.tooltip=null;
});
}
s.find(".js_price").text(o.price/100);
}else if(2==i){
if(!r){
var v=!1;
$(".js_confirm",s).click(function(){
v||($(this).btn(!1),v=!0,e.post({
url:"/merchant/cardmoneymgr?action=confirm_card_coin_pay",
data:{
card_id:_,
quantity:o.quantity,
free_coin:o.free_coin,
pay_coin:o.pay_coin,
order_id:o.order_id,
price:o.price
},
complete:function(){
v=!1;
},
success:function(t){
$(this).btn(!0),0==t.base_resp.ret?(t.addquantity=o.quantity,u.pay_info.is_swipe_card?l(9,t):l(3,t)):26==t.base_resp.ret?(t.is_fail=!1,
l(4,t)):10039==t.base_resp.ret||76==t.base_resp.ret?l(8,t):(t.is_fail=!0,l(4,t));
}
}));
}),$(".js_cancel",s).click(function(){
c.tooltip.hide(),n.removeAll(),c.tooltip=null;
});
}
s.find(".js_price").text(o.price/100),s.find(".js_quantity").text(o.quantity),s.find(".js_freecoin").text(o.free_coin/100),
s.find(".js_paycoin").text(o.pay_coin/100);
}else if(3==i||9==i){
r||$(".js_close_quantity",s).click(function(){
n.removeAll();
});
var x=o.addquantity;
s.find(".js_quantity").text(x),a.suc("设置库存成功"),setTimeout(function(){
3==i&&n.removeAll();
},1500),t.quantityChange&&t.quantityChange.call(c,_,x);
}else 4==i||7==i||8==i?(r||$(".js_close_quantity",s).click(function(){
n.removeAll();
}),8==i&&$(".js_quantity_exceed_msg h4",s).text(t.max_sku_for_eachcard>0?" 子商户每张券累计只可发放%s份 ".sprintf(t.max_sku_for_eachcard):" 账号违规，不能改动库存，详请查看通知中心 ")):5==i?r||$(".js_go_activate",s).click(function(){
n.removeAll(),location.href=wx.url("/merchant/cardstat?action=overviewpage");
}):6==i&&e.get({
url:"/merchant/cardmoneymgr?action=check_is_card_money_acct_open"
},function(t){
0==t.base_resp.ret?l(1==t.is_acct_open?0:5):e.show(t);
});
s.attr("isinit",1);
}
var d,_=$(this).data("cid");
if(t.before&&t.before(_)===!1)return!1;
var u=t.data;
if(t.cache_card&&(u=t.cache_card[_]),u.is_sns_card&&3!=u.status&&5!=u.status&&6!=u.status)return a.err("审核中的朋友的券无法修改库存"),
!1;
if(u.is_sns_card){
if(c.tooltip=new r({
container:this,
content:o({
setquantity:t.setquantity,
data:u
}),
container_mode:t.mode||"absolute",
reposition:!0,
type:"click",
onclose:function(t){
if(t){
for(var e=this.$dom.get(0),a=t.target,i=!1;a&&a!==document.body;){
if(a==e){
i=!0;
break;
}
a=a.parentNode;
}
i?this.show():this.hide();
}
}
}),l(6),c.tooltip.show(),n.removeAll(),n.add(c.tooltip),$(".popover").css({
"z-index":"10000",
width:"326px"
}),"fixed"==t.mode){
var p=parseInt(c.tooltip.$dom.css("top"))||0;
c.tooltip.$dom.css("top",p-($(document.body).scrollTop()||0));
}
s.stopPropagation();
}else{
var f=new r({
container:this,
content:o({
setquantity:t.setquantity,
data:u
}),
container_mode:t.mode||"absolute",
type:"click",
reposition:!0,
onclose:function(t){
if(t){
for(var e=this.$dom.get(0),a=t.target,i=!1;a&&a!==document.body;){
if(a==e){
i=!0;
break;
}
a=a.parentNode;
}
i?this.show():this.hide();
}
},
buttons:[{
text:"确定",
type:"btn_primary",
click:function(){
var o=f.$dom,s=o.find(".js_value"),r=parseInt($.trim(s.val()));
if(isNaN(r)||0>=r)return a.err("库存必须是不能小于1的整数"),!1;
var l=1e9;
return r>=l?(a.err("库存不能大于10亿"),s.focus(),!1):void e.post({
url:"/merchant/electroniccardmgr",
data:{
action:t.setquantity?"modifyquantity":"setquantity",
card_id:_,
value:r,
isadd:d.value()
}
},function(o){
if(0==o.base_resp.ret)a.suc("设置库存成功"),n.removeAll(),t.quantityChange&&t.quantityChange.call(c,_,!t.setquantity||d.value()?r:-r);else if(10039==o.base_resp.ret||76==o.base_resp.ret){
var s=$.parseJSON(o.biz_quota_json),l=i.parse_assistsend_quota(s.quota_list);
a.err(l.max_sku>0?"子商户每张券累计只可发放%s份".sprintf(l.max_sku):"账号违规，不能改动库存，详请查看通知中心");
}else 1e4==o.base_resp.ret?a.err("库存不能小于0"):e.show(o);
});
}
},{
text:"取消",
type:"btn_default",
click:function(){
n.removeAll();
}
}]
});
if(f.show(),n.removeAll(),n.add(f),$(".popover").css({
"z-index":"10000",
width:"326px"
}),d=f.$dom.find(".js_quantity_type").checkbox(),f.$dom.find(".js_value").focus(),
"fixed"==t.mode){
var p=parseInt(f.$dom.css("top"))||0;
f.$dom.css("top",p-($(document.body).scrollTop()||0));
}
s.stopPropagation();
}
});
};
return c;
});define("tpl/cardticket/card_preview.html.js",[],function(){
return'<div class="pop_card_preview js_pop_card_preview">\n	<span class="hook hook_right_top js_arrow">\n	<!--\n		箭头位置 \n		hook_right_top      右偏上\n		\n	-->\n		<span class="hook_top"></span>\n		<span class="hook_btm"></span>\n	</span>\n	<div class="card_preview">\n		<div class="client_side">\n			<div class="banner">{convert_type card.type}</div>\n			<div class="wrp">\n				<div class="top" style="background-color: {card.color};border-bottom-color: {card.color};">\n					<div class="logo group">\n						<div class="avartar l"><img src="{http2https card.logo_url}"></div>\n						<p>{card.brand_name}</p>\n					</div>\n					<div class="msg">\n						<div class="main_msg">\n							<p>{card.title}</p>\n							<p class="title_sub">{card.sub_title}</p>\n						</div>\n						<p class="time">有效期 {validtime card \'YYYY-MM-DD\'}</p>\n					</div>\n					<div class="deco"></div>\n				</div>\n				<div class="wrp_content">\n					<div class="wrp_section section_dispose">\n						{if card.code_type==0}\n							<div class="main_msg sn">1513-2290-1878</div>\n						{else if card.code_type==1}\n							<div class="bar_code_panel">\n								<div class="main_msg bar_code"></div>\n								<p class="sn">1513-2290-1878</p>\n							</div>\n						{else if card.code_type==2}\n							<div class="qr_code_panel">\n								<div class="main_msg qr_code"></div>\n								<p class="sn">1513-2290-1878</p>\n							</div>\n						{/if}\n						<p>{card.notice}</p>\n					</div>\n					<div class="wrp_section">\n						<ul class="info_list">\n							<li class="info_li">\n								<p class="info">{convert_type card.type}详情</p>\n								<span class="supply_area"><i class="ic ic_go"></i></span>\n							</li>\n							<li class="info_li">\n								<p class="info">适用门店</p>\n								<span class="supply_area">{card.location_id_list.length}家<i class="ic ic_go"></i></span>\n							</li>\n						</ul>\n					</div>\n				</div>\n			</div>\n		</div>\n	</div>\n</div>';
});define("tpl/cardticket/card_table.html.js",[],function(){
return'<div class="release_method js_card_container send_card">\n	{if loading}\n	<div class="loading"><i class="icon_loading_small white">loading...</i></div>\n	{else}\n	<div class="sub_title_bar group">\n		{if sns_card_type==2}<a href="javascript:void(0);" data-actionid="103" class="js_add_card_link r btn btn_primary js_clickreport">新建朋友的券 </a>{/if}\n		<!-- <span class="frm_input_box search append">\n			<a href="javascript:void(0);" class="js_search frm_input_append">\n				<i class="icon16_common search_gray">搜索</i>\n				&nbsp;\n			</a>\n			<input type="text" placeholder="请输入卡券名称" class="frm_input js_keyword">\n		</span>  -->\n	</div>\n	<div class="table_wrp release_method_select_table_wrp">\n		<table class="table" cellspacing="0">\n			<thead class="thead">\n				<tr>\n					<th class="table_cell release_method_select_box">&nbsp;</th>\n					{if view_mode==2}\n					<th class="table_cell">商户名</th>\n					{/if}\n					<th class="table_cell release_method_kind"><div class="td_panel">卡券类型</div></th>\n					<th class="table_cell release_method_name"><div class="td_panel"><div class="js_filter_tag">卡券名称</div></div></th>\n					<th class="table_cell release_method_time"><div class="td_panel">卡券有效期</div></th>\n					<th class="table_cell release_method_stock"><div class="td_panel">库存</div></th>\n					{if (payflag==1||payflag==2) && sns_card_type!=2}<th class="table_cell release_method_price"><div class="td_panel">微信价(元)</div></th>{/if}\n					<!-- <th class="table_cell release_method_preview"><div class="td_panel">预览</div></th> -->\n					<th class="table_cell release_method_state"><div class="td_panel">卡券状态</div></th>\n				</tr>\n			</thead>\n			<tbody class="tbody">\n			{if !data.length}\n				<tr>\n					<td class="empty_tips" colspan="6">暂无卡券</td>\n				</tr>\n			{else}\n			{each data as card i}\n            <tr  class="{if hasdata && (i<pageInfo.begin||i>=pageInfo.begin+pageInfo.count)}dn{/if}{if (sns_card_type==2 && !card.is_sns_card) || (sns_card_type==1 && card.is_sns_card) || card.is_sub_merchant_disabled} disabled_item{/if}" id="js_ct_tr_{card.id}">\n					<td class="table_cell release_method_select_box"><div class="td_panel">\n						{if !multi}\n						<label class="frm_radio_label">\n							<i class="icon_radio"></i>\n							<input type="radio" data-id="{card.id}" value="{card.id}" class="frm_radio js_select{if sns_card_type}{if card.is_sns_card} js_select_disabled_1{else} js_select_disabled_2{/if}{/if}">\n						</label>\n						{else}\n						<label class="frm_checkbox_label">\n							<i class="icon_checkbox"></i>\n							<input type="checkbox" data-id="{card.id}" value="{card.id}" class="frm_checkbox js_select{if sns_card_type}{if card.is_sns_card} js_select_disabled_1{else} js_select_disabled_2{/if}{/if}">\n						</label>\n						{/if}\n					</div></td>\n					{if view_mode==2}\n					<td class="table_cell release_method_kind"><div class="td_panel">{card.brand_name}</div></td>\n					{/if}\n					<td class="table_cell release_method_kind"><div class="td_panel">{convert_type card.type}</div></td>\n					<td class="table_cell release_method_name"><div class="td_panel">{card.title}{if card.is_sns_card}<i class="ic_social">共享</i>{/if}{if card.is_intercomm}<i class="icon18 ic_intercomm"></i>{/if}</div></td>\n					<td class="table_cell release_method_time"><div class="td_panel">{validtime card \'YYYY-MM-DD\'}</div></td>\n					<td class="table_cell release_method_stock"><div class="td_panel"><span class="js_sendcard_quantity{if card.quantity==0} text_weak{/if}">{card.quantity}</span>\n						{if editquantity && !card.is_from_intercomm && card.can_edit_quantity}<a class="icon14_common edit_gray js_modify_quantity" href="javascript:;" data-new="{if card.isnew}1{/if}" data-cid="{card.id}" data-x="-161"></a>{/if}</div>\n					</td>\n					{if (payflag==1||payflag==2) && sns_card_type!=2}<td class="table_cell release_method_price"><div class="td_panel">{if card.ispay}{card.price}{else}--{/if}</div></td>{/if}\n					<!-- <td class="table_cell release_method_preview"><div class="td_panel"><a data-cid="{card.id}" data-x="-125" class="js_card_preview" href="javascript:void(0);">预览</a></div></td> -->\n					<td class="table_cell release_method_state"><div class="td_panel"><span class="fail pass"><i></i>{convert_state card.status}</span></div></td></td>\n				</tr>\n			{/each}\n			{/if}\n			</tbody>\n		</table>\n		{if sns_card_type==1}<div class="mini_tips l">只能投放普通券</div>{else if sns_card_type==2}<div class="mini_tips l">{if use_scene==2}只能投放商户的其它可共享优惠券{else}只能投放可共享优惠券{/if}</div>{/if}\n        <div class="js_pager"></div>\n        {if multi}\n        <p class="dialog_bt_tip">已选<span class="js_selectcount">{defaultValues.length||0}</span>个</p>\n        {/if}\n	</div>\n	{/if}\n</div>\n';
});