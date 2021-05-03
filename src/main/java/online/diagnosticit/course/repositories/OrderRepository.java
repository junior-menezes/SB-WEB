package online.diagnosticit.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import online.diagnosticit.course.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
	// esse tipo de interface que extends JpaRepository não precisa criar uma classe para implementar ela.
	//portanto ela fica vazia.
	
}
