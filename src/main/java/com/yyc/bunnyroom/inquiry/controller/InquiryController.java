package com.yyc.bunnyroom.inquiry.controller;

import com.yyc.bunnyroom.inquiry.dto.InquiryDTO;
import com.yyc.bunnyroom.inquiry.dto.InquiryReplyDTO;
import com.yyc.bunnyroom.inquiry.service.InquiryReplyService;
import com.yyc.bunnyroom.inquiry.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("inquirys")
public class InquiryController {

    @Autowired
    private InquiryService inquiryService;

    @Autowired
    private InquiryReplyService inquiryReplyService;

    @GetMapping
    public ModelAndView showAllInquiry(ModelAndView mv){

        List<InquiryDTO> list = inquiryService.showAllInquiry();

        if(Objects.isNull(list)){
            System.out.println("안보여");
        }
        mv.addObject("inquirys", list);
        mv.setViewName("inquiry/allInquirys");
        return mv;
    }

//    @GetMapping
//    public String showInquiryContent(@RequestParam("code") int code, Model model) {
//        // 내용을 검색하고 뷰에 전달하는 로직
//        return "inquiry/allinquirysContent"; // "inquiryContent"가 Thymeleaf 템플릿 이름이라고 가정
//    }


    @GetMapping("/searchInquiry")
    public ModelAndView searchInquiry(ModelAndView mv, @RequestParam int code){

        InquiryDTO contentslist = inquiryService.searchInquiry(code);

        if(Objects.isNull(contentslist)){
            throw new NullPointerException();
        }else{
            mv.addObject("contentslist", contentslist);
            mv.setViewName("inquiry/allinquirysContent");
            return mv;

        }
    }

    @GetMapping("/insert_inquiry")
    public String insertInquiry(){
        return "inquiry/inquiryInsert";
    }

    @PostMapping("/insert")
    public String insertInquiryPage(InquiryDTO inquiryDTO){
        inquiryService.insertInquiry(inquiryDTO);

        return "redirect:/inquirys";
    }




    @GetMapping("/insert_inquiryReply")
    public String insertInquiryReply(){
        return "inquiry/inquiryReplyInsert";
    }

    @PostMapping("/insertReply")
    public String insertInquiryReplyPage(InquiryReplyDTO inquiryReplyDTO){
        inquiryReplyService.insertInquiryReply(inquiryReplyDTO);

        return "redirect:/inquirys/searchInquiry";
    }

    @GetMapping("/update_inquiry")
    public String updateInquiryView(){
        return "inquiry/inquiryUpdate";
    }

    @PostMapping("/updateInquiry")
    public String updateInquiry(InquiryDTO inquiryDTO){
        inquiryService.updateInquiry(inquiryDTO);

        return "redirect:/inquirys";
    }
}
