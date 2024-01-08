package fon.mas.novica.Faculty.Project.controller;

import fon.mas.novica.Faculty.Project.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
}
