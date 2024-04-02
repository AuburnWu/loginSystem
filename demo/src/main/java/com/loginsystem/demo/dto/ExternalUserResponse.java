package com.loginsystem.demo.dto;

import com.loginsystem.demo.enums.OperationResult;
import lombok.Data;

@Data
public class ExternalUserResponse<T> {

   private T returnData;

   private OperationResult operateStatus;

}
