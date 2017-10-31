package wtb.core.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import read.core.service.ReadChanceRecordService;
import read.core.service.ReadLotteryChanceService;
import read.core.service.ReadLotteryRecordService;
import read.core.service.ReadLotteryStatuService;
import read.core.service.ReadPrizesService;
import read.core.service.ReadRedPacketService;
import read.core.service.ReadRollsService;
import wtb.core.model.NoticesTemp;
import wtb.core.model.Students;
import wtb.core.model.Users;
import wtb.core.service.ActivityPartService;
import wtb.core.service.ActivityService;
import wtb.core.service.ApplyListService;
import wtb.core.service.BaseInfoService;
import wtb.core.service.ChanceRecordService;
import wtb.core.service.ClickListService;
import wtb.core.service.CompetitionApplyService;
import wtb.core.service.CommentService;
import wtb.core.service.DealInfoService;
import wtb.core.service.ErrorLogService;
import wtb.core.service.ErrorSendService;
import wtb.core.service.ErrorStatService;
import wtb.core.service.FeedbackService;
import wtb.core.service.GameQuestionService;
import wtb.core.service.HonorRecordService;
import wtb.core.service.IntegrationService;
import wtb.core.service.KeyWordService;
import wtb.core.service.LikeRecordService;
import wtb.core.service.LogFileService;
import wtb.core.service.LotteryChanceService;
import wtb.core.service.LotteryRecordService;
import wtb.core.service.LotteryStatuService;
import wtb.core.service.MessagesService;
import wtb.core.service.NoticesService;
import wtb.core.service.NoticesTempService;
import wtb.core.service.OnLineService;
import wtb.core.service.PaidListingService;
import wtb.core.service.PayRecordService;
import wtb.core.service.PermissionsService;
import wtb.core.service.PicturesService;
import wtb.core.service.PrizesService;
import wtb.core.service.ScenicService;
import wtb.core.service.ProdPicturesService;
import wtb.core.service.ProductService;
import wtb.core.service.QuestionLimitService;
import wtb.core.service.QuestionRecordService;
import wtb.core.service.QuestionStatService;
import wtb.core.service.RedPacketService;
import wtb.core.service.RegionService;
import wtb.core.service.RollsService;
import wtb.core.service.SignRecordService;
import wtb.core.service.SinaBallotService;
import wtb.core.service.StudentsLogService;
import wtb.core.service.StudentsService;
import wtb.core.service.UsersService;
import wtb.core.service.VerifyRecordService;
import wtb.core.service.VersionService;
import wtb.core.service.VideoService;
import wtb.core.service.VoteRecordsService;
import wtb.core.service.VoteService;
import wtb.core.service.WeChatBannerService;
import wtb.core.service.WeChatBindService;
import wtb.core.service.WeChatContentService;
import wtb.core.service.WeChatCustomService;
import wtb.core.service.WeChatGroupPartService;
import wtb.core.service.WeChatGroupService;
import wtb.core.service.WeChatInfoServices;
import wtb.core.service.WeChatPublicService;
import wtb.core.service.WeChatUserService;
import wtb.core.service.WeMoneyService;
import wtb.core.service.WeMoneyRecordService;
import wtb.core.service.MoneyRecordService;
import wtb.core.service.AdvertService;
import wtb.smUtil.ErrorUtil;
import wtb.smUtil.SmBaseUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by SASR on 2016-12-14
 */
@Controller
public class BaseController { // 基础控制器

	@Autowired
	protected ActivityService ActivityService;
	@Autowired
	protected ActivityPartService ActivityPartService;
	@Autowired
	protected  WeChatPublicService WeChatPublicService;
	@Autowired
	protected RegionService RegionService;
	@Autowired
	protected IntegrationService IntegrationService;
	@Autowired
	protected ApplyListService ApplyListService;
	@Autowired
	protected MessagesService MessagesService;
	@Autowired
	protected UsersService UsersService;
	@Autowired
	protected DealInfoService DealInfoService;
	@Autowired
	protected CommentService CommentService;
	@Autowired
	protected ErrorLogService ErrorLogService;
	@Autowired
	protected ErrorSendService ErrorSendService;
	@Autowired
	protected FeedbackService FeedbackService;
	@Autowired
	protected NoticesService NoticesService;
	@Autowired
	protected WeMoneyRecordService WeMoneyRecordService;
	@Autowired
	protected WeMoneyService WeMoneyService;
	@Autowired
	protected MessagesService MessageService;
	@Autowired
	protected  StudentsLogService StudentsLogService;
	@Autowired
	protected LikeRecordService LikeRecordService;
	@Autowired
	protected KeyWordService KeyWordService;
	@Autowired
	protected PaidListingService PaidListingService;
	@Autowired
	protected ProductService ProductService;
	@Autowired
	protected ClickListService ClickListService;
	@Autowired
	protected StudentsService StudentsService;
	@Autowired
	protected PicturesService PicturesService;
	@Autowired
	protected SinaBallotService SinaBallotService;
	@Autowired
	protected WeChatUserService WeChatUserService;
	@Autowired
	protected SignRecordService SignRecordService;
	@Autowired
	protected PermissionsService PermissionsService;
	@Autowired
	protected  VideoService VideoService;
	@Autowired
	protected WeChatContentService WeChatContentService;
	@Autowired
	protected WeChatInfoServices WeChatInfoServices;
	@Autowired
	protected WeChatCustomService WeChatCustomService;
	@Autowired
	protected ProdPicturesService ProdPicturesService;
	@Autowired
	protected WeChatGroupService WeChatGroupService;
	@Autowired
	protected WeChatGroupPartService WeChatGroupPartService;
	@Autowired
	protected WeChatBannerService WeChatBannerService;
	@Autowired
	protected  BaseInfoService BaseInfoService;
	@Autowired
	protected  NoticesTempService NoticesTempService;
	@Autowired
	protected  VerifyRecordService VerifyRecordService;
	@Autowired
	protected OnLineService  OnLineService;
	@Autowired
	protected VoteService  VoteService;
	@Autowired
	protected VoteRecordsService  VoteRecordsService;
	@Autowired
	protected MoneyRecordService MoneyRecordService;
	@Autowired
	protected CompetitionApplyService CompetitionApplyService;
	@Autowired
	protected HonorRecordService HonorRecordService;
	@Autowired
	protected WeChatBindService WeChatBindService;
	@Autowired
	protected PayRecordService PayRecordService;
	@Autowired
	protected wtb.core.service.CompetitionService CompetitionService;
	@Autowired
	protected wtb.core.service.OnlineCountService OnlineCountService;
	@Autowired
	protected wtb.core.service.AccessActiveService AccessActiveService;
	@Autowired
	protected wtb.core.service.NoticesContentService NoticesContentService;
	@Autowired
	protected wtb.core.service.VideoClassService VideoClassService;
	@Autowired
	protected GameQuestionService GameQuestionService;
	@Autowired
	protected QuestionLimitService QuestionLimitService;
	@Autowired
	protected QuestionRecordService QuestionRecordService;
	@Autowired
	protected QuestionStatService QuestionStatService;
	@Autowired
	protected ErrorStatService ErrorStatService;
	@Autowired
	protected  VersionService VersionService;
	@Autowired
	protected  OnLineService OnlineService;
	@Autowired
	protected  LogFileService LogFileService;
	@Autowired
	protected  AdvertService AdvertService;
	/* by Sheryl *///写
	@Autowired
	protected LotteryChanceService lotteryChanceService;
//	protected wtb.core.service.LotteryChanceService lotteryChanceService;
	@Autowired
	protected RollsService rollsService;
//	protected wtb.core.service.RollsService rollsService;
	@Autowired
	protected RedPacketService redPacketService;
	@Autowired
	protected PrizesService prizesService;
	@Autowired
	protected LotteryRecordService lotteryRecordService;
	@Autowired
	protected LotteryStatuService lotteryStatuService;
	@Autowired 
	protected ChanceRecordService chanceRecordService; 
	@Autowired 
	protected ScenicService ScenicService;
	
	
	/**
	 * 只读控制器
	 * 
	 * 
	 */
	@Autowired
	protected  read.core.service.ReadActivityService ReadActivityService;
	@Autowired
	protected  read.core.service.ReadNoticesService ReadNoticesService;
	@Autowired
	protected read.core.service.ReadProdPicturesService ReadProdPicturesService;
	@Autowired
	protected read.core.service.ReadPicturesService ReadPicturesService;
	@Autowired
	protected  read.core.service.ReadRegionService ReadRegionService;
	@Autowired
	protected read.core.service.ReadStudentsService ReadStudentsService;
	@Autowired
	protected read.core.service.ReadWeChatUserService ReadWeChatUserService;
	@Autowired
	protected read.core.service.ReadClickListService ReadClickListService;
	@Autowired
	protected read.core.service.ReadCommentService ReadCommentService;
	@Autowired
	protected read.core.service.ReadIntegrationService ReadIntegrationService;
	@Autowired
	protected read.core.service.ReadKeyWordService ReadKeyWordService;
	@Autowired
	protected read.core.service.ReadLikeRecordService ReadLikeRecordService;
	@Autowired
	protected read.core.service.ReadSignRecordService ReadSignRecordService;
	@Autowired
	protected read.core.service.ReadWeMoneyRecordService ReadWeMoneyRecordService;
	@Autowired
	protected read.core.service.ReadWeMoneyService ReadWeMoneyService;
	@Autowired
	protected read.core.service.ReadUsersService ReadUsersService;
	@Autowired
	protected read.core.service.ReadProductService ReadProductService;
	@Autowired
	protected  read.core.service.ReadWeChatPublicService ReadWeChatPublicService;
	@Autowired
	protected read.core.service.ReadFeedbackService ReadFeedbackService;
	@Autowired
	protected read.core.service.ReadStudentsLogService ReadStudentsLogService;
	@Autowired
	protected read.core.service.ReadActivityPartService ReadActivityPartService;
	@Autowired
	protected read.core.service.ReadCompetitionApplyService ReadCompetitionApplyService;
	@Autowired
	protected read.core.service.ReadBaseInfoService ReadBaseInfoService;
	@Autowired
	protected read.core.service.ReadHonorRecordService ReadHonorRecordService;
	@Autowired
	protected read.core.service.ReadWeChatBindService ReadWeChatBindService;
	@Autowired
	protected read.core.service.ReadPayRecordService ReadPayRecordService;
	@Autowired
	protected read.core.service.ReadCompetitionService ReadCompetitionService;
	@Autowired
	protected read.core.service.ReadOnlineCountService ReadOnlineCountService;
	@Autowired
	protected read.core.service.ReadAccessActiveService ReadAccessActiveService;
	@Autowired
	protected read.core.service.ReadStudentStatService ReadStudentStatService;
	@Autowired
	protected read.core.service.ReadNoticesContentService ReadNoticesContentService;
	@Autowired
	protected read.core.service.ReadVideoClassService ReadVideoClassService;
	@Autowired
	protected read.core.service.ReadVideoService ReadVideoService;
	@Autowired
	protected read.core.service.ReadVoteService ReadVoteService;
	@Autowired
	protected read.core.service.ReadGameQuestionService ReadGameQuestionService;
	@Autowired
	protected read.core.service.ReadQuestionLimitService ReadQuestionLimitService;
	@Autowired
	protected read.core.service.ReadQuestionRecordService ReadQuestionRecordService;
	@Autowired
	protected read.core.service.ReadQuestionStatService ReadQuestionStatService;
	@Autowired
	protected read.core.service.ReadErrorStatService ReadErrorStatService;
	@Autowired
	protected read.core.service.ReadVersionService ReadVersionService;
	@Autowired
	protected read.core.service.ReadLogFileService ReadLogFileService;
	@Autowired
	protected read.core.service.ReadAdvertService ReadAdvertService;
	/* by Sheryl *///只读
	@Autowired
	protected ReadLotteryChanceService readLotteryChanceService;
//	protected read.core.service.ReadLotteryChanceService readLotteryChanceService;
	@Autowired
	protected ReadPrizesService readPrizesService;
//	protected static read.core.service.ReadPrizesService readPrizesService;
	@Autowired
	protected ReadLotteryRecordService readLotteryRecordService;
//	@Autowired
//	protected read.core.service.ReadLotteryRecordService readLotteryRecordService;
	@Autowired
	protected ReadRedPacketService readRedPacketService;
	@Autowired
	protected ReadRollsService readRollsService;
	@Autowired
	protected ReadLotteryStatuService readLotteryStatuService;
	@Autowired
	protected ReadChanceRecordService readChanceRecordService;
	@Autowired
	protected read.core.service.ReadApplyListService ReadApplyListService;
	@Autowired
	protected read.core.service.ReadScenicService ReadScenicService;
	
	
	
	protected SmBaseUtil amBaseUtil = SmBaseUtil.getInstance();
	protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	protected SimpleDateFormat sdfShort = new SimpleDateFormat("yyyy-MM-dd"); 
	@ExceptionHandler
	public void exp(HttpServletRequest request, HttpServletResponse response, Exception ex, HttpSession session) throws ServletException, IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ex.printStackTrace(new PrintStream(baos));
        String exception = baos.toString();
		Students myself = (Students) request.getSession().getAttribute("StudentName");
		String clazz = ex.getClass().getName();
		String method = Thread.currentThread().getStackTrace()[1].getMethodName();
		for(StackTraceElement stack:ex.getStackTrace()){
			if(stack.getClassName()!=null && stack.getClassName().contains("wtb.")){
				clazz=stack.getClassName();
				method=stack.getMethodName();
				break;
			}
		}
		
		//先注释掉 错误发送
		//ErrorUtil.HandleError2(String.valueOf(myself != null ? myself.getID() : 0), clazz+"."+method, ex,exception);
		request.getRequestDispatcher("/include/500.html").forward(request, response);

	}

}
