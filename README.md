#### 1. Tomcat 서버를 시작할 때 웹 애플리케이션이 초기화하는 과정을 설명하라.
* 서버가 실행되면 추가된 라이브러리들을 참조하며 java class를 이용해 컴파일이 됩니다. 서버가 실행되면 ContextLoaderListener와 DispatcherServlet에서 두 가지 초기화가 진행됩니다. 
* ContextLoaderListener는 Servlet이 기본적으로 가지고 있는 ServletContextListener의 인터페이스를 구현해서 만들었기 때문에 Servlet이 뜰 때 contextInitialized 함수를 실행합니다. contextInitialized 함수에서는 본 프로젝트에 사용된 h2 데이터 베이스가 초기화 됩니다. 
* DispatcherServlet에서는 어노테이션 옵션인 'loadOnStartup = 1'에 의해 서버가 실행될 때 DispatcherServlet에서 코드를 한 번 실행합니다. 여기서 하는 주요 작업은 RequestMapping을 초기화 하는 일입니다.
* 위 두가지 작업이 모두 끝나면 "Completed Load ServletContext!", "Initialized Request Mapping!" 이라는 로그를 확인할 수 있습니다.

#### 2. Tomcat 서버를 시작한 후 http://localhost:8080으로 접근시 호출 순서 및 흐름을 설명하라.
* 브라우져에서 http://localhost:8080을 입력하면 로컬 포트 8080으로 연결을 요청합니다. 톰캣은 welcome-file이 설정되어 있지 않으므로 디폴트인 index.jsp로 연결합니다. index.jsp 파일을 보면 /list.next로 리다이렉트 하는 것을 확인할 수 있습니다. 사용자가 리다이렉트에 의해 다시 /list.next로 연결할 경우 DispatcherServlet으로 요청이 전해지고 DispatcherServlet에 있는 service 함수에서 RequestMapping에 의해 ListController가 선택되어 ModelAndView를 작성합니다. 이렇게 하면 우리가 보는 첫 화면을 출력하게 됩니다.

#### 7. ListController와 ShowController가 멀티 쓰레드 상황에서 문제가 발생하는 이유에 대해 설명하라.
* Dao가 Controller 단위에서 New 되기 때문에 임계구역(Critical Section) 문제가 발생할 가능성이 있습니다. 따라서 함수 단위로 집어 넣어서 필요할 때마다 Dao 객체를 새롭게 만들거나, Dao를  static으로 이용해서 한 번에 하나의 DB 작업을 하도록 해야 합니다.

