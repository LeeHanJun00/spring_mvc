package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {

  @Override
  public ModelView process(Map<String, String> paramMap) {
    // 논리적인 이름만 넣어 나중에 ViewResolver 에서 처리한다.
    return new ModelView("new-form");
  }
}
