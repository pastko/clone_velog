package com.gteam.glog.common.utils;


import com.gteam.glog.domain.dto.ResponseDTO;
import com.gteam.glog.domain.enums.ErrorCode;
import com.gteam.glog.domain.enums.ResponseStatusCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ResponseDTOUtils {

    /**
     * Generater ResponseDTO
     *
     * @param data - Response Data
     * @return - ResponseEntity
     */
    public ResponseEntity<?> doGenerateResponseDTO(Object data) {
        ResponseDTO responseDTO = ResponseDTO
                .builder()
                .status(202)
                .data(data)
                .msg(ResponseStatusCode.OK.getMsg())
                .build();

        log.info(responseDTO.toString());
        return doGenerateResponseEntity(responseDTO);
    }

    /**
     *
     * @param data
     * @param responseStatusCode
     * @return
     */
    public ResponseEntity<?> doGenerateResponseDTO(Object data,ResponseStatusCode responseStatusCode) {
        ResponseDTO responseDTO = ResponseDTO
                .builder()
                .status(responseStatusCode.get())
                .data(data)
                .msg(ResponseStatusCode.OK.getMsg())
                .build();

        log.info(responseDTO.toString());
        return doGenerateResponseEntity(responseDTO);
    }

    /**
     *
     * @param data
     * @param loginErrorCode
     * @return
     */
    public ResponseEntity<?> doGenerateResponseDTO(Object data, ErrorCode loginErrorCode) {
        ResponseDTO responseDTO = ResponseDTO
                .builder()
                .status(loginErrorCode.getStatus())
                .data(data)
                .msg(loginErrorCode.getDescription())
                .build();

        log.info(responseDTO.toString());
        return doGenerateResponseEntity(responseDTO);
    }


    private ResponseEntity<?> doGenerateResponseEntity(ResponseDTO responseDTO) {
        return ResponseEntity
                .status(responseDTO.getStatus()).header("Content-Type", "application/json")
                .body(responseDTO);
    }
}
