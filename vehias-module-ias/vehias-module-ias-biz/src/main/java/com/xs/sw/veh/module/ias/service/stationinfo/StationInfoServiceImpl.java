package com.xs.sw.veh.module.ias.service.stationinfo;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xs.sw.veh.module.system.controller.admin.dept.vo.dept.DeptSaveReqVO;
import com.xs.sw.veh.module.system.dal.dataobject.dept.DeptDO;
import com.xs.sw.veh.module.system.service.dept.DeptService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import com.xs.sw.veh.module.ias.controller.admin.stationinfo.vo.*;
import com.xs.sw.veh.module.ias.dal.dataobject.stationinfo.StationInfoDO;
import com.xs.sw.veh.framework.common.pojo.PageResult;
import com.xs.sw.veh.framework.common.pojo.PageParam;
import com.xs.sw.veh.framework.common.util.object.BeanUtils;

import com.xs.sw.veh.module.ias.dal.mysql.stationinfo.StationInfoMapper;

import static com.xs.sw.veh.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.xs.sw.veh.module.ias.enums.ErrorCodeConstants.*;

/**
 * 机构 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class StationInfoServiceImpl implements StationInfoService {

    @Resource
    private StationInfoMapper stationInfoMapper;

    @Resource
    private DeptService deptService ;
    @Override
    public Long createStationInfo(StationInfoSaveReqVO createReqVO) {
        // 插入机构
        StationInfoDO stationInfo = BeanUtils.toBean(createReqVO, StationInfoDO.class);
        JSONObject jsonObject = geoCoding(stationInfo.getArea());
        stationInfo.setCityId((String) geoCoding(stationInfo.getCity()).get("adcode"));
        stationInfo.setAreaId((String) jsonObject.get("adcode"));

        stationInfoMapper.insert(stationInfo);
        //连接部门和机构管理
        String province = jsonObject.get("province" , String.class);
        String city = jsonObject.get("city" , String.class);
        String district = jsonObject.get("district" , String.class);
        // 返回
        DeptSaveReqVO deptSaveReqVO  = new DeptSaveReqVO();
        //判断省
        DeptDO deptProvince = deptService.getDept(province);
        Long deptParentId = Long.valueOf(100);
        if(deptProvince == null){
            deptSaveReqVO.setName(province);
            deptSaveReqVO.setParentId(deptParentId);
            deptParentId = deptService.createDept(deptSaveReqVO);
        }else{
            deptParentId = deptProvince.getId();
        }
        //判断市
        DeptDO deptCity = deptService.getDept(city);
        if(deptCity == null){
            deptSaveReqVO.setName(city);
            deptSaveReqVO.setParentId(deptParentId);
            deptParentId = deptService.createDept(deptSaveReqVO);
        }else{
            deptParentId = deptCity.getId();
        }

        //判断区
        DeptDO deptDistrict = deptService.getDept(district);
        if(deptDistrict == null){
            deptSaveReqVO.setName(district);
            deptSaveReqVO.setParentId(deptParentId);
            deptParentId = deptService.createDept(deptSaveReqVO);
        }else{
            deptParentId = deptDistrict.getId();
        }

        deptSaveReqVO.setName(createReqVO.getJyjbmc());
        deptSaveReqVO.setParentId(deptParentId);
        QueryWrapper<StationInfoDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("jyjgbh", stationInfo.getJyjgbh());
        StationInfoDO infoDO = stationInfoMapper.selectOne(queryWrapper);
        deptSaveReqVO.setStationInfoId(infoDO.getId());
        deptService.createDept(deptSaveReqVO);
        return stationInfo.getId();
    }


    @Override
    public void updateStationInfo(StationInfoSaveReqVO updateReqVO) {
        // 校验存在
        validateStationInfoExists(updateReqVO.getId());
        // 更新
        StationInfoDO updateObj = BeanUtils.toBean(updateReqVO, StationInfoDO.class);
        stationInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteStationInfo(Long id) {
        // 校验存在
        validateStationInfoExists(id);
        // 删除
        stationInfoMapper.deleteById(id);
    }

    private void validateStationInfoExists(Long id) {
        if (stationInfoMapper.selectById(id) == null) {
            throw exception(STATION_INFO_NOT_EXISTS);
        }
    }

    @Override
    public StationInfoDO getStationInfo(Long id) {
        return stationInfoMapper.selectById(id);
    }

    @Override
    public PageResult<StationInfoDO> getStationInfoPage(StationInfoPageReqVO pageReqVO) {
        return stationInfoMapper.selectPage(pageReqVO);
    }

    public JSONObject geoCoding(String address) {
        String apiKey = "04ef2fd17b896937caecab1aeafb5b6e";
        String requestUrl = String.format("https://restapi.amap.com/v3/geocode/geo?key=%s&address=%s", apiKey, address);

        StringBuffer response = null ;
        JSONObject jsonObject = null ;
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            jsonObject = new JSONObject(response);
            System.out.println(jsonObject.get("geocodes"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONArray jsonArray = new JSONArray(jsonObject.get("geocodes"));

        // 因为数组里只有一个元素，我们可以直接获取第一个元素
        JSONObject jsonAdcode = jsonArray.getJSONObject(0);

        // 获取adcode的值
        //String adcode = (String) jsonAdcode.get("adcode");
        return jsonAdcode ;
    }
}