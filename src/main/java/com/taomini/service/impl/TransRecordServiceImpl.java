package com.taomini.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taomini.core.constant.IniConfigEnum;
import com.taomini.core.constant.TaoMiniConstant;
import com.taomini.core.constant.TransTypeEnum;
import com.taomini.core.constant.UserConstant;
import com.taomini.core.dao.ITransRecordMapper;
import com.taomini.model.IniConfigDTO;
import com.taomini.model.TransRecordDTO;
import com.taomini.model.vo.TransRecordVO;
import com.taomini.service.ITransRecordService;
import com.taomini.util.DateUtil;
import com.taomini.util.TaoMiniUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;

/**
 *
 * @author chentao
 * @create 2019/9/3
 * @since 1.0.0
 */
@Service
public class TransRecordServiceImpl implements ITransRecordService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransRecordServiceImpl.class);

    @Autowired
    private ITransRecordMapper transRecordMapper;

    @Autowired
    private IniConfigServiceImpl iniConfigService;

    @Autowired
    private TaoMiniUtils taoMiniUtils;

    @Override
    public void saveRecord(TransRecordDTO dto) {
        dto.setTransId(UUID.randomUUID().toString());
        if(dto.getTransDate().equals("今天")){
            dto.setTransDate(DateUtil.getCurrDate());
        }
        dto.setCrtDate(DateUtil.getCurrDate());
        dto.setCrtTime(DateUtil.getCurrTime());
        transRecordMapper.saveRecord(dto);
    }

    @Override
    public List<TransRecordDTO> getRecordByUserAndDate(String openId, String currDate) {
        TransRecordDTO dto = new TransRecordDTO();
        dto.setUser(openId);
        dto.setTransDate(currDate);
        return transRecordMapper.getRecordByUserAndDate(dto);
    }

    @Override
    public List<TransRecordVO> getRecordByUser(String openId) {
        TransRecordDTO dto = new TransRecordDTO();
        dto.setUser(openId);
        List<TransRecordDTO> transs = transRecordMapper.getRecordByUser(dto);
        List<TransRecordDTO> sameDate = new ArrayList<>();
        List<TransRecordVO> retList = new ArrayList<>();
        TransRecordVO vo = new TransRecordVO();
        String transDate = "";
        double total = 0;
        for(TransRecordDTO trans : transs){
            trans.setTransTypeName(taoMiniUtils.getTransTypeName(trans.getTransType()));
            IniConfigDTO imgDTO = iniConfigService.getIniConfig4One(IniConfigEnum.TRANSTYPEIMAGE.getIniType(), IniConfigEnum.TRANSTYPEIMAGE.getIniClass(), dto.getTransType());
            trans.setImageUrl(TaoMiniUtils.getTransActiveImageUrl(imgDTO.getIniCodeValue()));
            if(transDate.equals("")){
                //第一次进入
                transDate = trans.getTransDate();
            }

            //日期变了,重置对象
            if(!transDate.equals(trans.getTransDate())){
                //设置对象
                vo.setTotal(total+"");
                vo.setData(sameDate);
                vo.setTransDate(DateUtil.formatDateMMDD(transDate));
                retList.add(vo);
                //重置对象
                transDate = trans.getTransDate();
                vo = new TransRecordVO();
                sameDate = new ArrayList<>();
                total = 0;
                //加上本轮信息
                sameDate.add(trans);
                boolean isIncome = false;
                for(String transType : TaoMiniConstant.NOPAYTRANS){
                    if(transType.equals(trans.getTransType())){
                        isIncome = true;
                    }
                }
                if(!isIncome)
                    total += Double.parseDouble(trans.getMoney());
            }else{
                sameDate.add(trans);
                boolean isIncome = false;
                for(String transType : TaoMiniConstant.NOPAYTRANS){
                    if(transType.equals(trans.getTransType())){
                        isIncome = true;
                    }
                }
                if(!isIncome)
                    total += Double.parseDouble(trans.getMoney());
            }
        }

        //加上最后一段
        if(!sameDate.isEmpty()){
            vo.setTotal(total+"");
            vo.setData(sameDate);
            vo.setTransDate(DateUtil.formatDateMMDD(transDate));
            retList.add(vo);
        }

        return retList;
    }

    @Override
    public List<TransRecordVO> getRecordByUserAndDatePage(String openId, String _transDate) {
        IniConfigDTO iniConfigDTO = iniConfigService.getIniConfig4One(IniConfigEnum.SYSTEMCONFIG.getIniType(), IniConfigEnum.SYSTEMCONFIG.getIniClass(), "isAllTrans");
        if(iniConfigDTO!=null && iniConfigDTO.getIniCodeValue().equals("all")){
            return this.getRecordByDate(_transDate);
        }
        TransRecordDTO dto = new TransRecordDTO();
        dto.setUser(openId);
        dto.setTransDate(_transDate);
        List<TransRecordDTO> transs = transRecordMapper.getRecordByUserAndDate(dto);
        List<TransRecordDTO> sameDate = new ArrayList<>();
        List<TransRecordVO> retList = new ArrayList<>();
        TransRecordVO vo = new TransRecordVO();
        String transDate = "";
        double total = 0;
        for(TransRecordDTO trans : transs){
            trans.setTransTypeName(taoMiniUtils.getTransTypeName(trans.getTransType()));
            IniConfigDTO imgDTO = iniConfigService.getIniConfig4One(IniConfigEnum.TRANSTYPEIMAGE.getIniType(), IniConfigEnum.TRANSTYPEIMAGE.getIniClass(), trans.getTransType());
            trans.setImageUrl(TaoMiniUtils.getTransActiveImageUrl(imgDTO.getIniCodeValue()));
            if(transDate.equals("")){
                //第一次进入
                transDate = trans.getTransDate();
            }

            //日期变了,重置对象
            if(!transDate.equals(trans.getTransDate())){
                //设置对象
                vo.setTotal(total+"");
                vo.setData(sameDate);
                vo.setTransDate(DateUtil.formatDateMMDD(transDate));
                retList.add(vo);
                //重置对象
                transDate = trans.getTransDate();
                vo = new TransRecordVO();
                sameDate = new ArrayList<>();
                total = 0;
                //加上本轮信息
                sameDate.add(trans);
                //日统计统计收入
                boolean isIncome = false;
                for(String transType : TaoMiniConstant.NOPAYTRANS){
                    if(transType.equals(trans.getTransType())){
                        isIncome = true;
                    }
                }
                if(!isIncome)
                    total += Double.parseDouble(trans.getMoney());
            }else{
                sameDate.add(trans);
                //日统计统计收入
                boolean isIncome = false;
                for(String transType : TaoMiniConstant.NOPAYTRANS){
                    if(transType.equals(trans.getTransType())){
                        isIncome = true;
                    }
                }
                if(!isIncome)
                    total += Double.parseDouble(trans.getMoney());
            }
        }

        //加上最后一段
        if(!sameDate.isEmpty()){
            vo.setTotal(total+"");
            vo.setData(sameDate);
            vo.setTransDate(DateUtil.formatDateMMDD(transDate));
            retList.add(vo);
        }

        return retList;
    }

    @Override
    public String getPayByMonth(String month) {
        TransRecordDTO dto = new TransRecordDTO();
        dto.setTransDate(month);
        dto.setUser(UserConstant.TAO);
        double pay = 0;

        List<TransRecordDTO> list = transRecordMapper.getRecordByUserAndDate(dto);
        for(TransRecordDTO res : list){
            //计算支出，排除收入交易
            boolean isIncomeTrans = false;
            for(String transType : TaoMiniConstant.NOPAYTRANS){
                if(transType.equals(res.getTransType())){
                    isIncomeTrans = true;
                }
            }
            if(isIncomeTrans)
                continue;
            pay += Double.parseDouble(res.getMoney());
        }

        dto.setUser(UserConstant.SIQI);
        List<TransRecordDTO> listq = transRecordMapper.getRecordByUserAndDate(dto);
        for(TransRecordDTO res : listq){
            //计算支出，排除收入交易
            boolean isIncomeTrans = false;
            for(String transType : TaoMiniConstant.NOPAYTRANS){
                if(transType.equals(res.getTransType())){
                    isIncomeTrans = true;
                }
            }
            if(isIncomeTrans)
                continue;
            pay += Double.parseDouble(res.getMoney());
        }
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(pay) + "";
    }

    @Override
    public TransRecordDTO getTransRecord(String transId) {
        TransRecordDTO dto = transRecordMapper.getTransRecord(transId);
        dto.setTransTypeName(taoMiniUtils.getTransTypeName(dto.getTransType()));
        IniConfigDTO imgDTO = iniConfigService.getIniConfig4One(IniConfigEnum.TRANSTYPEIMAGE.getIniType(), IniConfigEnum.TRANSTYPEIMAGE.getIniClass(), dto.getTransType());
        dto.setImageUrl(TaoMiniUtils.getTransActiveImageUrl(imgDTO.getIniCodeValue()));
        return dto;
    }

    @Override
    public void deleteTransRecord(String transId) {
        TransRecordDTO dto = new TransRecordDTO();
        dto.setTransId(transId);
        dto.setStatus("0");
        transRecordMapper.updateTransRecordStatus(dto);
    }

    @Override
    public void updateTransRecord(TransRecordDTO dto) {
        transRecordMapper.updateTransRecord(dto);
    }

    @Override
    public String[] getTransReportWeek() {
        String[] strs = new String[2];
        StringBuffer sb = new StringBuffer();

        double totalMoney = 0;

        for(int i=0; i<7; i++){
            String date = DateUtil.getDateByInput(0-i);
            TransRecordDTO tao = new TransRecordDTO();
            tao.setUser(UserConstant.TAO);
            tao.setTransDate(date);
            List<TransRecordDTO> taos = transRecordMapper.getRecordByUserAndDate(tao);

            for(TransRecordDTO dto : taos){
                totalMoney += Double.parseDouble(dto.getMoney());
            }

            TransRecordDTO pang = new TransRecordDTO();
            pang.setUser(UserConstant.SIQI);
            pang.setTransDate(date);
            List<TransRecordDTO> pangs = transRecordMapper.getRecordByUserAndDate(pang);

            for(TransRecordDTO dto : pangs){
                totalMoney += Double.parseDouble(dto.getMoney());
            }
        }
        strs[0] = DateUtil.getCurrDate();
        sb.append("本周花销:");
        sb.append(totalMoney);
        strs[1] = sb.toString();

        return strs;
    }

    @Override
    public String getTransReportDate() {
        List<TransRecordDTO> retList = transRecordMapper.getTransRecordDate(DateUtil.getCurrDate());
        StringBuffer sb = new StringBuffer();
        for(TransRecordDTO dto : retList){
            sb.append(dto.getTransTypeName());
            sb.append(":");
            sb.append(dto.getMoney());
            sb.append(";\n");
        }
        return sb.toString();
    }

    @Override
    public String[] getTransReportMonth() {
        String[] strs = new String[3];
        //统计时间，本月支出，记账数目

        strs[0] = format(DateUtil.getCurrDate() + " " + DateUtil.getCurrTime());

        //获取Tao
        String month = DateUtil.getCurrDate().substring(0, 6);
        TransRecordDTO dto = new TransRecordDTO();
        dto.setTransDate(month);
        dto.setUser(UserConstant.TAO);
        List<TransRecordDTO> list = transRecordMapper.getRecordByUserAndDate(dto);

        //获取SQ
        dto.setUser(UserConstant.SIQI);
        List<TransRecordDTO> qlist = transRecordMapper.getRecordByUserAndDate(dto);

        strs[2] = (list.size() + qlist.size()) + "笔";

        StringBuffer detail = new StringBuffer();
        Map<String, String> detailMap = new HashMap();
        for(TransRecordDTO item : list){

            if(!item.getTransType().equals(TransTypeEnum.BREAKFASTTRANS.getTransType()) &&
                !item.getTransType().equals(TransTypeEnum.LUNCHTRANS.getTransType()) && !item.getTransType().equals(TransTypeEnum.DINNERTRANS.getTransType())){
                String transTypeName = taoMiniUtils.getTransTypeName(item.getTransType());
                if(detailMap.get(transTypeName) == null){
                    detailMap.put(transTypeName, item.getMoney());
                }else{
                    double money = Double.parseDouble(detailMap.get(transTypeName));
                    money += Double.parseDouble(item.getMoney());
                    detailMap.put(transTypeName, money+"");
                }
                continue;
            }

            //计算日常伙食
            if(detailMap.get("daily") == null){
                detailMap.put("daily", item.getMoney());
            }else{
                double money = Double.parseDouble(detailMap.get("daily"));
                money += Double.parseDouble(item.getMoney());
                detailMap.put("daily", money+"");
            }
        }

        for(TransRecordDTO item : qlist){
            if(!item.getTransType().equals(TransTypeEnum.BREAKFASTTRANS.getTransType()) &&
                    !item.getTransType().equals(TransTypeEnum.LUNCHTRANS.getTransType()) && !item.getTransType().equals(TransTypeEnum.DINNERTRANS.getTransType())){
                String transTypeName = taoMiniUtils.getTransTypeName(item.getTransType());
                if(detailMap.get(transTypeName) == null){
                    detailMap.put(transTypeName, item.getMoney());
                }else{
                    double money = Double.parseDouble(detailMap.get(transTypeName));
                    money += Double.parseDouble(item.getMoney());
                    detailMap.put(transTypeName, money+"");
                }
                continue;
            }

            //计算日常伙食
            if(detailMap.get("daily") == null){
                detailMap.put("daily", item.getMoney());
            }else{
                double money = Double.parseDouble(detailMap.get("daily"));
                money += Double.parseDouble(item.getMoney());
                detailMap.put("daily", money+"");
            }
        }

        detail.append("具体消费如下:\n");
        for(String key : detailMap.keySet()){
            if(key.equals("daily")){
                detail.append("日常伙食:" + detailMap.get(key) + "元。");
            }else{
                detail.append(key + detailMap.get(key) + "元。");
            }
            detail.append("\n");
        }
        detail.append("总计支出:" + this.getPayByMonth(month));
        strs[1] = detail.toString();

        return strs;
    }

    @Override
    public String getIncomeByMonth(String month) {
        TransRecordDTO dto = new TransRecordDTO();
        dto.setTransDate(month);
        double pay = 0;

        //SQL中有UNION语句，只需要查询一次就足够了
        List<TransRecordDTO> list = transRecordMapper.getRecordByUserAndDate(dto);
        for(TransRecordDTO res : list){
            //计算收入
            for(String transType : TaoMiniConstant.NOPAYTRANS){
                if(transType.equals(res.getTransType())){
                    pay += Double.parseDouble(res.getMoney());
                }
            }
        }

        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(pay) + "";
    }

    @Override
    public JSONArray getTransList() {
        List<IniConfigDTO> iniConfigDTOS = iniConfigService.getIniConfig(IniConfigEnum.TRANSTYPE.getIniType(), IniConfigEnum.TRANSTYPE.getIniClass());

        iniConfigDTOS.sort(Comparator.comparing( obj -> Integer.parseInt(obj.getIniDesc()))); //默认升序
        JSONArray arr = new JSONArray();

        List<IniConfigDTO> imageDTOS = iniConfigService.getIniConfig(IniConfigEnum.TRANSTYPEIMAGE.getIniType(), IniConfigEnum.TRANSTYPEIMAGE.getIniClass());



        Map<String , String> imgMap = new HashMap<>();
        for(IniConfigDTO imgDto : imageDTOS){
            imgMap.put(imgDto.getIniCode(), imgDto.getIniCodeValue());
        }

        for(IniConfigDTO dto : iniConfigDTOS){
            JSONObject object = new JSONObject();
            String transType = dto.getIniCode();
            object.put("transType", transType);
            object.put("transTypeName", dto.getIniCodeValue());
            object.put("imageUrl", imgMap.get(transType));
            object.put("imageUrlActive", TaoMiniUtils.getTransActiveImageUrl(imgMap.get(transType)));
            arr.add(object);
        }

        return arr;
    }

    @Override
    public List<TransRecordVO> getRecordByDate(String _transDate) {
        long begin = System.currentTimeMillis();
        TransRecordDTO dto = new TransRecordDTO();
        dto.setTransDate(_transDate);
        List<TransRecordDTO> transs = transRecordMapper.getRecordByUserAndDate(dto);
        long end = System.currentTimeMillis();
        System.out.println("1" + (end - begin));
        List<TransRecordDTO> sameDate = new ArrayList<>();
        List<TransRecordVO> retList = new ArrayList<>();
        TransRecordVO vo = new TransRecordVO();
        String transDate = "";
        double total = 0;
        List<IniConfigDTO> iniConfigDTOS = iniConfigService.getIniConfig(IniConfigEnum.TRANSTYPEIMAGE.getIniType(), IniConfigEnum.TRANSTYPEIMAGE.getIniClass());
        end = System.currentTimeMillis();
        System.out.println("2" + (end - begin));

        for(TransRecordDTO trans : transs){
            trans.setTransTypeName(taoMiniUtils.getTransTypeName(trans.getTransType()) + "|" + TaoMiniUtils.getUserName(trans.getUser()));
            //IniConfigDTO imgDTO = iniConfigService.getIniConfig4One(IniConfigEnum.TRANSTYPEIMAGE.getIniType(), IniConfigEnum.TRANSTYPEIMAGE.getIniClass(), trans.getTransType());
            IniConfigDTO imgDTO = new IniConfigDTO();
            for(IniConfigDTO config : iniConfigDTOS){
                if(config.getIniCode().equals(trans.getTransType())){
                    imgDTO = config;
                    break;
                }
            }
            trans.setImageUrl(TaoMiniUtils.getTransActiveImageUrl(imgDTO.getIniCodeValue()));
            if(transDate.equals("")){
                //第一次进入
                transDate = trans.getTransDate();
            }

            //日期变了,重置对象
            if(!transDate.equals(trans.getTransDate())){
                //设置对象
                vo.setTotal(total+"");
                vo.setData(sameDate);
                vo.setTransDate(DateUtil.formatDateMMDD(transDate));
                retList.add(vo);
                //重置对象
                transDate = trans.getTransDate();
                vo = new TransRecordVO();
                sameDate = new ArrayList<>();
                total = 0;
                //加上本轮信息
                sameDate.add(trans);
                //日统计统计收入
                boolean isIncome = false;
                for(String transType : TaoMiniConstant.NOPAYTRANS){
                    if(transType.equals(trans.getTransType())){
                        isIncome = true;
                    }
                }
                if(!isIncome)
                    total += Double.parseDouble(trans.getMoney());
            }else{
                sameDate.add(trans);
                //日统计统计收入
                boolean isIncome = false;
                for(String transType : TaoMiniConstant.NOPAYTRANS){
                    if(transType.equals(trans.getTransType())){
                        isIncome = true;
                    }
                }
                if(!isIncome)
                    total += Double.parseDouble(trans.getMoney());
            }
        }

        //加上最后一段
        if(!sameDate.isEmpty()){
            vo.setTotal(total+"");
            vo.setData(sameDate);
            vo.setTransDate(DateUtil.formatDateMMDD(transDate));
            retList.add(vo);
        }
        end = System.currentTimeMillis();
        System.out.println("3" + (end - begin));
        return retList;
    }

    @Override
    public String getTransMoneyDate() {
        List<TransRecordDTO> retList = transRecordMapper.getTransRecordDate(DateUtil.getCurrDate());
        double sum = 0;
        for(TransRecordDTO transRecordDTO : retList){
            sum+=Double.parseDouble(transRecordDTO.getMoney());
        }
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(sum) + "";
    }

    private String format(String date){
        String[] dates = date.split(" ");
        return dates[0].substring(0,4) + "年" + dates[0].substring(4,6) + "月" + dates[0].substring(6, 8) + "日 " + dates[1].substring(0, 2) + ":" + dates[1].substring(2,4) + ":" + dates[1].substring(4,6);
    }
}