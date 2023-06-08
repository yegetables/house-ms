package com.xupt.house.dto;

import com.xupt.house.entity.Category;
import lombok.Data;

import java.util.List;

@Data
public class AnalysizesResult {
    public List<Category> categorys;
    public List<Double> scores;
}
