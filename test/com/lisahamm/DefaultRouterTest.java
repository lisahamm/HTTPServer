//package com.lisahamm;
//
//import com.lisahamm.application.controllers.Controller;
//import com.lisahamm.mocks.MockHTTPRequest;
//import com.lisahamm.mocks.MockResourceManager;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class DefaultRouterTest {
//
//    @Test
//    public void testItRegistersARoute() throws Exception {
//        DefaultRouter router = new DefaultRouter(new MockResourceManager());
//        MockController controller = new MockController();
//        router.registerRoute("GET", "/form", controller);
//
//        MockHTTPRequest request = new MockHTTPRequest();
//        request.requestMethod = "GET";
//        request.requestURI = "/form";
//
//        router.invoke(request, new ResponseBuilder());
//
//        assertTrue(controller.isExecuted);
//    }
//
//    public class MockController implements Controller {
//        public boolean isExecuted;
//
//        public MockController() {
//            this.isExecuted = false;
//        }
//
//        public boolean shouldExecute(Request request) {
//            return false;
//        }
//
//
//        public void execute(Request request, ResponseBuilder response) {
//            this.isExecuted = true;
//        }
//    }
//}