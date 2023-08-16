package productcrudapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import productcrudapp.dao.Productdao;
import productcrudapp.model.Product;

@Controller
public class MainController {
	@Autowired
	private Productdao productDao; 
	
	@RequestMapping("/home")
	public String home(Model m) {
		List<Product> products = productDao.getProducts();
		m.addAttribute("product", products);
		System.out.println("This is home url");
		
		  return "home";
	}
	//show title of  add-product form
	@RequestMapping("/add-product")
	public String addProduct(Model m) {
		m.addAttribute("title", "Add Product");
		System.out.println("This is Add product  url");
		
		  return "add_product_form";
	}
	
	//handle Add product form
	
	@RequestMapping(value="/handle-product", method = RequestMethod.POST)
	public RedirectView handleProduct (@ModelAttribute Product product, HttpServletRequest request) {
		System.out.println("Product added");
		System.out.println(product);
		
		productDao.createProduct(product);
		
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath() + "/");
		return redirectView;
	}
	//Delete Handler
	@RequestMapping(value="/delete/{productId}")
	public RedirectView handleProduct (@PathVariable("productId") int productId, HttpServletRequest request) {
		System.out.println("Product Deleted");
		
		this.productDao.deleteProduct(productId);
		
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath() + "/");
		return redirectView;
	}
	//Update Handler
	@RequestMapping(value="/update/{productId}")
	public String updateForm (@PathVariable("productId") int pid, Model model) {
		System.out.println("Product Update Url");
		Product product = this.productDao.getProduct(pid);
		model.addAttribute("product", product);
		return "update_form";
	}
}
