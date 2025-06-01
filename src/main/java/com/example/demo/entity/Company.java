package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name="table_company")
@Builder
public class Company {
	
	@Id
	@Column(length = 50)
	String id;
	
	@Column(length = 100)
	String name;
	
	@Column(length = 255)
	String description;
	
	@Column(length = 255)
	String location;
	
	@Column(length = 20)
	String phone;
	

}
