package cn.edu.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import cn.edu.pojo.Auction;
import cn.edu.pojo.AuctionCustom;
import cn.edu.pojo.Auctionrecord;
import cn.edu.pojo.Auctionuser;
import cn.edu.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.mail.Multipart;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping(value = "/auction")
public class auctionController {
	@Autowired
	private AuctionService auctionService;
	public static final int PAGE_SIZE = 10;
	//每次10条显示商品信息
	@RequestMapping("/queryAuctions.html")
	public ModelAndView queryAuctios(
	        @ModelAttribute("condition") Auction condition,
	        @RequestParam(value="pageNum",required=false,defaultValue="1")int pageNum){
		ModelAndView mv = new ModelAndView();
		PageHelper.startPage(pageNum,PAGE_SIZE);
		List<Auction> list = auctionService.findAuctions(condition);
		mv.addObject("auctionList",list);
		PageInfo pageInfo = new PageInfo<>(list);

		mv.addObject("pageInfo",pageInfo);
		mv.setViewName("index");
		return mv;
	}
	//跳转到添加商品的页面
	@RequestMapping("/goadd.html")
	public String goadd(){
		return "addAuction";
	}
	//添加商品
	@RequestMapping("/publishAuctions.html")
	public String piblishAuctions(Auction auction, MultipartFile pic, HttpSession session){
		try {
		    String path=session.getServletContext().getRealPath("upload");
		    Auctionuser auctionuser=(Auctionuser) session.getAttribute("user");
		    if(pic.getSize()>0){
                File targetFile =new File(path,pic.getOriginalFilename());
				pic.transferTo(targetFile);
            }
		    auction.setAuctionpic(pic.getOriginalFilename());
		    auction.setAuctionpictype(pic.getContentType());
		    auctionService.addAuction(auction,auctionuser.getUsername(),auctionuser.getUserisadmin());
		}catch (IOException e){
			e.printStackTrace();
		}
		return "redirect:/auction/queryAuctions.html";
	}
	//根据id跳转到修改页面
	@RequestMapping("/toupdate/{auctionid}.html")
	public ModelAndView toupdate(@PathVariable int auctionid){
		ModelAndView mv=new ModelAndView();
		Auction auction=auctionService.getAuctionById(auctionid);
		mv.addObject("auction",auction);
		mv.setViewName("updateAuction");
		return mv;
	}
	//修改商品信息
	@RequestMapping("/updateAuctoinSubmit.html")
	public String updateAuctoinSubmit(Auction auction, MultipartFile pic, HttpSession session){
		try {
			String path=session.getServletContext().getRealPath("upload");
			if(pic.getSize()>0){
				File oldFile=new File(path,auction.getAuctionpic());
				if (oldFile.exists()){
					oldFile.delete();
				}
				File targetFile =new File(path,pic.getOriginalFilename());
				pic.transferTo(targetFile);
			}
			auction.setAuctionpic(pic.getOriginalFilename());
			auction.setAuctionpictype(pic.getContentType());
			auctionService.updateAuction(auction);
		}catch (IOException e){
			e.printStackTrace();
		}
		return "redirect:/auction/queryAuctions.html";
	}
	//跳转到竞拍页面
	@RequestMapping("/toDetail/{auctionid}.html")
	public ModelAndView toDetail(@PathVariable int auctionid){
		ModelAndView mv=new ModelAndView();
		Auction auction=auctionService.findAuctionAndRecordListById(auctionid);
		mv.addObject("auctionDetail",auction);
		mv.setViewName("auctionDetail");
		return mv;
	}
	//错误页面
	@RequestMapping("/saveAuctionRecord.html")
	public String saveAuctionRecord(Auctionrecord record,HttpSession session,Model model){
		try{
			Auctionuser auctionuser=(Auctionuser)session.getAttribute("user");
			record.setUserid(auctionuser.getUserid());
			record.setAuctiontime(new Date());
			auctionService.addAuctionRecord(record);
		}catch (Exception e){
			model.addAttribute("errorMsg",e.getMessage());
			e.printStackTrace();
			return "error";
		}
		return "redirect:/auction/toDetail/"+record.getAuctionid()+".html";
	}
	//根据商品id删除商品
	@RequestMapping("/deleteAuction/{auctionid}.html")
	public String deleteAuction(@PathVariable int auctionid){

		Auction auction=auctionService.getAuctionById(12);
		auctionService.deleteAuction(auctionid);
		return "redirect:/auction/queryAuctions.html";
	}
	//跳转到竞拍结果页面
	@RequestMapping("/toAuctionResult.html")
	public ModelAndView toAuctionResult(){
		ModelAndView mv=new ModelAndView();
		List<AuctionCustom> list1=auctionService.finAuctionEndtimeList();
		List<Auction> list2=auctionService.findAuctionNoEndtimeList();
		mv.addObject("auctionCustomList",list1);
		mv.addObject("auctionList",list2);
		mv.setViewName("auctionResult");
		return mv;
	}
}
