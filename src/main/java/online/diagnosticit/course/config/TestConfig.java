package online.diagnosticit.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import online.diagnosticit.course.entities.Category;
import online.diagnosticit.course.entities.Order;
import online.diagnosticit.course.entities.OrderItem;
import online.diagnosticit.course.entities.Payment;
import online.diagnosticit.course.entities.Product;
import online.diagnosticit.course.entities.User;
import online.diagnosticit.course.entities.enums.OrderStatus;
import online.diagnosticit.course.repositories.CategoryRepository;
import online.diagnosticit.course.repositories.OrderItemRepository;
import online.diagnosticit.course.repositories.OrderRepository;
import online.diagnosticit.course.repositories.ProductRepository;
import online.diagnosticit.course.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	//nessa primeira para inicia-se as injeções de depências com @Autowired. Os Repositories dependem do JpaRepository
	//então o Spring Boot cria essas depências automaticamente.
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public void run(String... args) throws Exception {
		
		//iniciamos algumas variáveis do tipo Categoria para popular o banco
		Category cat1 = new Category(null, "Electronics"); 
		Category cat2 = new Category(null, "Books"); 
		Category cat3 = new Category(null, "Computers"); 
		
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, ""); 
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, ""); 
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, ""); 
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, ""); 
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");

		categoryRepository.saveAll(Arrays.asList(cat1, cat2,cat3));
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		
		//colocar categoria nos produtos
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);
		
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		
		
		
		//para testar iniciamos algumas variáveis do tipo User para popular o banco
		User u1 = new User(null, "Maria Brown",	"maria@gmail.com", "999999999", "123456");
		User u2 = new User(null, "Alex Grenn",	"alex@gmail.com", "999999997", "123456");
		
		//iniciamos algumas variáveis do tipo Oder para popular o banco
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1); 
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WITIN_PAYMENT, u2); 
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WITIN_PAYMENT, u1);
		
		//Para salvar no banco usamos os Repositories para fazer as ações no DB.
		userRepository.saveAll(Arrays.asList(u1, u2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice()); 
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice()); 
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice()); 
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());
		
		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
		
		Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1);
		o1.setPayment(pay1);
		orderRepository.save(o1);
		
		
		Payment pay2 = new Payment(null, Instant.parse("2019-07-22T16:21:22Z"), o3);
		o3.setPayment(pay2);
		orderRepository.save(o3);
	}
}
