package com.example.d3nserver.common.dto;
import org.springframework.http.ResponseEntity;
import java.io.Serializable;


public record ResponseDto<T>(T data) implements Serializable {
    public static<T> ResponseEntity<T> ok(T data){
        return ResponseEntity.ok(data);
    }
    public static<T> ResponseEntity<T> noContent(){
        return ResponseEntity.noContent().build();
    }
}
