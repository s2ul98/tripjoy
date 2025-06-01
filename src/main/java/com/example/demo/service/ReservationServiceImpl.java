package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ReservationRequestDto;
import com.example.demo.dto.ReservationResponseDto;
import com.example.demo.entity.Company;
import com.example.demo.entity.Reservation;
import com.example.demo.entity.User;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.UserRepository;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyRepository companyRepository;
	
	
	@Override
	public String register(ReservationRequestDto dto) {
	    System.out.println("💡 userId: " + dto.getUserId());
	    System.out.println("💡 companyId: " + dto.getCompanyId());

	    User user = userRepository.findById(dto.getUserId())
	            .orElseThrow(() -> new IllegalArgumentException("User not found"));

	    Company company = companyRepository.findById(dto.getCompanyId())
	            .orElseThrow(() -> new IllegalArgumentException("Company not found"));

	    Reservation entity = dtoToEntity(dto, user, company);

	    reservationRepository.save(entity);

	    return entity.getId();
	}


	@Override
	public Page<ReservationResponseDto> getList(int pageNumber) {
		
		int pageNum = (pageNumber <= 0) ? 0 : pageNumber - 1;
		
        Pageable pageable = PageRequest.of(pageNum, 10, Sort.by("createdDate").descending());
        
        Page<Reservation> entityPage = reservationRepository.findAll(pageable);
        
        Page<ReservationResponseDto> dtoPage = entityPage.map(entity -> entityToDto(entity));
        
        return dtoPage;
	}

	@Override
	public ReservationResponseDto read(String id) {
		
		Optional<Reservation> result = reservationRepository.findById(id);
       
		if (result.isPresent()) {
			
            Reservation entity = result.get();
            ReservationResponseDto dto = entityToDto(entity);
            return dto;
            
        } else {
        	
            return null;
            
        }
	}

	@Override
	public void modify(ReservationRequestDto dto) {
		
		Optional<Reservation> result = reservationRepository.findById(dto.getId());
	        
		if (result.isPresent()) {
			
	            Reservation entity = result.get();
	            entity.setActivityDate(LocalDate.parse(dto.getActivityDate()));
	            reservationRepository.save(entity);
	            
	        }
		
	}

	@Override
	public void remove(String id) {
		
        Optional<Reservation> result = reservationRepository.findById(id);
       
        if (result.isPresent()) {
        	
            reservationRepository.deleteById(id);
        
        }

		
	}

	@Override
	public List<ReservationResponseDto> findByUserId(String userId) {
		   List<Reservation> reservations = reservationRepository.findByUser_Id(userId);

		    return reservations.stream()
		            .map(this::entityToDto)
		            .toList();
	}
	
	
}
