package hello.servlet.basic.web.frontcontroller.v5.adapter;

import hello.servlet.basic.web.frontcontroller.ModelView;
import hello.servlet.basic.web.frontcontroller.v4.ControllerV4;
import hello.servlet.basic.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException {
        ControllerV4 controller = (ControllerV4) handler;

        // request parameter를 모두 hashmap에 담는다.
        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();

        // 이 handle()이 호출 되었을 때는, handler가 무조건 controllerV4의 인스턴스 이므로 힙에 할당된 오버라이딩 process 메서드를 호출한다.(구현된 메서드)
        // 그럼 요청 url에 해당하는 인스턴스에 맞는 컨트롤러의 프로세스가 호출된다.
        String viewName = controller.process(paramMap, model);

        ModelView mv = new ModelView(viewName);
        mv.setModel(model);

        return mv;
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
