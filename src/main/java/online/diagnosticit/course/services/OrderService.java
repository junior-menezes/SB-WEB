package online.diagnosticit.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.diagnosticit.course.entities.Order;
import online.diagnosticit.course.repositories.OrderRepository;

//aqui é feito o registro da clasa como um componente do spring boot (@Component/Services, ou Repository, etc)
//Como Service é um componente fica semanticamente melhor.
@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	public List<Order> findAll(){
		return repository.findAll();
	}
	
	public Order findById(Long id) {
		Optional<Order> obj =  repository.findById(id);
		return obj.get();
	}
}
