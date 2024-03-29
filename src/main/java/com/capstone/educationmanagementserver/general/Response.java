package com.capstone.educationmanagementserver.general;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response<T> {

    private Status status;
    private T payload;
    private Object errors;
    private Object metadata;

    public static <T> Response<T> badRequest(HttpServletResponse resp) {
    	resp.setStatus(HttpStatus.SC_BAD_REQUEST);
        Response<T> response = new Response<>();
        response.setStatus(Status.BAD_REQUEST);
        return response;
    }
    
    public static <T> Response<T> internalError(HttpServletResponse resp) {
    	resp.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        Response<T> response = new Response<>();
        response.setStatus(Status.INTERNAL_SERVER_ERROR);
        return response;
    }

    public static <T> Response<T> ok() {
        Response<T> response = new Response<>();
        response.setStatus(Status.OK);
        return response;
    }

    public static <T> Response<T> unauthorized(HttpServletResponse resp) {
        Response<T> response = new Response<>();
        response.setStatus(Status.UNAUTHORIZED);
        return response;
    }

    public static <T> Response<T> validationException() {
        Response<T> response = new Response<>();
        response.setStatus(Status.VALIDATION_EXCEPTION);
        return response;
    }

    public static <T> Response<T> wrongCredentials() {
        Response<T> response = new Response<>();
        response.setStatus(Status.WRONG_CREDENTIALS);
        return response;
    }

    public static <T> Response<T> accessDenied() {
        Response<T> response = new Response<>();
        response.setStatus(Status.ACCESS_DENIED);
        return response;
    }

    public static <T> Response<T> exception() {
        Response<T> response = new Response<>();
        response.setStatus(Status.EXCEPTION);
        return response;
    }

    public static <T> Response<T> notFound() {
        Response<T> response = new Response<>();
        response.setStatus(Status.NOT_FOUND);
        return response;
    }

    public static <T> Response<T> duplicateEntity() {
        Response<T> response = new Response<>();
        response.setStatus(Status.DUPLICATE_ENTITY);
        return response;
    }

    public void addErrorMsgToResponse(String errorMsg, Exception ex) {
        ResponseError error = new ResponseError()
                .setDetails(errorMsg)
                .setMessage(ex.getMessage())
                .setTimestamp(DateUtils.today());
        setErrors(error);
    }
    
    public static byte[] restResponseBytes(Response<Object> eErrorResponse) throws IOException {
		String serialized = new ObjectMapper().writeValueAsString(eErrorResponse);
		return serialized.getBytes();
	}
    
    public void responseError(HttpServletResponse resp, int status, Response<Object> response) throws IOException {    	
		byte[] responseToSend = Response.restResponseBytes(response);
		resp.setStatus(status);
		resp.setHeader("Content-Type", "application/json");
		resp.setContentType("application/json");
		resp.getOutputStream().write(responseToSend);	
    }

    public enum Status {
        OK, BAD_REQUEST, UNAUTHORIZED, VALIDATION_EXCEPTION, EXCEPTION, WRONG_CREDENTIALS, ACCESS_DENIED, NOT_FOUND, DUPLICATE_ENTITY, JWT_INVALID_OR_EXPIRED,INTERNAL_SERVER_ERROR
    }

    @Getter
    @Accessors(chain = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PageMetadata {
        private int size;
        private long totalElements;
        private int totalPages;
        private int number;

        public PageMetadata(int size, long totalElements, int totalPages, int number) {
            this.size = size;
            this.totalElements = totalElements;
            this.totalPages = totalPages;
            this.number = number;
        }
    }

}


