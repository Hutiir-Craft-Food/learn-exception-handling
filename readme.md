# Exception Handling in SpringBoot

## References:
- https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/servlet/mvc/method/annotation/ResponseEntityExceptionHandler.html
- https://docs.spring.io/spring-security/reference/servlet/authorization/authorize-http-requests.html
- https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc
- https://medium.com/@farzinpashaeee/8e1921e6c2a2
- https://medium.com/@seonggil/e7c95216da8d
- https://medium.com/@ksaquib/2491b43a841b
- https://www.baeldung.com/spring-response-status-exception
- https://www.baeldung.com/exception-handling-for-rest-with-spring
- https://www.baeldung.com/global-error-handler-in-a-spring-rest-api
- https://auth0.com/blog/get-started-with-custom-error-handling-in-spring-boot-java
- https://stackoverflow.com/questions/26236811/

## More Comprehensive Examples:
- https://github.com/paulc4/mvc-exceptions

## DefaultErrorAttributes:
groupId: org.springframework.boot
artifactId: spring-boot
package: org.springframework.boot.web.servlet.error
reference: [DefaultErrorAttributes](https://docs.spring.io/spring-boot/api/java/org/springframework/boot/web/servlet/error/DefaultErrorAttributes.html)

Default implementation of ErrorAttributes. Provides the following attributes when possible:
- timestamp - The time that the errors were extracted
- status - The status code
- error - The error reason
- exception - The class name of the root exception (if configured)
- message - The exception message (if configured)
- errors - Any ObjectErrors from a BindingResult or MethodValidationResult exception (if configured)
- trace - The exception stack trace (if configured)
- path - The URL path when the exception was raised