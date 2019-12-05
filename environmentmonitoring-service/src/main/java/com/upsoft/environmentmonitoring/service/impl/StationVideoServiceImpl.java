package com.upsoft.environmentmonitoring.service.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.upsoft.environmentmonitoring.domain.po.StationVideoBean;
import com.upsoft.environmentmonitoring.constant.StationVideo;
import com.upsoft.environmentmonitoring.domain.po.StationAqi;
import com.upsoft.environmentmonitoring.domain.vo.DeviceVO;
import com.upsoft.environmentmonitoring.domain.vo.StationAqiVO;
import com.upsoft.environmentmonitoring.service.StationVideoService;

/**
 * @ClassName StationVideoServiceImpl
 * @Description StationVideoServiceImpl 适配器 接口
 * @Author Wang wanqian
 * @Date 2019-11-29
 * @Version 1.0
 */
@Service("stationVideoServiceImpl")
public class StationVideoServiceImpl extends StationAqiServiceImpl implements StationVideoService{

	
	/**
	 * @Description: 注入stationVideoBean
	 */
	@Autowired
	private StationVideoBean stationVideoBean;
	
	/**
	 * @Description: 注入HttpServletRequest
	 */
	@Autowired
	private HttpServletRequest httpServletRequest;
	
	/**
	 * @Description: 从application.yml中读取视频平台url
	 */
	@Value("${video_platform.url}")
	private String videoPlatformUrl;

	/**
	 * @Description: 从application.yml中读取视频平台登录用户名
	 */
	@Value("${video_platform.username}")
	private String username;

	/**
	 * @Description: 从application.yml中读取视频平台登录用户密码
	 */
	@Value("${video_platform.password}")
	private String password;
	
	/**
	 * @Author Wang wanqian
	 * @Description 为返回信息添加videoId
	 * @Date 2019/11/29
	 * @Params [stationAqi]
	 * @return List<StationAqiVO>
	 **/
	@Override
	public List<StationAqiVO> getStationVideo(StationAqi stationAqi) {
		List<StationAqiVO> listSVo=super.getStationAqiByOperation(stationAqi);
		List<DeviceVO> listDVo=new ArrayList<DeviceVO>();
		try {
			listDVo = getDevices(listDVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(null!=listSVo&&null!=listDVo){
			for(DeviceVO dv:listDVo){
				for(StationAqiVO sv:listSVo){
					// 这里不需要映射关系
					/*if(sv.getStationName().contains(dv.getTitle())){
						sv.setVideoId(dv.getChanid());
					}*/
					
					// 这里需要映射关系支持
					String sName=getMapsStationName(dv.getTitle());
					if(sName.equals(sv.getStationName())){
						sv.setVideoId(dv.getChanid());
					}
				}
			}
		}
		return listSVo;
	}

	/**
	 * @Author Wang wanqian
	 * @Description 解决不支持中文的key问题（需要改呀）
	 * @Date 2019/12/02
	 * @Params title
	 * @return String
	 **/
	private String getMapsStationName(String title) {
		Map<String,String> map=new HashMap<String,String>();
		if(StationVideo.STATION_JINKE.getVideoName().equals(title)){
			String a=stationVideoBean.getMaps().get(StationVideo.STATION_JINKE.getStationName());
			String[] b=a.split("&");
			map.put(b[0], b[1]);
		}else if(StationVideo.STATION_MINJIE.getVideoName().equals(title)){
			String a=stationVideoBean.getMaps().get(StationVideo.STATION_MINJIE.getStationName());
			String[] b=a.split("&");
			map.put(b[0], b[1]);
		}else if(StationVideo.STATION_BANGTAI.getVideoName().equals(title)){
			String a=stationVideoBean.getMaps().get(StationVideo.STATION_BANGTAI.getStationName());
			String[] b=a.split("&");
			map.put(b[0], b[1]);
		}
		return map.get(title);
	}

	/**
	 * @Author Wang wanqian
	 * @Description 登录视频平台
	 * @Date 2019/11/29
	 * @Params 
	 * @return String
	 * @throws Exception 
	 **/
	private String login() throws Exception {
		JSONObject json = new JSONObject();
		json.put("user",username);
		json.put("passw",password);
		json.put("method","login");
		String params = json.toString();
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost();
		httpPost.setURI(new URI(videoPlatformUrl));
		StringEntity httpEntity = new StringEntity(params, ContentType.APPLICATION_JSON);
		httpPost.setEntity(httpEntity);
		CloseableHttpResponse response =  client.execute(httpPost);
		String resStr = getResultString(response.getEntity().getContent());
		JSONObject jsonObject = JSONObject.parseObject(resStr);
		if(jsonObject.getIntValue("retcode") == 0) {
			return jsonObject.getString("sessionid");
		}
		return "";
	}

	/**
	 * @Author Wang wanqian 
	 * @Description 获取视频数据
	 * @Date 2019/11/21
	 * @Params TODO
	 * @return BaseResult
	 * @throws Exception 
	 **/
	private List<DeviceVO> getDevices(List<DeviceVO> node) throws Exception {
		String sessionId = login();
		JSONObject json = new JSONObject();
		json.put("sessionid",sessionId);
		json.put("method","get_all_node");
		String params = json.toString();
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost();
		httpPost.setURI(new URI(videoPlatformUrl));
		HttpEntity httpEntity = new StringEntity(params, ContentType.APPLICATION_JSON);
		httpPost.setEntity(httpEntity);
		CloseableHttpResponse response =  client.execute(httpPost);
		String resStr = getResultString(response.getEntity().getContent());
		node=JSONObject.parseArray(JSONObject.parseObject(resStr).getString("node"), DeviceVO.class);
		return node;
	}
	
	/**
	 * @Author weiwei
	 * @Description 解析视频平台接口返回的数据
	 * @Date 2019/11/21
	 * @Params TODO
	 * @return String
	 **/
	public static String getResultString(InputStream ins) throws Exception {
		String resStr;
		BufferedReader in = new BufferedReader(new InputStreamReader(ins,"utf-8"));
		StringBuffer sb = new StringBuffer();
		String line = null;
		String NL = System.getProperty("line.separator");
		while ((line = in.readLine()) != null) {
			sb.append(line + NL);
		}
		in.close();
		resStr = sb.toString();
		return resStr;
	}

}
