package com.global.coffeeshop.controller.dto.response;

public class QueueDetailsDto {

    public QueueDetailsDto(Long queueNo, Long position) {
        this.queueNo = queueNo;
        this.position = position;
    }

    private Long queueNo;
    private Long position;

    public Long getQueueNo() {
        return queueNo;
    }

    public void setQueueNo(Long queueNo) {
        this.queueNo = queueNo;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }
}
