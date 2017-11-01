<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1#0 Transitional//EN" "http://www#w3#org/TR/xhtml1/DTD/xhtml1-transitional#dtd">
<%@page import="wtb.smUtil.SmBaseGlobal"%>
<%@page import="wtb.smUtil.SmBaseUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = SmBaseUtil.getProjectBaseUrl(request);
%>
<html>
<head>
   		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
   		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title><%=SmBaseGlobal.CompanyName %></title>
		<!-- <link href='<%=basePath %>css/jianianpc/fonts.css' rel='stylesheet' type='text/css'> -->
		<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,800italic,800,700italic,700,600italic,400italic,600,300italic,300|Oswald:400,300,700' rel='stylesheet' type='text/css'>
		<!-- Bootstrap -->
		<link href="<%=basePath %>css/jianianpc/bootstrap.min.css" rel="stylesheet">
		<link href="<%=basePath %>css/jianianpc/font-awesome.min.css" rel="stylesheet">


		<link href="<%=basePath %>css/jianianpc/owl.carousel.css" rel="stylesheet">
		<link href="<%=basePath %>css/jianianpc/owl.theme.css" rel="stylesheet">
		<link href="<%=basePath %>css/jianianpc/owl.transitions.css" rel="stylesheet">

		<link href="<%=basePath %>css/jianianpc/style.css" rel="stylesheet">
	</head>
	<body data-spy="scroll" data-target=".main-nav">

		<header class="st-navbar">
			<nav class="navbar navbar-default navbar-fixed-top clearfix" role="navigation">
				<div class="container"><!-- Brand and toggle get grouped for better mobile display -->
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#sept-main-nav">
							<span class="sr-only">Toggle navigation</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="#"><img src="<%=basePath %>images/photos/logo.png" alt="" class="img-responsive"></a>
					</div>
					
					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse main-nav" id="sept-main-nav">
						<ul class="nav navbar-nav navbar-right">
							<li class="active"><a href="#home">主页</a></li>
							<li><a href="#pricing">套餐</a></li>
							<li><a href="#service">学员</a></li>
							<li><a href="#contact">报名</a></li>
							<li><a href="#about">关于</a></li>
							
							<!-- <li><a href="#blog">报名</a></li> -->
							
						</ul>
					</div><!-- /.navbar-collapse -->
				</div>
			</nav>
		</header>
		
		<section class="home" id="home" data-stellar-background-ratio="0.4">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="st-home-unit">
							<div class="hero-txt">
								<p class="hero-work">杭州嘉念网络科技有限公司</p>
								<h2 class="hero-title">嘉念人人学车 人人都来学车</h2>
								<!-- <p class="hero-sub-title">We Provide Hight Quality Bootstrap Template</p> -->
								<!-- <a href="#" class="btn btn-default btn-lg left-btn">Purchase Now</a> -->
								<a href="#pricing" class="btn btn-main btn-lg">了解套餐</a>
							</div>

						</div>
					</div>
				</div>
			</div>
			<div class="mouse-icon"><div class="wheel"></div></div>
		</section>


<section class="pricing" id="pricing">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="section-title st-center">
							<h3>优惠的套餐</h3>
							<p>我们拥有人性化的套餐</p>
						</div>
					</div>
				</div>
				
				
				
				<div class="row">
				
				<c:forEach items="${Combos }" var="Combos">
						<div class="col-md-4">
							<div class="pricing-table <c:if test="${Combos.comboBest eq 1 } ">featured</c:if>">
								<div class="pricing-header">
									<div class="pt-price">￥${Combos.comboPrice }<small>/元</small></div>
									<div class="pt-name">${Combos.comboTitle }</div>
									<c:if test="${Combos.comboBest eq 1 } ">
										<div class="featured-text">Best Value</div>
									</c:if>
									
								</div>
								<div class="pricing-body">
									<ul>
										<li><i class="fa <c:if test="${Combos.comboParameter1 eq 1 }">fa-check</c:if><c:if test="${Combos.comboParameter1 eq 0 }">fa-times</c:if>"></i> 精准的服务</li>
										<li><i class="fa <c:if test="${Combos.comboParameter2 eq 1 }">fa-check</c:if><c:if test="${Combos.comboParameter2 eq 0 }">fa-times</c:if>"></i> 各科现场免费补考一次 </li>
										<li><i class="fa <c:if test="${Combos.comboParameter3 eq 1 }">fa-check</c:if><c:if test="${Combos.comboParameter3 eq 0 }">fa-times</c:if>"></i> 体检物料、学车用具</li>
										<li><i class="fa <c:if test="${Combos.comboParameter4 eq 1 }">fa-check</c:if><c:if test="${Combos.comboParameter4 eq 0 }">fa-times</c:if>"></i> 优先安排学车</li>
										<li><i class="fa <c:if test="${Combos.comboParameter5 eq 1 }">fa-check</c:if><c:if test="${Combos.comboParameter5 eq 0 }">fa-times</c:if>"></i> 一对一教学、配学车顾问一名</li>
									</ul>
								</div>
								<div class="pricing-footer">
									<a href="#contact" class="btn btn-default">立即报名</a>
								</div>
							</div>
						</div>
				</c:forEach>

					
					<div style="color: #fff;">以上费用均不包括车管所费用￥1000元</div>
					<div style="text-align: center;">以上费用均不包括车管所费用￥1000元</div>
				</div>
			</div>
		</section>
			<section class="call-us">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<h3>如果你有学车意向</h3>
						<a href="#contact" class="btn btn-default-o btn-lg">立即报名</a>
					</div>
				</div>
			</div>
		</section>
	<section class="service" id="service">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="section-title st-center">
							<h3>已经取到证书的学员</h3>
							<p>"我们可以，你也可以"</p>
						</div>
						<div class="row">
							<c:forEach var="comment" items="${Comment1}">
								<div class="col-md-3">
									<div class="st-feature">
										<div class="st-feature-icon" style="background-color: #fff;">
												<img width="80" height="80" style="border-radius: 45px;" src="${comment.imageUrl}">
										</div>
										<strong class="st-feature-title">${comment.name}</strong>
										<p>${comment.content}</p>
									</div>
								</div>
							
							</c:forEach>	
						</div>
				
					</div>
				</div>
			</div>
		</section>

		<!-- <section class="funfacts" data-stellar-background-ratio="0.4">
			<div class="container">
				<div class="row">
					<div class="col-md-3">
						<div class="funfact">
							<div class="st-funfact-icon"><i class="fa fa-briefcase"></i></div>
							<div class="st-funfact-counter" ><span class="st-ff-count" data-from="0" data-to="25964" data-runit="1">0</span>+</div>
							<strong class="funfact-title">Projects</strong>
						</div>
					</div>
					<div class="col-md-3">
						<div class="funfact">
							<div class="st-funfact-icon"><i class="fa fa-clock-o"></i></div>
							<div class="st-funfact-counter" ><span class="st-ff-count" data-from="0" data-to="35469" data-runit="1">0</span>+</div>
							<strong class="funfact-title">Hours Work</strong>
						</div>
					</div>
					<div class="col-md-3">
						<div class="funfact">
							<div class="st-funfact-icon"><i class="fa fa-send"></i></div>
							<div class="st-funfact-counter" ><span class="st-ff-count" data-from="0" data-to="86214" data-runit="1">0</span>+</div>
							<strong class="funfact-title">E-mail</strong>
						</div>
					</div>
					<div class="col-md-3">
						<div class="funfact">
							<div class="st-funfact-icon"><i class="fa fa-magic"></i></div>
							<div class="st-funfact-counter" ><span class="st-ff-count" data-from="0" data-to="3647" data-runit="1">0</span>+</div>
							<strong class="funfact-title">Completed</strong>
						</div>
					</div>
				</div>
			</div>
		</section> -->

	
		<!-- <section class="features-desc">
			<div class="container">
				<div class="row">
					<div class="col-md-5">
						<img src="<%=basePath %>images/photos/feature.png" alt="" class="img-responsive">
					</div>
					<div class="col-md-7">
						<h3 class="bottom-line">SOME OF OUR IMPORTANT FEATURES</h3>
						<p>Graeci decore metrodorus conturbamur nostri alii veniamus temperantia audivi, discidia optari pariter formidines nimis dissidens quosvis epicureis, iustitia inbecilloque cognoscerem remotis solet duce pondere, stoicos amaret, faciam sic reperiuntur, timeam dedocere spatio censet cernantur dicas miseram alienum. Attico fonte errem neque, causam nimium reliqui fana, duo sane consequi quos cogitarent dicant profecto.</p>
						<a href="#" class="btn btn-main btn-lg">Read more</a>
					</div>
				</div>
			</div>
		</section> -->

		<!-- <section class="call-2-acction" data-stellar-background-ratio="0.4">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="c2a">
							<h2>Omnibus reliquar rebus</h2>
							<p>Evertitur depravatum illo tamquam novum, possent intus laudatur hinc grate aristoteli per splendido soluta fabulae, ne aristippi cui deleniti nostros illud.</p>
							<a href="#" class="btn btn-main btn-lg">Purchase Now</a>
						</div>
					</div>
				</div>
			</div>
            
		</section> -->
       


		<!-- <section class="clients">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<!-- <div class="section-title st-center">
							<h3>Some Of our clients</h3>
							<p>Avocent deditum long</p>
						</div> -->
					<!-- 	<ul class="clients-carousel">
							<li><img src="<%=basePath %>images/photos/client.png" class="img-responsive" alt=""></li>
							<li><img src="<%=basePath %>images/photos/client2.png" class="img-responsive" alt=""></li>
							<li><img src="<%=basePath %>images/photos/client3.png" class="img-responsive" alt=""></li>
							<li><img src="<%=basePath %>images/photos/client4.png" class="img-responsive" alt=""></li>
							<li><img src="<%=basePath %>images/photos/client5.png" class="img-responsive" alt=""></li>
							<li><img src="<%=basePath %>images/photos/client6.png" class="img-responsive" alt=""></li>
							<li><img src="<%=basePath %>images/photos/client7.png" class="img-responsive" alt=""></li>
							<li><img src="<%=basePath %>images/photos/client8.png" class="img-responsive" alt=""></li>
							<li><img src="<%=basePath %>images/photos/client9.png" class="img-responsive" alt=""></li>
						</ul>
					</div>
				</div>
			</div>
		</section> -->

		<section class="testimonials">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="testimonials-carousel">
							<ul>
								<c:forEach var="comment2" items="${Comment2 }">
									<li>
										<div class="testimonial">
											<div class="testimonial-img">
												<img width="100" height="100" src="${comment2.imageUrl }" alt="">
											</div>
											<blockquote>
												<p>${comment2.content }</p>
												<footer>${comment2.name }<cite title="Source Title"></cite></footer>
											</blockquote>
										</div>
									</li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</section>

		
		<!-- <section class="faq-sec">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
					
						<div class="section-title st-center">
							<h3>Some questions</h3>
							<p>frequently asked questions</p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="faq">
							<h3><i class="fa fa-question-circle"></i> Minime paulo beatus stare?</h3>
							<p>Praesidium quaerat doloribus turpis fruuntur vocant postremo optimus utraque, veserim vitae appellant fructuosam, mediocris consistat impetu illae coniunctione modi consequentis nosque, vis qui deorum, poenis fuisse timorem ferae fastidium.</p>
						</div>
						<div class="faq">
							<h3><i class="fa fa-question-circle"></i> Ferentur interrogari diceret?</h3>
							<p>Pertinerent non importari, populo faciendi civium vetuit. Gravitate numquam praesentium fabulas. Abest ponatur ineruditus restat consoletur causam, ordiamur temperantiam repellat desistemus conquirendae molestia aiunt discenda monet.</p>
						</div>
					</div>
					<div class="col-md-6">
						<div class="faq">
							<h3><i class="fa fa-question-circle"></i> Dicent erigimur secutus fortunae?</h3>
							<p>Quarum difficilius aegritudo epularum municipem alias. Vidisse litteris, rationibus eadem, mandaremus maluisset, delectus terrore angusta deduceret numquam fidelissimae mens gravissimo propter, tradit, fastidium facta facerem qua quippiam vacuitate cupiditatum admirer navigandi.</p>
						</div>
						<div class="faq">
							<h3><i class="fa fa-question-circle"></i> Molestiae dedocere effluere?</h3>
							<p>Habeo mala nocet perpetiuntur legendos dicemus levitatibus abducat futura, occultarum probant vitae evertunt laudantium docendi difficilem, offendit concederetur tuo hortensio deserere, molita gaudemus titillaret difficilius, parum timeret unum molestiam omnis vitae.</p>
						</div>
					</div>
				</div>
			</div>
		</section> -->
	
	

		<!-- <section class="subscribe">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<h3 class="subscribe-title">Subscribe Newsletter</h3>
						<form  role="form" class="subscribe-form">
							<div class="input-group">
								<input type="email" class="form-control" id="mc-email" placeholder="Enter E-mail...">
								<span class="input-group-btn">
									<button class="btn btn-main btn-lg" type="submit">Subscribe!</button>
								</span>
							</div>
						</form>
						<div class="subscribe-result"></div>
						<p class="subscribe-or">or</p>
						<ul class="subscribe-social">
							<li><a href="#" class="social twitter"><i class="fa fa-twitter"></i> Follow</a></li>
							<li><a href="#" class="social facebook"><i class="fa fa-facebook"></i> Like</a></li>
							<li><a href="#" class="social rss"><i class="fa fa-rss"></i> RSS</a></li>
						</ul>
					</div>
				</div>
			</div>
		</section> -->

		<section class="contact" id="contact">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="section-title st-center">
							<h3>立刻报名</h3>
							<p>我们会有更好的服务等着你来</p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
							<input type="text" class="form-control" id="username" name="fname" placeholder="你的名字呦~" style="    height: 50px;">
							<input type="phone" class="form-control" id="userphone" name="email" placeholder="你的手机~" style="    height: 50px; margin-top: 25px;">
							<select class="form-control" id="course" name="subj" style="    height: 50px;margin-bottom: 30px;		margin-top: 25px;					">
									<option  value ="0">请选择课程</option>
									<option value ="1">团报班</option>
									<option value="2">普通版</option>
									<option value="3">挂科无忧班</option>
							</select>
						
							<textarea id="content" name="mssg" placeholder="你的意见" class="form-control" rows="10"></textarea>
							<button style="margin-top: 20px;" class="btn btn-main btn-lg" type="submit" id="send" data-loading-text="<i class='fa fa-spinner fa-spin'></i> Sending..."><i class="fa fa-paper-plane "></i> 报名</button>
						<div id="result-message" role="alert"></div>
					</div>
				</div>
			</div>
		</section>
		<section class="about" id="about">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="section-title st-center">
							<h3>WelCome to You</h3>
							<p>我们是有梦想的团队</p>
						</div>
						<div class="row mb90">
							<div class="col-md-6">
								<P style="text-indent:2em;margin-top: 20px">杭州嘉念网络科技有限公司是由一群有志青年集体创办，经过社会的锤炼后我们走到一起，互相成为公司运作中的长期发展战略伙伴。公司以为客户提供全方位、一体化的服务为己任。
								 <div style="text-indent:2em;">公司旗下嘉念人人学车项目自2016年11月开始运营，至2017年9月，已服务学员近千名，服务范围覆盖杭州多所高校。目前，嘉念人人学车项目已逐步跨入健康、稳定的发展道路，已有业内人士进行业务投资，并给予公司大力支持。嘉念人人学车项目在杭州部分地区已实现自营，未来，我们将继续深化供应链，在其它地区逐步实现自营。</div>
								</P>
							</div>
							<div class="col-md-6">
								<img src="<%=basePath %>images/photos/about.jpg" alt="" class="img-responsive">
							</div>
						</div>

						<div class="row">
							<div class="col-md-4">
								<div class="st-member">
									<div class="st-member-img">
										<img src="<%=basePath %>images/photos/member1.png" alt="" class="img-responsive">
									</div>
									<div class="st-member-info">
										<strong class="st-member-name">李甲兴</strong>
										<p class="st-member-pos">公司创始人</p>
										<div class="skills">
											<div class="skill">
												<strong>执行力</strong>
												<span>100%</span>
												<div class="progress">
													<div class="progress-bar progress-bar-sept" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%;">
														<span class="sr-only">100% </span>
													</div>
												</div>
											</div>
											<div class="skill">
												<strong>管理能力</strong>
												<span>100%</span>
												<div class="progress">
													<div class="progress-bar progress-bar-sept" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%;">
														<span class="sr-only">100%</span>
													</div>
												</div>
											</div>
											<div class="skill">
												<strong>服务力</strong>
												<span>100%</span>
												<div class="progress">
													<div class="progress-bar progress-bar-sept" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%;">
														<span class="sr-only">100%</span>
													</div>
												</div>
											</div>
										</div>
										<div class="st-member-social">
											<ul>
												<li><a href="#" class="wechat" data-toggle="tooltip" data-placement="top" title="wxid_hlpirtr3q3sl22"><i class="fa fa-wechat"></i></a></li>
												<li><a href="#" class="weibo" data-toggle="tooltip" data-placement="top" title="weibo"><i class="fa fa-weibo"></i></a></li>
												<li><a href="#" class="qq" data-toggle="tooltip" data-placement="top" title="qq"><i class="fa fa-qq"></i></a></li>
											</ul>
										</div>
									</div>
								</div>
							</div>

							<div class="col-md-4">
								<div class="st-member">
									<div class="st-member-img">
										<img src="<%=basePath %>images/photos/member2.png" alt="" class="img-responsive">
									</div>
									<div class="st-member-info">
										<strong class="st-member-name">李甲兴</strong>
										<p class="st-member-pos">公司创始人</p>
										<div class="skills">
											<div class="skill">
												<strong>执行力</strong>
												<span>100%</span>
												<div class="progress">
													<div class="progress-bar progress-bar-sept" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%;">
														<span class="sr-only">100% </span>
													</div>
												</div>
											</div>
											<div class="skill">
												<strong>管理能力</strong>
												<span>100%</span>
												<div class="progress">
													<div class="progress-bar progress-bar-sept" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%;">
														<span class="sr-only">100%</span>
													</div>
												</div>
											</div>
											<div class="skill">
												<strong>服务力</strong>
												<span>100%</span>
												<div class="progress">
													<div class="progress-bar progress-bar-sept" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%;">
														<span class="sr-only">100%</span>
													</div>
												</div>
											</div>
										</div>
										<div class="st-member-social">
											<ul>
												<li><a href="#" class="wechat" data-toggle="tooltip" data-placement="top" title="wxid_hlpirtr3q3sl22"><i class="fa fa-wechat"></i></a></li>
												<li><a href="#" class="weibo" data-toggle="tooltip" data-placement="top" title="weibo"><i class="fa fa-weibo"></i></a></li>
												<li><a href="#" class="qq" data-toggle="tooltip" data-placement="top" title="qq"><i class="fa fa-qq"></i></a></li>
											</ul>
										</div>
									</div>
								</div>
							</div>

							<div class="col-md-4">
								<div class="st-member">
									<div class="st-member-img">
										<img src="<%=basePath %>images/photos/member3.png" alt="" class="img-responsive">
									</div>
									<div class="st-member-info">
										<strong class="st-member-name">李甲兴</strong>
										<p class="st-member-pos">公司创始人</p>
										<div class="skills">
											<div class="skill">
												<strong>执行力</strong>
												<span>100%</span>
												<div class="progress">
													<div class="progress-bar progress-bar-sept" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%;">
														<span class="sr-only">100% </span>
													</div>
												</div>
											</div>
											<div class="skill">
												<strong>管理能力</strong>
												<span>100%</span>
												<div class="progress">
													<div class="progress-bar progress-bar-sept" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%;">
														<span class="sr-only">100%</span>
													</div>
												</div>
											</div>
											<div class="skill">
												<strong>服务力</strong>
												<span>100%</span>
												<div class="progress">
													<div class="progress-bar progress-bar-sept" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%;">
														<span class="sr-only">100%</span>
													</div>
												</div>
											</div>
										</div>
										<div class="st-member-social">
											<ul>
												<li><a href="#" class="wechat" data-toggle="tooltip" data-placement="top" title="wxid_hlpirtr3q3sl22"><i class="fa fa-wechat"></i></a></li>
												<li><a href="#" class="weibo" data-toggle="tooltip" data-placement="top" title="weibo"><i class="fa fa-weibo"></i></a></li>
												<li><a href="#" class="qq" data-toggle="tooltip" data-placement="top" title="qq"><i class="fa fa-qq"></i></a></li>
											</ul>
										</div>
									</div>
								</div>
							</div>
							
							
						</div>

					</div>
				</div>
			</div>
		</section>
		<!-- <footer class="site-footer">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						&copy; CantoThemes 2017.
						Don't Remove/Edit this. If you remove this you don't have permission to use this template.
						
						So Please don't remove this
					</div>
				</div>
			</div>
		</footer> -->
		
		
		<div class="row">
                <div style="font-size: 12px;" class=" text-center">
                    <span>杭州嘉念网络科技有限公司-版权所有 Copyright  © 2016 - 2017.　All Rights Reserved.</span>
                   
				</div>
				<br>

            </div>

		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="<%=basePath %>js/jianianpc/jquery.min.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="<%=basePath %>js/jianianpc/bootstrap.min.js"></script>
		<script src="<%=basePath %>js/jianianpc/jquery.easing.min.js"></script>
		<script src="<%=basePath %>js/jianianpc/jquery.stellar.js"></script>
		<script src="<%=basePath %>js/jianianpc/jquery.appear.js"></script>
		<script src="<%=basePath %>js/jianianpc/jquery.nicescroll.min.js"></script>
		<script src="<%=basePath %>js/jianianpc/jquery.countTo.js"></script>
		<script src="<%=basePath %>js/jianianpc/jquery.shuffle.modernizr.js"></script>
		<script src="<%=basePath %>js/jianianpc/jquery.shuffle.js"></script>
		<script src="<%=basePath %>js/jianianpc/owl.carousel.js"></script>
		<script src="<%=basePath %>js/jianianpc/jquery.ajaxchimp.min.js"></script>
		<script src="<%=basePath %>js/jianianpc/script.js"></script>
		<script src="<%=basePath %>js/jianianpc/swwetalert.min.js"></script>
	<!-- 	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script> -->
		<script type="text/javascript">
			function a(){
				console.log("asdf");
			}
			$("#send").click(function(){
				var UserName=$("#username").val();
				var UserPhone=$("#userphone").val();
				var course=$("#course").val();
				var content=$("#content").val();
				$.ajax({
                    url : "<%=basePath%>ApplyList/applyCombo",
                    type:"post",
                    data : {
                          'UserName' : UserName,
                          'UserPhone' : UserPhone,
                          'Course' : course,
                          'Content':content,
                          'ApplyType':<%=SmBaseGlobal.ApplyWhichType.Official.getid()%>
                    },
                    success : function(obj) {
                    		if(obj.wh_code==0){
                    			swal("",obj.message, "success", {
                    				  button: "好",
                    			});
                    		}else{
                    			swal("",obj.message, "warning", {
                  				  button: "重试",
                  			}); 
                    		}
                    		     
                    }
                 });
			})
		</script>
	</body>
</html>
   