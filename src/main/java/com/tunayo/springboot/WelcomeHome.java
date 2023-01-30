package com.tunayo.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import javax.annotation.PostConstruct;
import java.util.Optional;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.BindingResult;


import com.tunayo.springboot.repositories.MyDataRepository;
import com.tunayo.springboot.repositories.MyDataRepository2;
import com.tunayo.springboot.repositories.MyDataRepository3;
import com.tunayo.springboot.repositories.MyDataRepository4;
import com.tunayo.springboot.repositories.MyDataRepository5;
import com.tunayo.springboot.repositories.MyDataRepository6;

import lombok.Data;

import com.tunayo.springboot.User;

@Controller
@Service
public class WelcomeHome {
	@Autowired
	MyDataRepository repository;
	
	@Autowired
	MyDataRepository4 repository4;

	@Autowired
	MyDataRepository2 repository2;
	
	@Autowired
	MyDataRepository5 repository5;
	
	@Autowired
	MyDataRepository6 repository6;
	
	@Autowired
	LoginUser loginUser;
	
	@RequestMapping(value = "/makeMix",method = RequestMethod.GET)
	
		public ModelAndView make( 
			@ModelAttribute("formModel")MixFlavor mixflavor,
			ModelAndView mav) {
				mav.setViewName("make");
				mav.addObject("msg","this is sample content.");
				Iterable<MixFlavor> list = repository2.findAll();
				mav.addObject("datalist",list);
		
				return mav;
		}
	
	@RequestMapping(value = "/pra2",method = RequestMethod.GET)
	
	public ModelAndView pra2(ModelAndView mav) {
		mav.setViewName("pra2");		return mav;
		
	}
	
		@RequestMapping(value = "/makeMix",method = RequestMethod.POST)
		@Transactional(readOnly=false)
		public ModelAndView form(
			@ModelAttribute ("formModel")
	        @Validated MixFlavor mixflavor,
	        
			BindingResult result,
			ModelAndView mov) {
			
			//id auto incrementにつきまだここは入っていない
				
				ModelAndView res = null;
				if(!result.hasErrors()) {
					repository2.saveAndFlush(mixflavor);
					String str2 = mixflavor.getCons();
					
					Cart cart = loginUser.getCart();
					List<MixCart> mixCart = cart.getList();
					MixCart mi = new MixCart();
					mi.setName("koremo");
					mi.setPrice(400);
					mixCart.add(mi);
					
					String[] str3 = str2.split(",");
			        for (String str4 : str3) {
			        	Composition com = new Composition();
			        	Flavor gg = repository.findByName(str4);
			        	int id = mixflavor.getId();
			        	com.setFlavorid(gg.getId());
			        	com.setMixid(id);
			        	repository5.save(com);
			        	//idは重複していないのに更新をかけられてしまう
			        	
	//ここにレポジトリでインサート処理		           
			        }
					res = new ModelAndView("redirect:/");
				}else {
					
					mov.addObject("msg","sorry,error is occured...");
					Iterable<MixFlavor> list = repository2.findAll();
					mov.addObject("datalist",list);
					mov.addObject("loginUser",loginUser);
					mov.setViewName("make");
					res = mov;
				}
				return res;
		}
		
		
		@RequestMapping(value = "/check",method = RequestMethod.POST)

		public ModelAndView check(
				@RequestParam("checkbox")int[] id,
				@RequestParam("buy")int[] count,
				ModelAndView mav) {
			
			int buy = count[0];
			//暫定処理
			//本来これでは複数が選択されてきた場合動かない
			//idを引数に取ってきたリストのmixからIDをKEYとして
			//出したindexOFでその番号から元々の並び順であれば
			//何番目のリストが選択されたか分かるため数量のセレクトボックス
			//が取得できる
			//int buy = Integer.parseInt(count);
			List<MixFlavor> mix = new ArrayList<MixFlavor>();
			List<Integer> k = loginUser.getCount();
			for(int p : id) {
				k.add(p);
			}
			for(int o : id) {
				MixFlavor j = repository2.findById(o);
				mix.add(j);
				
			}
			mav.addObject("datalist",mix);
			mav.addObject("loginUser",loginUser);
			mav.addObject("buy",buy);
			mav.setViewName("buyView");
			
			return mav;
		}
		
		@RequestMapping(value = "/check",method = RequestMethod.GET)

		public ModelAndView checking(
				ModelAndView mav) {
			Cart cart = loginUser.getCart();
			List<MixCart> mixCart = cart.getList();
			int total = loginUser.getTotal();
			loginUser.setNumber(mixCart.size());
			mav.addObject("datalist",mixCart);
			mav.addObject("total",total);
			mav.addObject("loginUser",loginUser);
			mav.setViewName("cart");
			
			return mav;
		}
		@RequestMapping(value = "/buy",method = RequestMethod.POST)

		public ModelAndView made(
				ModelAndView mav) {
			
			Cart cart = loginUser.getCart();
			List<MixCart> mixCart = cart.getList();
			
			List<Integer> i = new ArrayList<Integer>();
			List<String> s = new ArrayList<String>();
			List<Integer> k = loginUser.getCount();
			if(k != null) {
			
			for(int u : k) {
				MixFlavor fla = repository2.findById(u);
				MixCart mi = new MixCart();
				mi.setName(fla.getName());
				mi.setPrice(fla.getValue());
				mixCart.add(mi);
				
				i.add(fla.getValue());
				s.add(fla.getName());
			}
			}else {
				
			}
			
			loginUser.setTotal(i.stream().reduce(0, Integer::sum));
			int total = loginUser.getTotal();
			String str = String.join(",", s);
			//int r = str.length()-1;
			//String str2 = str.substring(1, r);
			
			mav.addObject("datalist",mixCart);
			mav.addObject("consname",str);
			mav.addObject("total",total);
			mav.addObject("loginUser",loginUser);
			mav.setViewName("cart");
			
			
			return mav;
		}
		
		@RequestMapping(value = "/remove",method = RequestMethod.GET)

		public ModelAndView ma(
				@RequestParam("name")String key,
				ModelAndView mav) {
			Cart cart = loginUser.getCart();
			List<MixCart> mixCart = cart.getList();
			
			int firstIndex = mixCart.indexOf(key);
			int g = mixCart.get(firstIndex+1).getPrice();
			int o = loginUser.getTotal();
			mixCart.remove(firstIndex+1);
			int e = loginUser.getNumber();
			loginUser.setNumber(e-1);
			loginUser.setTotal(o-g);
			mav.addObject("datalist",mixCart);
			
			mav.addObject("loginUser",loginUser);
			mav.setViewName("cart");
			
			
			return mav;
		}

		@RequestMapping(value = "/getdown",method = RequestMethod.GET)

		public ModelAndView mad(
				@RequestParam("id")int id,
				ModelAndView mav) {
			Cart cart = loginUser.getCart();
			List<MixCart> mixCart = cart.getList();
			
			MixFlavor ji = repository2.findById(id);
			int value = ji.getValue();
			String name = ji.getName();
			MixCart fk = new MixCart(name,value);
			mixCart.add(fk);
			
			cart.setList(mixCart);
			int total = loginUser.getTotal();
			loginUser.setNumber(mixCart.size());
			loginUser.setTotal(total + ji.getValue());
			List<MixFlavor> popo  = repository2.findAll();
			mav.addObject("datalist",popo);
			
			mav.addObject("loginUser",loginUser);
			mav.setViewName("MixFlavor");
			
			
			return mav;
		}

	
		@Autowired
		MyDataRepository3 repository3;
		@RequestMapping(value = "/makeUser",method = RequestMethod.GET)
	
		public ModelAndView makeUser( 
			@ModelAttribute("formModel")User user,
			ModelAndView mav) {
		    	mav.setViewName("makeUser");
		    	mav.addObject("msg","this is sample content.");
		    	return mav;
		}
	//バリデーションに引っかっかてない＠valid
		@RequestMapping(value = "/makeUser",method = RequestMethod.POST)
		@Transactional(readOnly=false)
		public ModelAndView forms(
			@ModelAttribute ("formModel")
			@Validated User user,
			BindingResult result,
			ModelAndView mov) {
				ModelAndView res = null;
				if(!result.hasErrors()) {
					repository3.saveAndFlush(user);
					res = new ModelAndView("redirect:/");
				}else {
					mov.setViewName("makeUser");
					mov.addObject("msg","sorry,error is occured...");
			
					res = mov;
				}
				return res;
		}
	
		@RequestMapping(value = "/befoMakeUser",method = RequestMethod.POST)
		@Transactional(readOnly=false)
		public ModelAndView befoforms(
			@ModelAttribute ("formModel")
			@Validated User user,
			BindingResult result,
			ModelAndView mov) {
				ModelAndView res = null;
				if(!result.hasErrors()) {
					res = new ModelAndView();
					mov.setViewName("befoMakeUser");
				}else {
					mov.setViewName("makeUser");
					mov.addObject("msg","sorry,error is occured...");
			
					res = mov;
				}
				return res;
		}

		@RequestMapping(value = "/Flavor",method = RequestMethod.POST)

		public ModelAndView Flor(
			@RequestParam (value = "select1",required=false)
			String select1,
			@RequestParam (value = "sort",required=false)
			Integer sort,
			ModelAndView mav) {
				Iterable<Flavor> list = null;
				if(sort == 1) {
					list = repository.findByGenreOrderByValueAsc(select1);
			
				}
				if(sort == 2) {
					list = repository.findByGenreOrderByValueDesc(select1);
			
				}
			mav.addObject("datalist",list);
			mav.addObject("select1",select1);
			mav.addObject("sort",sort);
			mav.setViewName("Flavor");
			return mav;
		}
		
		@RequestMapping(value = "/SearchMix",method = RequestMethod.POST)

		public ModelAndView SearchMix(
			@RequestParam (value = "select1",required=false)
			String select1,
			@RequestParam (value = "sort",required=false)
			Integer sort,
			ModelAndView mav) {
				Iterable<MixFlavor> list = null;
				if(sort == 1) {
					list = repository2.findByGenreOrderByValueAsc(select1);
			
				}
				if(sort == 2) {
					list = repository2.findByGenreOrderByValueDesc(select1);
			
				}
			mav.addObject("datalist",list);
			mav.addObject("select1",select1);
			mav.addObject("sort",sort);
			mav.setViewName("MixFlavor");
			return mav;
		}
	
		@RequestMapping(value = "/men",method = RequestMethod.GET)
	
		public ModelAndView Fl( 
			@ModelAttribute("formModel")Flavor flavor,
			ModelAndView mav,
			@ModelAttribute User user,HttpSession httpSession) {
				mav.setViewName("Flavor");
				String ud = user.getName();
				mav.addObject("msg",ud);
				
				Iterable<Flavor>list =  repository4.fin();
				
				mav.addObject("datalist",list);		
				return mav;
		}

		@RequestMapping(value = "/menMix",method = RequestMethod.GET)

		public ModelAndView Mi( 
			@ModelAttribute("formModel")MixFlavor mixFlavor,
			ModelAndView mav) {
				mav.setViewName("mixFlavor");
	
	 
				Iterable<MixFlavor>list =  repository2.mix();
				mav.addObject("loginUser",loginUser);
				mav.addObject("datalist",list);		
				return mav;
				
				/*Iterable<MixFlavor>list =  repository2.mix();
				 // 全部（１０）のリストを呼び出し
				for(MixFlavor nj : list) {
				//それをリスト分まわし個別のMIXをだし
					String co = nj.getCons();
					//その中から構成の文字を取り出す
					String[] str3 = co.split(",");
					//そしてそれを一つか二つ以上に関係なく
					//カンマを抜く
					//そしてそれが二つ以上の構成を含むのであれば
					if(1 <= str3.length) {
						for (String str4 : str3) {
							String name = str4 + "他";
							mixFlavor.setCons(name);
							list.add(mixFlavor);
			        }
					}
				}*/
		}

		@PostMapping("for")
		public String create(@Validated User user, BindingResult
			result, @ModelAttribute("name") String name,
			RedirectAttributes redirectAttributes) {
				user.setName("gg");
				return "index5";
		}
		@RequestMapping(value = "/mens",method = RequestMethod.GET)

		public ModelAndView Fls( 
			@ModelAttribute("formModel")Flavor flavor,
			ModelAndView mav) {
				mav.setViewName("Flavor");
				mav.addObject("msg","this is sample content.");
				Iterable<Flavor>list =  repository4.fon();
				mav.addObject("datalist",list);
				return mav;
		}
//@RequestMapping(value = "/mensk",method = RequestMethod.POST)
		@GetMapping("/mensk")
		@ResponseBody
		public List<Flavor> note() {
			List<Flavor> json =  repository4.fon();
			return json;
		}
		
		@GetMapping("/menskkk")
		@ResponseBody
		//実質ここに変数をもってくればこの分でも行ける
		public List<Flavor> menskkk(@RequestParam String count) {
			int counto = Integer.parseInt(count);
			if(counto < 22) {
				List<Flavor> json =  
				repository.findByIdGreaterThan(counto);
			
				return json;
			}else {
				return null;
			}
			
		}
		
		@RequestMapping(value = "/pop",method = RequestMethod.POST)
		@ResponseBody// このIDはいわばミックスフレーバーのID
		public List<Flavor> pop(@RequestParam String id) {
			int ido = Integer.parseInt(id);
			List<Flavor> json = new ArrayList<Flavor>();
			List<Composition> comp = repository5.findByMixid(ido);
			for(Composition o : comp) {
				int a = o.getFlavorid();
				Flavor ji = repository.findById(a);
				json.add(ji);
			}
			return json;
		}
		
		@RequestMapping(value = "/checks",method = RequestMethod.POST)
		@ResponseBody// このIDはいわばフレーバーのID
		public String checks(@RequestBody String[] checks) {
			List<String> s = new ArrayList<String>();
			System.out.println(checks);
			for (String o : checks) {
				int ol = Integer.parseInt(o);
				Flavor fla = repository.findById(ol);
				s.add(fla.getName());
			}
			
			String jso = String.join(",", s);
			String json = "{\"jso\":\"" + jso + "\"}";
			
			return json;
		}
		
		@RequestMapping(value = "/makingmix",method = RequestMethod.POST)
		@ResponseBody// このIDはいわばミックスフレーバーのID
		public List<Flavor> popjj(@RequestParam String id) {
			int ido = Integer.parseInt(id);
			List<Flavor> json = new ArrayList<Flavor>();
			List<Composition> comp = repository5.findByMixid(ido);
			for(Composition o : comp) {
				int a = o.getFlavorid();
				Flavor ji = repository.findById(a);
				json.add(ji);
			}
			return json;
		}
		
		@RequestMapping(value = "/home",method = RequestMethod.GET)
	    public ModelAndView mention(@RequestParam("id") Integer id
	    		,ModelAndView mav
	    		,@ModelAttribute("formModel")MixFlavor mixFlavor) {
			MixFlavor part =  repository2.findById(id);
			mav.addObject("mention",part);
			String k = part.getCons();
			List<Flavor> flavor = new ArrayList<Flavor>();
	
		    List<Review> review = repository6.findAll();
		    
		    if(review == null) {
		    	mav.addObject("msg","表示するレビューが見つかりません");
		    }else {
		    	mav.addObject("reviewlist",review);
		    }
			String[] fruits = k.split(",");
	        for (String fruit : fruits) {
	        	Flavor fl = repository.findByName(fruit);
	        	flavor.add(fl);
	        }
	        mav.addObject("loginUser",loginUser);
	        mav.addObject("datalist",flavor);
	        mav.addObject("id",id);
			mav.setViewName("part");
	        return mav;
	    }
		
		@RequestMapping(value = "/jump",method = RequestMethod.GET)
	    public ModelAndView mention(@RequestParam("id") Integer id
	    		,ModelAndView mav
	    		,@ModelAttribute("formModel")Flavor flavor) {
			
			Flavor ff = repository.findById(id);
			mav.addObject("datalist",ff);
			//de-taはひとつだけだが同じhtmlをつかいたいため
			mav.setViewName("flavor");
	        return mav;
	    }

		@RequestMapping(value = "/write",method = RequestMethod.GET)
	    public ModelAndView reviews(@RequestParam("id") Integer id
	    		,ModelAndView mav
	    		,@ModelAttribute("formModel")Review review) {
			
			
			mav.addObject("id",id);
			mav.setViewName("makeReview");
	        return mav;
	    }
		
		@RequestMapping(value = "/changeReview",method = RequestMethod.GET)
	    public ModelAndView showReviews(ModelAndView mav) {
			
			String name = loginUser.getName();
			List<Review> rev = repository6.findByName(name);
			mav.addObject("datalist",rev);
			mav.setViewName("changeReviews");
	        return mav;
	    }
		
		
		@Transactional
		@RequestMapping(value = "/changingrev",method = RequestMethod.POST)
	    public ModelAndView showRevs(ModelAndView mav,
	    		@RequestParam("id") int[] id) {
			String name = loginUser.getName();
			List<Review> rev = new ArrayList<Review>();
			for(int y : id) {
				repository6.deleteById(y);
				
			}
			List<Review> revw = repository6.findAll();
			mav.addObject("datalist",revw);
			mav.setViewName("changeReviews");
	        return mav;
	    }
		
		@RequestMapping(value = "/register",method = RequestMethod.POST)
	    public ModelAndView reviews(
	    		//@RequestParam("name") String name,
	    		@RequestParam("star") int star,
	    		@RequestParam("review") String reviews,
	    		@RequestParam("date") String date,
	    		@RequestParam("fl") int id,
	    		ModelAndView mav
	    		) {
			List<Review> rev = new ArrayList<Review>();
			//int stars = Integer.parseInt(star);
			MixFlavor part =  repository2.findById(id);
			Review re = new Review();
			re.setName(loginUser.getName());
			re.setDate(date);
			re.setId(loginUser.getId());
			re.setStar(star);
			re.setReview(reviews);
			repository6.saveAndFlush(re);
			rev.add(re);
			
			List<Review> lolo = repository6.findAll();
			
			String k = part.getCons();
			List<Flavor> flavor = new ArrayList<Flavor>();
	
		    List<Review> review = repository6.findAll();
		    
		    if(review == null) {
		    	mav.addObject("msg","表示するレビューが見つかりません");
		    }else {
		    	mav.addObject("reviewlist",review);
		    }
			String[] fruits = k.split(",");
	        for (String fruit : fruits) {
	        	Flavor fl = repository.findByName(fruit);
	        	flavor.add(fl);
	        }
	        
	        mav.addObject("datalist",flavor);
			mav.addObject(rev);
			mav.addObject("mention",part);
			mav.addObject("reviewlist",lolo);
			mav.addObject("loginUser",loginUser);
			mav.setViewName("part");
	        return mav;
	    }

	
}
