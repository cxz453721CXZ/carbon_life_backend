package com.example.carbonlife.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.carbonlife.entity.Questionnaire;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SubmitQuestionMapper extends BaseMapper<Questionnaire> {
}
