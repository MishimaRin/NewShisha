package com.tunayo.springboot;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes(types = Cart.class) 
public class CartUseController {
	
	@ModelAttribute("cart")
	public Cart cart() {
		return new Cart();
	}
	
	@RequestMapping("/getC")
	  public Cart getData(@ModelAttribute Cart cart,
			  HttpServletRequest request,HttpSession httpSession,
			  SessionStatus sessionStatus) {
	    //セッションデータ取得
		List<MixCart> ca = cart.getList();
		
		for(MixCart i : ca) {
			String name = i.getName();
			System.out.println(name);
		}
		
	    return cart;
	  }
	
	@GetMapping("/sr")
	public ModelAndView login(@ModelAttribute Cart cart,
			HttpSession httpSession,
			ModelAndView mav) {
		
		
			mav.addObject("msg","ようこそ");
			mav.setViewName("index5");
			MixCart ff = new MixCart();
			ff.setName("sumomo");
			ff.setPrice(2444);
			
			
		
		
		
		return mav;	
	}


}
