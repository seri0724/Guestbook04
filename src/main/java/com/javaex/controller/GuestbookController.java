package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Controller
public class GuestbookController {

	@Autowired
	private GuestbookDao dao;
	
	@RequestMapping(value="/list")
	public String list(Model model) {
		System.out.println("list 진입");
		List<GuestbookVo> list = dao.getList();
		model.addAttribute("list", list);
		return"list";
	}
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(@ModelAttribute GuestbookVo vo) {
		System.out.println("add 진입");
		dao.insert(vo);
		return"redirect:/list";
	}
	@RequestMapping(value="/deleteform", method=RequestMethod.GET)
	public String deleteform(@RequestParam("no") int no, Model model) {
		System.out.println("deleteform 진입");
		model.addAttribute("no", no);
		return"deleteform";
	}
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(@ModelAttribute GuestbookVo vo) {
		System.out.println("delete 진입");
		int result = dao.delete(vo.getNo(), vo.getPassword());
		if(result == 1) { 
			System.out.println("정상 삭제 되었습니다.");
			return"redirect:/list";
		}else {
			System.out.println("비밀번호가 틀렸습니다.");
			return"redirect:/list";
		}
	}
}