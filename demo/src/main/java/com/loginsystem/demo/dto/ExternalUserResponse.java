package com.loginsystem.demo.dto;

import com.loginsystem.demo.enums.OperationResult;
import lombok.Data;

//對 controller 回傳 資料與處理狀態
@Data
public class ExternalUserResponse<T> {

   private T returnData;

   private OperationResult operateStatus;

}
