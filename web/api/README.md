# api

WebMvcConfigurationSupportを使っていると、Jackson2ObjectMapperBuilderのBeanが自動で設定されないので、HttpMessageConverterを自分で設定しないといけない
https://github.com/spring-projects/spring-boot/issues/2116
https://spring.io/blog/2014/12/02/latest-jackson-integration-improvements-in-spring#with-spring-boot
