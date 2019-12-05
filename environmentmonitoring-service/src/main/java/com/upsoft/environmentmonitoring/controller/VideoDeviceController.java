package com.upsoft.environmentmonitoring.controller;

import com.alibaba.fastjson.JSONObject;
import com.upsoft.environmentmonitoring.annotation.SystemOperationLog;
import com.upsoft.environmentmonitoring.constant.Constants;
import com.upsoft.environmentmonitoring.domain.po.FavoriteDevice;
import com.upsoft.environmentmonitoring.domain.po.User;
import com.upsoft.environmentmonitoring.domain.vo.BaseResult;
import com.upsoft.environmentmonitoring.domain.vo.DeviceVO;
import com.upsoft.environmentmonitoring.service.FavoriteDeviceService;
import com.upsoft.environmentmonitoring.utils.BaseResultUtil;
import com.upsoft.environmentmonitoring.utils.JwtUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName VideoDeviceController
 * @Description 视频设备Restful API接口
 * @Author Wei Wei
 * @Date 2019-11-19
 * @Version 1.0
 */
@RestController
@Api(value = "VideoDevice管理接口")
public class VideoDeviceController {
   /**
	 * @Description: 日志记录器
	 */
	Logger logger = LoggerFactory.getLogger(getClass());

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
	 * @Description: 注入HttpServletRequest
	 */
	@Autowired
	private HttpServletRequest httpServletRequest;
	
	/**
	 * @Description: 注入
	 */
	@Autowired
	private FavoriteDeviceService favoriteDeviceService; 
	
	
	/**
	 * @Author weiwei
	 * @Description 登录视频平台
	 * @Date 2019/11/21
	 * @Params TODO
	 * @return BaseResult
	 **/
	@PostMapping("/videoPlatform/login")
	@ApiOperation(value = "视频平台登录")
	@SystemOperationLog(description = "视频平台登录", operationType = "查询")
	public BaseResult login() throws Exception {
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
			String sessionId = jsonObject.getString("sessionid");
			return BaseResultUtil.success(sessionId);
		}
		return BaseResultUtil.error(Constants.LOGIN_FAIL_CODE, "视频平台登录失败！");
	}

	/**
	 * @Author weiwei
	 * @Description 查询视频设备列表
	 * @Date 2019/11/21
	 * @Params TODO
	 * @return BaseResult
	 **/
	@PostMapping("/videoPlatform/getDeviceList")
	@ApiOperation(value = "视频设备列表查询")
	@SystemOperationLog(description = "视频设备列表查询", operationType = "查询")
	public BaseResult getDevices() throws Exception{
		String token = httpServletRequest.getHeader("token");
		User user = JwtUtil.getClaimsValue(token, "user", User.class);
		BaseResult br = login();
		String sessionId = br.getData().toString();
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
		return BaseResultUtil.success(IntegrationDevice(user,JSONObject.parseObject(resStr)));
	}

	/**
	 * @Author Wang wanqian
	 * @Description 查询视频设备列表,并新增返回信息中是否有收藏视频
	 * @Date 2019/11/21
	 * @Params TODO
	 * @return BaseResult
	 **/
	private List<DeviceVO> IntegrationDevice(User user,JSONObject jsonObject) {
		FavoriteDevice favoriteDevice=new FavoriteDevice();
		favoriteDevice.setUserId(user.getUserId()).setUserName(user.getUserName());
		List<FavoriteDevice> list=favoriteDeviceService.getFavoriteDevice(favoriteDevice);
		List<DeviceVO> node=JSONObject.parseArray(jsonObject.getString("node"), DeviceVO.class);
		if(null!=list&&list.size()>0&&null!=node&&node.size()>0){
		for(FavoriteDevice fd:list){
			for(int i=0;i<node.size();i++){
				if(null!=node.get(i).getChanid()&&node.get(i).getChanid().equals(fd.getChannelId())){
					node.get(i).setIsKeep(true);
					}
				}
			}
		}
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
