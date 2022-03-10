package com.gteam.glog.common.utils;



import com.gteam.glog.domain.dto.ResponseDTO;
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
     * @param data  - Response Data
     * @return - ResponseEntity
     */
    public ResponseEntity<?> doGenerateResponseDTO(Object data){
        log.info("Response DTO - {}",data);
        if(data != null) {
            return ResponseEntity
                    .status(ResponseStatusCode.OK.get()).header("Content-Type","application/json")
                    .body(ResponseDTO
                            .builder()
                            .success(true)
                            .data(data)
                            .msg(ResponseStatusCode.OK.getMsg())
                            .build());
        }else{
            return ResponseEntity
                    .status(ResponseStatusCode.BADREQUEST.get()).header("Content-Type","application/json")
                    .body(ResponseDTO
                            .builder()
                            .success(false)
                            .data(null)
                            .msg(ResponseStatusCode.BADREQUEST.getMsg())
                            .build());
        }
    }
}
