#### 1. Tomcat 서버를 시작할 때 웹 애플리케이션이 초기화하는 과정을 설명하라.
* 서버가 실행되면 추가된 라이브러리들을 참조하며 java class를 이용해 컴파일이 됩니다. 서버가 실행되면 ContextLoaderListener와 DispatcherServlet에서 두 가지 초기화가 진행됩니다. 
* ContextLoaderListener는 Servlet이 기본적으로 가지고 있는 ServletContextListener의 인터페이스를 구현해서 만들었기 때문에 Servlet이 뜰 때 contextInitialized 함수를 실행합니다. contextInitialized 함수에서는 본 프로젝트에 사용된 h2 데이터 베이스가 초기화 됩니다. 
* DispatcherServlet에서는 어노테이션 옵션인 'loadOnStartup = 1'에 의해 서버가 실행될 때 DispatcherServlet에서 코드를 한 번 실행합니다. 여기서 하는 주요 작업은 RequestMapping을 초기화 하는 일입니다.
* 위 두가지 작업이 모두 끝나면 "Completed Load ServletContext!", "Initialized Request Mapping!" 이라는 로그를 확인할 수 있습니다.

#### 2. Tomcat 서버를 시작한 후 http://localhost:8080으로 접근시 호출 순서 및 흐름을 설명하라.
* 

#### 7. ListController와 ShowController가 멀티 쓰레드 상황에서 문제가 발생하는 이유에 대해 설명하라.
* 

