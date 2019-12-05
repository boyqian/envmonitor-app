package com.upsoft.environmentmonitoring.domain.vo;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName StationRaitoVO
 * @Description 统计分析-站点比对返回信息
 * @Author Wang wanqian
 * @Date 2019-11-19
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class StationRatioVO {

	/**
     * @Description: stationName
     */
	private String stationName;
	
    /**
     * @Description: so2
     */
    private List<RatioVo> factor;
}
