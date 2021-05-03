package online.diagnosticit.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.diagnosticit.course.entities.Product;
import online.diagnosticit.course.repositories.ProductRepository;

//aqui é feito o registro da clasa como um componente do spring boot (@Component/Services, ou Repository, etc)
//Como Service é um componente fica semanticamente melhor.
@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	public List<Product> findAll(){
		return repository.findAll();
	}
	
	public Product findById(Long id) {
		Optional<Product> obj =  repository.findById(id);
		return obj.get();
	}
}
