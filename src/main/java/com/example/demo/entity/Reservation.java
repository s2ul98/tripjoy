package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name="table_reservation")
@Builder
public class Reservation {
	
	@Id
	@Column(length = 50)
	String id;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
	
	@Column(name = "activity_date")
	LocalDate activityDate; // 방문할 날짜
	
	@Column(name = "created_date")
	LocalDate createdDate; // 예약 날짜
	
	@Column(length = 20)
	String status;
	

}
