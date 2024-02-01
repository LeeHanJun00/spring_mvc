package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

  @WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
  public class FrontControllerServletV2 extends HttpServlet {

    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    // 서블릿이 생성 될때 매핑정보 담아두기
    public FrontControllerServletV2() {
      controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
      controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
      controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // URL 정보 가져오기
      String requestURI = request.getRequestURI();

      // 해당하는 url 의 키값(해당 컨트롤러 생성자)을 가져옴
      ControllerV2 controller = controllerMap.get(requestURI);

      // 해당 URL 의 값이 없으면 404
      if (controller == null) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return;
      }

      // MyView 객체를 반환한다.
      MyView view = controller.process(request, response);

      view.render(request, response);
    }
  }

