package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.ReservationRequestDto;
import com.example.demo.dto.ReservationResponseDto;
import com.example.demo.service.ReservationService;

@Controller
@RequestMapping("")
public class ReservationController {
	
	@Autowired
    ReservationService reservationService;
	
//	목록
	@GetMapping("/list")
    public void list(@RequestParam(defaultValue = "1", name = "page") int page, Model model) {
        Page<ReservationResponseDto> list = reservationService.getList(page);
        model.addAttribute("list", list);
    }
	
//	예약 페이지 이동
	@GetMapping("/register")
    public void register() {
    }

//	예약 등록
    @PostMapping("/register")
    public String registerPost(ReservationRequestDto dto, RedirectAttributes redirectAttributes) {
        String reservationId = reservationService.register(dto);
        redirectAttributes.addFlashAttribute("msg", reservationId);
        return "redirect:/reservation/list";
    }
    
//  예약 상세 조회
    @GetMapping("/read")
    public void read(@RequestParam(name = "id") String id,
                     @RequestParam(defaultValue = "1", name = "page") int page,
                     Model model) {
        ReservationResponseDto dto = reservationService.read(id);
        model.addAttribute("dto", dto);
        model.addAttribute("page", page);
    }
    
//  예약 수정 페이지 이동
    @GetMapping("/modify")
    public void modify(@RequestParam(name = "id") String id, Model model) {
        ReservationResponseDto dto = reservationService.read(id);
        model.addAttribute("dto", dto);
    }
    
//  예약 수정
    @PostMapping("/modify")
    public String modifyPost(ReservationRequestDto dto, RedirectAttributes redirectAttributes) {
        reservationService.modify(dto);
        redirectAttributes.addAttribute("id", dto.getId());
        return "redirect:/reservation/read";
    }
    
//  예약 삭제
    @PostMapping("/remove")
    public String removePost(@RequestParam("id") String id) {
        reservationService.remove(id);
        return "redirect:/reservation/list";
    }

}
