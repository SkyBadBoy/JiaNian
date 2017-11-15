package wtb.smUtil;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.aliyun.oss.OSSClient;

public class SmBaseGlobal {
	private static SmBaseGlobal instance;
	/**
	 * 统一的公司名称
	 */
	public static String CompanyName="杭州嘉念网络科技有限公司";
	
	public static String YouMenCode="";
	/**
	 * 当前的年度
	 */
	public static String CurrentYear=String.valueOf((new Date()).getYear()+1900);
	
	/**
	 * 官网地址
	 */
	public static String JIANIANPC="views/JiaNianPC/";
	/**
	 * 移动端视图的路径
	 */
	public  static String  WebViewPath="views/web/";
	public  static String  WebApyApiPath="views/api/";
	/**
	 * 移动端的视图路径
	 */
	public  static String  MobileViewPath="views/mobiles/";
	/**
	 * 基本视图路径
	 */
	public  static String  BaseViewPath="views/";
	/**
	 * 移动端Sina小编视图路径
	 */
	public static String SinaMobileViewPath="views/SinaMobile/";
	/**
	 * 移动端微官网视图路径
	 */
	public static String WeNewsMobileViewPath="views/WeNewsMobiles/";
	
	/**
	 * pc端微官网视图路径
	 */
	public static String WeNewsPCViewPath="views/WeNewsPC/";
	/**
	 * 用户默认的头像 /img/profile_small.png
	 */
	public  static String  UserDefaultImageUrl="/img/tx_default.jpg";
	public  static String  SchoolDefaultImageUrl="/img/schooldefault.png";
	public  static String  PhoneUserDefaultImageUrl="/img/tx_default.jpg";
	public  static String  ErrorPicture="img/errorpic.jpg";
	public  static String  DefaultVideoPicture="img/errorpic.jpg";
	
	/**
	 * 岗位图标
	 */
	public  static String  Chief="images/weixinwenshe/sz.png";
	public  static String  DeputyChief="images/weixinwenshe/fsz.png";
	public  static String  Editorial="images/weixinwenshe/bw.png";
	public  static String  Normal="images/weixinwenshe/cy.png";
	public  static String  UnNormal="images/weixinwenshe/fcy.png";
	/**
	 * 微信公众号接口
	 */
	public  static String  WeChatAPIURL ="http://www.whohelp.cc/";
	/**
	 * 掌易的微博授权登录
	 */
	public  static String  XBLoginOrRegister=WeChatAPIURL+"WeNewsAgency/Students/phoneRegister";//="http://sina.ztnet.com.cn/index.php/Home/Phone/index";
	public  static String  XBUrl="http://sina.ztnet.com.cn/";
	/**
	 * 上传图片服务器 上传操作
	 */
	public  static String PictureService="http://pic.whohelp.cc/PictureService/ProdPicture/uploadpic";
	/**
	 * 上传图片服务器 删除操作
	 */
	public  static String PictureServiceDelete="http://pic.whohelp.cc/PictureService/ProdPicture/deleteProdPictures";
	public  static int DefaultWeMoney=10;//新用户初始默认添加的微米数量
	public  static int BaseRewardWeMoney=5;//系统奖励微米的基础值
	
	public  static  SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
	public  static  SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public  static  SimpleDateFormat sdfDate_zn = new SimpleDateFormat("yyyy年MM月dd日");
	public  static  int ScreenHeight = 700;
	/**
	 * 上传视频
	 */
	public  static String VideoService="WeNewsInterface/Video/AddVideo";
	public  static String WeNewsInterfaceService="WeNewsInterface";
	/**
	 * 接口开关条件
	 */
	public static boolean IOpen=true;
	
	
	/**
	 * 报名管理
	 * @author MaJian
	 *
	 */
	public enum ApplyType {
		/**
		 * 已经处理(报名)
		 */
		NoApply{
			public int getid() {
				return 0;
			}
		},
		/**
		 * 已经处理(报名)
		 */
		Dispose {
			public int getid() {
				return 1;
			}
		},	
		/**
		 *  未处理
		 */
		NoDispose {
			public int getid() {
				return 2;
			}
		},
		/**
		 *  等待处理
		 */
		LoadDispose {
			public int getid() {
				return 3;
			}
		};
		public abstract int getid();
	}
	
	/**
	 * 
	 * @author MaJian
	 *	从哪个地方报名的
	 */
	public enum ApplyWhichType {
		/**
		 * 官网
		 */
		Official {
			public int getid() {
				return 1;
			}
		},	
		/**
		 *  手机端 微官网
		 */
		Mobile {
			public int getid() {
				return 2;
			}
		};
		public abstract int getid();
	}
	
	
	/**
	 * 状态类型 
	 * 
	 * @author SASR_Studio
	 * 
	 *         1 有效 ,所有 2 商家已加盟 ,所有 88 不可用,所有
	 */
	public enum CheckStatus {
		Default {
			public int getid() {
				return 0;
			}
		},
		/** 有效,所有 */
		Effective {
			public int getid() {
				return 1;
			}
		},
		/** 已加盟 ,所有 */
		AddGrouped {
			public int getid() {
				return 2;
			}
		},
		/** 不可用,所有 */
		Disabled {
			public int getid() {
				return 88;
			}
		},
		/** 未激活 */
		UnActivate {
			public int getid() {
				return -1;
			}
		},
		
		/** 待审核 */
		Apply {
			public int getid() {
				return 7;
			}
		},
		/** 未通过 */
		NotPass {
			public int getid() {
				return 8;
			}
		},
		/** 上头条*/
		Up {
			public int getid() {
				return 9;
			}
		},
		/** 下头条*/
		Down {
			public int getid() {
				return 10;
			}
		},
		/**编辑*/
		Edit {
			public int getid() {
				return 11;
			}
		},
		/** 草稿  从来没有发布过的*/
		Draft {
			public int getid() {
				return 12;
			}
		};
		public abstract int getid();
	}

	public enum LevelStatus {
		/** 超级管理员 */
		SuperManage {
			public int getid() {
				return 3;
			}
		},
		/** 普通管理员 */
		Manage {
			public int getid() {
				return 4;
			}
		},
		/** 学生 */
		StudentManage {
			public int getid() {
				return 10;
			}
		},
		/** 教师 */
		TeacherManage {
			public int getid() {
				return 9;
			}
		},
		/** 区域管理员 */
		AreaManage {
			public int getid() {
				return 5;
			}
		},
		/** 商家 */
		ParsonManage {
			public int getid() {
				return 6;
			}
		};

		public abstract int getid();
	}

	/**
	 * userSex
	 * 
	 * @author SASR_Studio
	 * 
	 */
	public enum SexStatus {
		/** 男性 */
		Male {
			public int getid() {
				return 37;
			}
		},
		/** 女性 */
		Female {
			public int getid() {
				return 38;
			}
		};
		public abstract int getid();
	}
	
	/**
	 * userSex
	 * 
	 * @author SASR_Studio
	 * 
	 */
	public enum RegionType {
		/** 国家 */
		Country {
			public int getid() {
				return 0;
			}
		},
		/** 省级 */
		Province {
			public int getid() {
				return 1;
			}
		},
		/** 市级 */
		City {
			public int getid() {
				return 2;
			}
		},
		/** 区级 */
		Region {
			public int getid() {
				return 3;
			}
		},
		/** 学校 */
		School {
			public int getid() {
				return 4;
			}
		};
		public abstract int getid();
	}
	
	public enum NewType {
		/** 通知 */
		Notices {
			public int getid() {
				return 1;
			}
		},
		/** 申请列表 */
		Applys {
			public int getid() {
				return 3;
			}
		},
		/** 学生新闻 */
		News {
			public int getid() {
				return 2;
			}
		};
		public abstract int getid();
	}
	/**
	 * 处理信息列表
	 * @author SASR_Studio
	 *
	 */
	public enum DealInfoType {
		/** 申请列表 */
		ApplyList {
			public int getid() {
				return 1;
			}
		},
		/** 批改列表 */
		CorrectList {
			public int getid() {
				return 2;
			}
		};
		public abstract int getid();
	}
	/**
	 * 微米增减类型 1为增加微米 2为赞赏 3为消费 4为其他
	 * @author SASR_Studio
	 *
	 */
	public enum WeMoneyType {
		/** 新增微米 */
		Added {
			public int getid() {
				return 1;
			}
		},
		Reward { //打赏微米
			public int getid() {
				return 2;
			}
		},
		Consume {//消费微米
			public int getid() {
				return 3;
			}
		},
		Other {//其他奖励
			public int getid() {
				return 4;
			}
		},
		AutoReward {//系统奖励
			public int getid() {
				return 5;
			}
		};
		public abstract int getid();
	}
	
	
	/**
	 * 微米增减类型 1为增加微米 2为赞赏 3为消费 4为其他
	 * @author SASR_Studio
	 *
	 */
	public enum WeMoneyClassify {
		None {/*无 */
			public int getid() {
				return 0;
			}
		},
		/** 阅读微米 */
		Read {
			public int getid() {
				return 1;
			}
		},
		Like { //点赞微米
			public int getid() {
				return 2;
			}
		},
		Vote {//投票微米
			public int getid() {
				return 3;
			}
		},
		Comment {//评论微米
			public int getid() {
				return 4;
			}
		},
		Other {//其他奖励
			public int getid() {
				return 5;
			}
		};
		public abstract int getid();
	}
	public enum IntegrationType {
		/** 新增积分*/
		Added {
			public int getid() {
				return 1;
			}
		},
		Consume {//消费积分
			public int getid() {
				return 2;
			}
		},
		Other {//其他奖励
			public int getid() {
				return 3;
			}
		},
		AutoReward {//系统奖励
			public int getid() {
				return 4;
			}
		};
		public abstract int getid();
	}
	 /***
     * 关键字类型
     */
    public enum KeywordType {
        LimitChar { // 平台关键字或者、系统关键字

            public int getCode() {
                return 0;
            }
        },
        SearchChar {  //  用户搜索关键字

            public int getCode() {
                return 1;
            }
        },
        ActivityChar {  //  活动关键字

            public int getCode() {
                return 2;
            }
        },
        BlackChar {  //  登录黑名单关键字

            public int getCode() {
                return 3;
            }
        },
        ReadReward {  //  活动阅读量匹配的微米

            public int getCode() {
                return 4;
            }
        },
        LikeReward {  //  活动点赞匹配的微米

            public int getCode() {
                return 5;
            }
        },
        CommentReward {  //  活动评论匹配的微米

            public int getCode() {
                return 6;
            }
        },
        VoteReward {  //  活动投票匹配的微米

            public int getCode() {
                return 7;
            }
        };
        public abstract int getCode();
    }
	/**
	 * 视频用户类型 0是超级用户，1是学校2是学生
	 * 
	 * @author SASR_Studio
	 * 
	 */
	public enum UserType {
		Admin {
			public int getid() {
				return 0;
			}
		},	
		School {
			public int getid() {
				return 1;
			}
		},
		Student {
			public int getid() {
				return 2;
			}
		};
		public abstract int getid();
	}
	

	
	public enum ChiefType  {
		Chief {//社长
			public int getid() {
				return 3;
			}
		},	
		DeputyChief {//副社长
			public int getid() {
				return 2;
			}
		},
		Editorial {//编委
			public int getid() {
				return 1;
			}
		},
		Normal {//编委
			public int getid() {
				return 0;
			}
		};
		public abstract int getid();
	}
	
	public enum OfficialType  {
		Official {//正式社员
			public int getid() {
				return 1;
			}
		},
		Normal {//普通成员
			public int getid() {
				return 0;
			}
		};
		public abstract int getid();
	}

    

	/**
	 * 钱类型11是付，12退
	 * 13退款失败
	 * 
	 * @author SASR_Studio
	 * 
	 */

	public enum MoneyType {
		/** 付*/
		Pay {
			public int getid() {
				return 11;
			}
		},
		/** 退*/
		Refund {
			public int getid() {
				return 12;
			}
		},
		/** 退款失败*/
		Fail { 
			public int getid() {
				return 13;
			}
		};
		public abstract int getid();
	}
	/**
	 * 评论类型 0是新闻，1是视频
	 * 
	 * @author SASR_Studio
	 * 
	 */
	public enum CommentType {
		NewComment {
			public int getid() {
				return 0;
			}
		},	
		VideoComment {
			public int getid() {
				return 1;
			}
		};
		public abstract int getid();
	}
	
	/**
	 * 支付类型
	 * 14是免费，
	 * 15人民币付钱
	 * 16微米支付
	 * 
	 * @author SASR_Studio
	 * 
	 */
	public enum PayType {
		/** 免费*/
		Free {
			public int getid() {
				return 14;
			}
		},
		/** 人民币*/
		RMB {
			public int getid() {
				return 15;
			}
		},
		/** 微米*/
		WeMoney { 
			public int getid() {
				return 16;
			}
		};
		public abstract int getid();
	}
	/**
	 * 支付用途

	 * 
	 * @author SASR_Studio
	 * 
	 */
	public enum PayUse {
		/** 充值*/
		Recharge {
			public int getid() {
				return 1;
			}
		},
		/** 活动付款 */
		ActivityPay {
			public int getid() {
				return 2;
			}
		},
		/** 比赛付款*/
		MatchPay { 
			public int getid() {
				return 3;
			}
		},
		/** 第三方付款*/
		OtherPay { 
			public int getid() {
				return 4;
			}
		},
		/** 服务付款*/
		ServicePay { 
			public int getid() {
				return 5;
			}
		};
		public abstract int getid();
	}
	/**
	 * 增值服务类型
	 * @author apple
	 *
	 */
	public enum ServicePayType {
		/** 作文批改*/
		Correcting {
			public int getid() {
				return 1;
			}
		};
		public abstract int getid();
	}
	
	public enum SettingType {
		/** 勋章管理*/
		Honor {
			public int getid() {
				return 97;
			}
		};
		public abstract int getid();
	}
	/*2017第一期夏令营专用*/
	public enum SummerCamp {
		OptionalCourse {
			public int getid() {
				return 90;
			}
		},
		SummerCamp {
			public int getid() {
				return 90;
			}
		};
		public abstract int getid();
	}
    
	public enum HonerType {
		/** 写作达人*/
		Write {
			public int getid() {
				return 94;
			}
		},
		/** 新闻达人*/
		Excellent {
			public int getid() {
				return 95;
			}
		},
		/** 传播达人*/
		Spread {
			public int getid() {
				return 96;
			}
		};
		public abstract int getid();
	}
	
	public enum NoticesContentType {
		/** 默认模式*/
		Default{
			public int getid() {
				return 0;
			}
		},
		/** Html模式 */
		Html {
			public int getid() {
				return 1;
			}
		};
		public abstract int getid();
	}
	
	public enum DriveType {
		/** 默认模式 0是后台，1是接口，2是Android，3是IOS，4是其他 */
		Service{
			public int getid() {
				return 0;
			}
		},
		/** 接口模式 */
		Inteface {
			public int getid() {
				return 1;
			}
		},
		/** Android模式 */
		Android {
			public int getid() {
				return 2;
			}
		},
		/** IOS模式 */
		IOS {
			public int getid() {
				return 3;
			}
		},
		/** IOS模式 */
		Other {
			public int getid() {
				return 4;
			}
		};
		public abstract int getid();
	}
	
	public enum ProjectType {
		WeNewsService{
			public int getid() {
				return 1;
			}
		},
		/** 接口模式 */
		WeNewsInteface {
			public int getid() {
				return 2;
			}
		},
		/** 微新闻社后台日统计活跃量 */
		WeNewsServiceDay {
			public int getid() {
				return 3;
			}
		},
		/** 微新闻社接口日统计活跃量 */
		WeNewsIntefaceDay {
			public int getid() {
				return 4;
			}
		};
		public abstract int getid();
	}
	
	
	/**
	 * 
	 * 发稿设备类型
	 *
	 */
	public enum NoticeDeviceType {
		/**微信端*/
		WeChat{
			public int getid() {
				return 0;
			}
		},
		/** pc端 */
		PC {
			public int getid() {
				return 1;
			}
		},/** Android端 */
		Android {
			public int getid() {
				return 2;
			}
		},
		/** 苹果端*/
		IOS {
			public int getid() {
				return 2;
			}
		};
		public abstract int getid();
	}
	
	public enum ClientType {
		
		Android {
			public int getid() {
				return 1;
			}
		},
		/** 苹果端*/
		IOS {
			public int getid() {
				return 0;
			}
		};
		public abstract int getid();
	}
	public enum UpdateType {
		
		NoUpdate {
			public int getid() {
				return 0;
			}
		},
		Update {
			public int getid() {
				return 1;
			}
		},
		/** 苹果端*/
		ForceUpdate {
			public int getid() {
				return 2;
			}
		};
		public abstract int getid();
	}
	
	public static enum AdvertType {
		/**
		 * app启动广告
		 */
		AppLoading {
			public int getid() {
				return 1;
			}
			public String getName() {
				return "app启动广告";
			}
		},
		/**
		 * app首页弹窗广告
		 */
		APPHome {
			public int getid() {
				return 2;
			}
			public String getName() {
				return "app首页弹窗";
			}
		},
		/**新闻详情页面广告
		 * 
		 *  **/
		NewDetail {
			public int getid() {
				return 3;
			}
			public String getName() {
				return "新闻详情";
			}
		},
		/**PC端首页左边Banner
		 * 
		 *  **/
		PCBannerLeft {
			public int getid() {
				return 4;
			}
			public String getName() {
				return "PC左边Banner";
			}
		},
		/**PC端二维码广告
		 * 
		 *  **/
		PCBannerQR {
			public int getid() {
				return 5;
			}
			public String getName() {
				return "PC二维码";
			}
		},
		/**PC端首页右边Banner
		 * 
		 *  **/
		PCBannerRight {
			public int getid() {
				return 6;
			}
			public String getName() {
				return "PC右边Banner";
			}
		},
		/**PC学校Banner
		 * 
		 *  **/
		PCSchoolBanner {
			public int getid() {
				return 7;
			}
			public String getName() {
				return "PC学校Banner";
			}
		},
		/**个人中心背景
		 * 
		 *  **/
		UserCenterBanner {
			public int getid() {
				return 8;
			}
			public String getName() {
				return "个人中心背景";
			}
		},
		/**PC首页的logo
		 * 
		 *  **/
		PCHomeLogo {
			public int getid() {
				return 9;
			}
			public String getName() {
				return "PC首页logo";
			}
		};
		public abstract int getid();
		public abstract String getName();
	}
	
	public enum PictureType {
		/**
		 * 新闻素材图片
		 */
		Default {
			public int getid() {
				return 0;
			}
		},
		/**
		 * 新闻素材图片
		 */
		Material {
			public int getid() {
				return 1;
			}
		},
		/** 首页banner图片
		 * 
		 *  **/
		Banner {
			public int getid() {
				return 2;
			}
		},
		/**
		 * 广告图片
		 * 
		 *  **/
		Advert {
			public int getid() {
				return 3;
			}
		},
		/**
		 * 游戏图片
		 * 
		 *  **/
		Game {
			public int getid() {
				return 4;
			}
		};
		public abstract int getid();
	}
	
	/**
	 * 
	 * @param is
	 * @param prefix
	 * @param FileType Video 视频类型    File 文件类型          Images  图片类型 
	 * @return
	 */
	public static String UploadAliYunFileService(InputStream is,String prefix,String FileType){
		Date date=new Date();
		String path=FileType+"/"+SmBaseGlobal.sdfDate.format(date)+"/";
		UUID uuid = UUID.randomUUID();
		path= path+uuid.randomUUID().toString()+"."+prefix;
		// endpoint以杭州为例，其它region请按实际情况填写
		String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
		// accessKey请登录https://ak-console.aliyun.com/#/查看
		String accessKeyId = "LTAIIQkISE7PpDu1";
		String accessKeySecret = "j5Fd1wNx5w5J3Ox3ntouL8HqWwh3Vs";
		// 创建OSSClient实例
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		// 上传文件流
		InputStream inputStream = is;
		//       Images/2017-03-15/----------------cb21aa0a-cceb-49d6-a507-77f3dd71ee5c_800.PNG
		ossClient.putObject("wenews", path, inputStream);
		// 关闭client
		ossClient.shutdown();
		return "http://wenews.oss-cn-hangzhou.aliyuncs.com/"+path;
	}
	
}
