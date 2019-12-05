package com.upsoft.environmentmonitoring.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.upsoft.environmentmonitoring.constant.Constants;
import com.upsoft.environmentmonitoring.constant.HourDayType;
import com.upsoft.environmentmonitoring.domain.po.CityAqi;
import com.upsoft.environmentmonitoring.domain.vo.CityAqiVO;
import com.upsoft.environmentmonitoring.domain.vo.Factor;
import com.upsoft.environmentmonitoring.service.CityAqiHourDayService;
import com.upsoft.environmentmonitoring.utils.DateUtil;
import com.upsoft.environmentmonitoring.utils.StringUtil;

/**
 * @ClassName CityAqiHourDayServiceImpl
 * @Description CityAqiHourDayServiceImpl 适配器 接口
 * @Author Wang wanqian
 * @Date 2019-12-03
 * @Version 1.0
 */
@Service("cityAqiHourDayServiceImpl")
public class CityAqiHourDayServiceImpl extends CityAqiServiceImpl implements CityAqiHourDayService{

	
	/**
	 * @Author Wang wanqian
	 * @Description 适配器的方法
	 * @Date 2019/12/03
	 * @Params []
	 * @return 
	 **/
	@Override
	public List<CityAqiVO> getCityAqiHourDay(CityAqi cityAqi) {
		List<CityAqi> list=super.getProblemByOperation(cityAqi);
		List<CityAqiVO> listVo=null;
		if(null==cityAqi.getCityAqiType()||cityAqi.getCityAqiType().equals(HourDayType.H.getValue())){
			// 返回24小时制的数据
			listVo=new ArrayList<CityAqiVO>(Constants.HOUR_24);
			for(int h=0;h<Constants.HOUR_24;h++){
				listVo.add(h,new CityAqiVO().setFactor(new Factor().setAqi(Constants.FACTOR_VALUE)
						.setCo(Constants.FACTOR_VALUE)
						.setNo2(Constants.FACTOR_VALUE)
						.setO3(Constants.FACTOR_VALUE)
						.setPm10(Constants.FACTOR_VALUE)
						.setPm25(Constants.FACTOR_VALUE)
						.setSo2(Constants.FACTOR_VALUE))
						.setPublishDateTime(DateUtil.addHourValue(h)));
			}
			setFactorValue(list,listVo,Constants.HOUR_24);
		}else if(null!=cityAqi.getCityAqiType()&&cityAqi.getCityAqiType().equals(HourDayType.D.getValue())){
			// 返回30天制的数据
			listVo=new ArrayList<CityAqiVO>(Constants.DAY_30);
			for(int d=0;d<Constants.DAY_30;d++){
				listVo.add(d,new CityAqiVO().setFactor(new Factor().setAqi(Constants.FACTOR_VALUE)
						.setCo(Constants.FACTOR_VALUE)
						.setNo2(Constants.FACTOR_VALUE)
						.setO3(Constants.FACTOR_VALUE)
						.setPm10(Constants.FACTOR_VALUE)
						.setPm25(Constants.FACTOR_VALUE)
						.setSo2(Constants.FACTOR_VALUE))
						.setPublishDateTime(DateUtil.addDayValue(d)));
			}
			setFactorValue(list,listVo,Constants.DAY_30);
		}
		return listVo;
	}
	
	/**
	 * @Author Wang wanqian
	 * @Description 处理数据-给对象设置值
	 * @Date 16:13 2019/12/03
	 * @Params [list,listVo,hourday]
	 * @return void
	 **/
	private void setFactorValue(List<CityAqi> list, List<CityAqiVO> listVo, Integer hourday) {
		if(null!=list&&list.size()>0){
			for(int g=0;g<hourday;g++){
				for(CityAqi ca:list){
					if(null!=ca.getPublishDateTime()&&DateUtil.isSameTime(listVo.get(g).getPublishDateTime(), ca.getPublishDateTime())){
						Factor factor=new Factor();
						factor.setAqi(StringUtil.valueToString(ca.getAqi())).setCo(StringUtil.valueToString(ca.getCo()))
						.setNo2(StringUtil.valueToString(ca.getNo2())).setO3(StringUtil.valueToString(ca.getO3()))
						.setPm10(StringUtil.valueToString(ca.getPm10())).setPm25(StringUtil.valueToString(ca.getPm25()))
						.setSo2(StringUtil.valueToString(ca.getSo2()));
						if(hourday.equals(Constants.HOUR_24)){
							listVo.set(g, new CityAqiVO().setFactor(factor).setPublishDateTime(DateUtil.addHourValue(g)));
						}else if(hourday.equals(Constants.DAY_30)){
							listVo.set(g, new CityAqiVO().setFactor(factor).setPublishDateTime(DateUtil.addDayValue(g)));
						}
						
					}
				}
			}
		}
	}
}
