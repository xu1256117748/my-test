package com.xyk.TestExample;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.math3.stat.descriptive.summary.Sum;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * @author 徐亚奎
 * @date 2021-10-17 15:37
 * @Description
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Stu {
    private Integer chineseScore;
    private Integer mathScore;
    private Integer englishScore;
    private Integer sum;
    private Double average;
    public Stu(Integer chineseScore,Integer mathScore,Integer englishScore){
        this.chineseScore = chineseScore;
        this.mathScore = mathScore;
        this.englishScore = englishScore;
        sum();
        average();

    }
    public Integer sum(){
        sum = chineseScore+mathScore+englishScore;
        return sum;
    }
    public Double average(){
        average = BigDecimal.valueOf(sum)
                            .divide(BigDecimal.valueOf(3),2, RoundingMode.HALF_UP)
                            .doubleValue();
        return average;
    }
}
