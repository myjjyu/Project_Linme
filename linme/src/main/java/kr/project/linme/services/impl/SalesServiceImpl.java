package kr.project.linme.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.project.linme.mappers.SalesMapper;
import kr.project.linme.models.Sales;
import kr.project.linme.services.SalesService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SalesServiceImpl implements SalesService {

    @Autowired
    private SalesMapper salesMapper;

    @Override
    public void addItem() throws Exception {

        try {
            salesMapper.insert();
        } catch (Exception e) {
            log.error("데이터 저장에 실패했습니다.", e);
            throw e;
        }
    }

    @Override
    public Sales editItem(Sales input) throws Exception {
        int rows = 0;

        try {
            rows = salesMapper.update(input);

            if (rows == 0) {
                throw new Exception("수정된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 수정에 실패했습니다.", e);
            throw e;
        }

        return salesMapper.selectItem(input);
    }

    @Override
    public int deleteItem(Sales input) throws Exception {
        int rows = 0;

        try {
            rows = salesMapper.delete(input);

            if (rows == 0) {
                throw new Exception("삭제된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 삭제에 실패했습니다.", e);
            throw e;
        }

        return rows;
    }

    @Override
    public Sales getItem(Sales input) throws Exception {
        Sales output = null;

        try {
            output = salesMapper.selectItem(input);

            if (output == null) {
                throw new Exception("조회된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 조회에 실패했습니다.", e);
            throw e;
        }

        return output;
    }

    @Override
    public List<Sales> getList(Sales input) throws Exception {
        List<Sales> output = null;

        try {
            output = salesMapper.selectList(input);
        } catch (Exception e) {
            log.error("데이터 목록 조회에 실패했습니다.", e);
            throw e;
        }

        return output;
    }

    @Override
    public int getCount(Sales input) throws Exception {
        int output = 0;

        try {
            output = salesMapper.selectCount(input);
        } catch (Exception e) {
            log.error("데이터 집계에 실패했습니다.", e);
            throw e;
        }

        return output;
    }
}