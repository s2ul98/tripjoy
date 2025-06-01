package com.example.demo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.ReservationDto;
import com.example.demo.dto.ReservationRequestDto;
import com.example.demo.dto.ReservationResponseDto;
import com.example.demo.service.ReservationService;

@Controller
public class HomeController {
	
	@Autowired
	ReservationService reservationService;
	
	@GetMapping("/")
    public String home() {
        
        return "main";
    }
	
	@GetMapping("/sub_1")
    public String sub1() {
        return "sub_1";
    }

    @GetMapping("/sub_1_1")
    public String sub1_1() {
        return "sub_1_1";
    }

    @GetMapping("/sub_2")
    public String sub2() {
        return "sub_2";
    }

//    @GetMapping("/sub_3")
//    public String sub3() {
//        return "sub_3";
//    }
    
    @GetMapping("/reservation")
    public String reservation(Model model) {
        ReservationDto dto = ReservationDto.builder()
            .id("R-0001")
            .userId("u123")
            .activityDate("2025-06-02")
            .createdDate("2025-06-01")
            .status("예약완료")
            .name("이슬")
            .phone("010-1234-5678")
            .build();

        model.addAttribute("dto", dto);
        return "reservation";
    }
    
    @GetMapping("/reservation/{id}")
    public String reservationDetail(@PathVariable("id") String id, Model model) {
    	
        ReservationResponseDto dto = reservationService.read(id);
        
        if (dto == null) {

            return "redirect:/reservation_list";
            
        }
        
        model.addAttribute("dto", dto);
        return "reservation";
    }



    
    @GetMapping("/reservation_list")
    public String reservationList(Model model, Principal principal) {
        List<ReservationResponseDto> reservations = reservationService.findByUserId(principal.getName());
        model.addAttribute("reservations", reservations);
        return "reservation_list";
    }

    
    @PostMapping("/reservation")
    public String createReservation(ReservationRequestDto dto, Principal principal) {
        if (principal == null) {
           
            return "redirect:/login";
        }
        
        String userId = principal.getName();
        System.out.println("예약하기 - 로그인 유저 ID: " + userId);
        
        dto.setUserId(principal.getName());
        reservationService.register(dto);
        
        return "redirect:/reservation_list";
    }

    



}
