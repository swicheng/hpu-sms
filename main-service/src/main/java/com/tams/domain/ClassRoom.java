package com.tams.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tams.enums.ClassRoomTypeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

/**
 * @author swiChen
 * @date 2022/1/12
 **/

@Data
@Accessors(chain = true)
@TableName("classroom")
public class ClassRoom implements Serializable {

    @TableId
    private Long roomId;

    private String name;

    @Enumerated(EnumType.STRING)
    private ClassRoomTypeEnum type;

    private Integer capacity;

    private Integer status;

}
