package com.example.demo.service;

import org.springframework.data.domain.Page;

import com.example.demo.dto.ReservationRequestDto;
import com.example.demo.dto.ReservationResponseDto;
import com.example.demo.entity.Company;
import com.example.demo.entity.Reservation;
import com.example.demo.entity.User;

public interface ReservationService {

	// 예약 생성
	String register(ReservationRequestDto dto);
	
	//	예약 목록
	Page<ReservationResponseDto> getList(int pageNumber);
	
	// 예약 상세 조회
	ReservationResponseDto read(String id);
	
	//	예약 수정
	void modify(ReservationRequestDto dto);
	 
	// 예약 삭제
	void remove(String id);
	
	default Reservation dtoToEntity(ReservationRequestDto dto, User user, Company company) {
		Reservation entity = Reservation.builder()
                .id(java.util.UUID.randomUUID().toString())
                .user(user)
                .company(company)
                .activityDate(java.time.LocalDate.parse(dto.getActivityDate()))
                .createdDate(java.time.LocalDate.now())
                .status("예약완료")
                .build();
		return entity;
    }
	
    default ReservationResponseDto entityToDto(Reservation entity) {
    	ReservationResponseDto dto = ReservationResponseDto.builder()
                .id(entity.getId())
                .userId(entity.getUser().getId())
                .companyId(entity.getCompany().getId())
                .activityDate(entity.getActivityDate().toString())
                .createdDate(entity.getCreatedDate().toString())
                .status(entity.getStatus())
                .build();
        return dto;
    }
	

}
