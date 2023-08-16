package productcrudapp.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import productcrudapp.model.Product;



@Component
public class Productdao {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	//save product
		@Transactional
		public void createProduct(Product product) {
			//insert Or save
			 this.hibernateTemplate.saveOrUpdate(product);//if its have alredy id then it will simply update otherwise save it
			
		}
		
		//get the single product data
		public Product getProduct (int pid) {
			return this.hibernateTemplate.get(Product.class, pid);
			
		}
		//get all products(all rows)
		public List<Product> getProducts(){
			List<Product> products = this.hibernateTemplate.loadAll(Product.class);
			return products;
		}
		
		//deleting single data
		@Transactional
		public void deleteProduct(int pid) {
			Product p = this.hibernateTemplate.get(Product.class, pid);
			this.hibernateTemplate.delete(p);
		}
		
		//updating data..
		@Transactional
		public void updateStudent(Product product) {
			this.hibernateTemplate.update(product);
		}
}
