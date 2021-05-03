package online.diagnosticit.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.diagnosticit.course.entities.Category;
import online.diagnosticit.course.repositories.CategoryRepository;

//aqui é feito o registro da clasa como um componente do spring boot (@Component/Services, ou Repository, etc)
//Como Service é um componente fica semanticamente melhor.
@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	public List<Category> findAll(){
		return repository.findAll();
	}
	
	public Category findById(Long id) {
		Optional<Category> obj =  repository.findById(id);
		return obj.get();
	}
}
