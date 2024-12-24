package kr.project.linme.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.project.linme.mappers.BestProdMapper;
import kr.project.linme.models.BestProd;
import kr.project.linme.services.BestProdService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BestProdServiceImpl implements BestProdService {

    @Autowired
    private BestProdMapper bestProdMapper;

    @Override
    public BestProd addItem(BestProd input) throws Exception {
        int rows = 0;

        try {
            bestProdMapper.insertBestProds();

            if (rows == 0) {
                throw new Exception("저장된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 저장에 실패했습니다.", e);
            throw e;
        }

        return bestProdMapper.selectItem(input);
    }

    @Override
    public BestProd editItem(BestProd input) throws Exception {
        int rows = 0;

        try {
            rows = bestProdMapper.update(input);

            if (rows == 0) {
                throw new Exception("수정된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 수정에 실패했습니다.", e);
            throw e;
        }

        return bestProdMapper.selectItem(input);
    }

    @Override
    public int deleteItem(BestProd input) throws Exception {
        int rows = 0;

        try {
            rows = bestProdMapper.delete(input);

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
    public BestProd getItem(BestProd input) throws Exception {
        BestProd output = null;

        try {
            output = bestProdMapper.selectItem(input);

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
    public List<BestProd> getList(BestProd input) throws Exception {
        List<BestProd> output = null;

        try {
            output = bestProdMapper.selectList(input);
        } catch (Exception e) {
            log.error("데이터 목록 조회에 실패했습니다.", e);
            throw e;
        }

        return output;
    }

    @Override
    public int getCount(BestProd input) throws Exception {
        int output = 0;

        try {
            output = bestProdMapper.selectCount(input);
        } catch (Exception e) {
            log.error("데이터 집계에 실패했습니다.", e);
            throw e;
        }

        return output;
    }
}