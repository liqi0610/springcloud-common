package cn.v5cn.springcloud.gateway.exception;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class CustomExceptionHandler extends DefaultErrorWebExceptionHandler {

    public CustomExceptionHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties, ErrorProperties errorProperties, ApplicationContext applicationContext) {
        super(errorAttributes, resourceProperties, errorProperties, applicationContext);
    }

    @Override
    protected HttpStatus getHttpStatus(Map<String, Object> errorAttributes) {
        HttpStatus status = (HttpStatus) errorAttributes.get("status");
        return HttpStatus.INTERNAL_SERVER_ERROR == status ? HttpStatus.OK : status;
    }

    @Override
    protected Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = new LinkedHashMap<>();
        errorAttributes.put("code", 1000);
        Throwable error = this.getError(request);
        errorAttributes.put("status", this.determineHttpStatus(error));
        errorAttributes.put("message", this.buildMessage(error));
        errorAttributes.put("path", request.path());
        errorAttributes.put("timestamp", new Date());
        return errorAttributes;
    }

    private String buildMessage(Throwable t) {
        return "未知错误！";
    }

    private HttpStatus determineHttpStatus(Throwable error) {
        return error instanceof ResponseStatusException ? ((ResponseStatusException)error).getStatus() : HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
