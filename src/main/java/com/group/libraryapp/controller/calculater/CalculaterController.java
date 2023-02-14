package com.group.libraryapp.controller.calculater;


import com.group.libraryapp.dto.calulator.request.CalculatorAddRequest;
import com.group.libraryapp.dto.calulator.request.CalculatorMultiplyRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController // 클래스를 REST API의 진입지점으로 만들어 주기 위함
public class CalculaterController {

    @GetMapping("/add") //GET /add  -> API 명세

    public int addTwoNumbers(CalculatorAddRequest request){
        return request.getNumber1() + request.getNumber2();
    }

    @PostMapping("/multiply")  //POST /multiply
    public int multiplyTwoNumbers(@RequestBody CalculatorMultiplyRequest request){
        return request.getNumber1() * request.getNumber2();
    }
}
