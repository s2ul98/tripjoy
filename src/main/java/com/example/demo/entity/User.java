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
@Table(name="table_user")
@Builder
public class User {
	
	@Id
	@Column(length = 50)
	String id;
	
	@Column(name = "user_name", length = 50)
	String userName;
	
	@Column(length = 100)
	String password;
	
	@Column(length = 20)
	String phone;
	
	@Column(name = "birth_date", length = 20)
	String birthDate;
	
	@Column(length = 50)
	String nickname;

}
