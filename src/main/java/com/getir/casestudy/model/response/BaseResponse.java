package com.getir.casestudy.model.response;

import com.getir.casestudy.domain.Statistic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BaseResponse<T> {
    private MessageResponse messageResponse;
    private T data;

    public BaseResponse(T data, MessageResponse message) {
        this.data = data;
        this.messageResponse = message;
    }

    public BaseResponse(T data) {
        this.data = data;
    }

    public BaseResponse(MessageResponse message) {
        this.messageResponse = message;
    }
}
