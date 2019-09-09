package com.taomini.service.impl;

import com.taomini.core.dao.ITransRecordMapper;
import com.taomini.model.TransRecordDTO;
import com.taomini.model.vo.TransRecordVO;
import com.taomini.service.ITransRecordService;
import com.taomini.util.DateUtil;
import com.taomini.util.TaoMiniUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author chentao
 * @create 2019/9/3
 * @since 1.0.0
 */
@Service
public class TransRecordServiceImpl implements ITransRecordService {

    @Autowired
    private ITransRecordMapper transRecordMapper;

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
            trans.setTransTypeName(TaoMiniUtils.getTransTypeName(trans.getTransType()));
            trans.setImageUrl(TaoMiniUtils.getTransImageUrl(trans.getTransType()));
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
                total += Double.parseDouble(trans.getMoney());
            }else{
                sameDate.add(trans);
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
            trans.setTransTypeName(TaoMiniUtils.getTransTypeName(trans.getTransType()));
            trans.setImageUrl(TaoMiniUtils.getTransImageUrl(trans.getTransType()));
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
                total += Double.parseDouble(trans.getMoney());
            }else{
                sameDate.add(trans);
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
}