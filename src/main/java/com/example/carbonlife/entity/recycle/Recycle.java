package com.example.carbonlife.entity.recycle;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("cycle_record")
@NoArgsConstructor
@AllArgsConstructor
public class Recycle {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer type;

    private String weight;

    @TableField("cycle_address_id")
    private Integer cycleAddressId;

    @TableField("cycle_date")
    private String cycleDate;

    @TableField("cycle_reward")
    private Integer cycleReward;

    @TableField("user_id")
    private Integer userId;

    @TableField("is_access")
    private Integer isAccess;
}
