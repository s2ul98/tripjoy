package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, String> {

	List<Reservation> findByUser_Id(String userId);

	
}
