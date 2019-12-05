package com.upsoft.environmentmonitoring.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.upsoft.environmentmonitoring.constant.Constants;
import com.upsoft.environmentmonitoring.domain.po.StationAqi;
import com.upsoft.environmentmonitoring.domain.vo.StationAqiVO;
import com.upsoft.environmentmonitoring.domain.vo.StationPlayAqi;
import com.upsoft.environmentmonitoring.domain.vo.StationPlayAqiVO;
import com.upsoft.environmentmonitoring.service.StationPlayAqiService;
import com.upsoft.environmentmonitoring.utils.DateUtil;

/**
 * @ClassName StationPlayAqiServiceImpl
 * @Description StationPlayAqiServiceImpl 适配器 接口
 * @Author Wang wanqian
 * @Date 2019-11-01
 * @Version 1.0
 */
@Service("stationPlayAqiServiceImpl")
public class StationPlayAqiServiceImpl extends StationAqiServiceImpl implements StationPlayAqiService{

	
	/**
	 * @Author Wang wanqian
	 * @Description 适配器的方法
	 * @Date 2019/11/22
	 * @Params []
	 * @return 
	 **/
	@Override
	public List<StationPlayAqiVO> request(StationAqi stationAqi) {
		List<StationPlayAqiVO> list=new ArrayList<StationPlayAqiVO>(Constants.HOUR_24);
		for(int h=0;h<Constants.HOUR_24;h++){
			list.add(h, new StationPlayAqiVO().setTime(DateUtil.addHourValue(h)).setValue(new ArrayList<StationPlayAqi>()));
		}
		List<StationAqiVO> listStation=super.getStationAqiByOperation(stationAqi);
		if(null!=listStation&&listStation.size()>0){
				for(int g=0;g<Constants.HOUR_24;g++){
					StationPlayAqiVO spav=new StationPlayAqiVO();
					List<StationPlayAqi> value=new ArrayList<StationPlayAqi>();
						for(StationAqiVO stav:listStation){
							if(null!=stav.getPublishDateTime()&&DateUtil.isSameTime(list.get(g).getTime(),stav.getPublishDateTime())){
								StationPlayAqi spa=new StationPlayAqi();
								spa.setAirVal(stav.getAirVal())
								.setGeo(stav.getGeo())
								.setId(stav.getId())
								.setStationCode(stav.getStationCode())
								.setStationName(stav.getStationName())
								.setStationType(stav.getStationType());
								value.add(spa);
							}
						}
					spav.setTime(DateUtil.addHourValue(g));
					spav.setValue(value);
					list.set(g,spav);
				}
		}
		return list;
	}

}
