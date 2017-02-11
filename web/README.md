# spring-webmvc

Spring MVCは「FrontControllerパターン」を採用している

FrontControllerはDispatcherServletクラスとして実装されている
https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/java/org/springframework/web/servlet/DispatcherServlet.java

クライアントからのリクエストをFrontControllerが受け取り、リクエストの内容に応じて実行するHandlerを選択する

HandlerMappingが、クライアントからのリクエストをもとに、どのControllerを実行するかを決定する
https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/java/org/springframework/web/servlet/HandlerMapping.java

