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
    public List<BestProd> selectWList() throws Exception {
        List<BestProd> result = null;

        try {
            result = bestProdMapper.selectWList();
        } catch (Exception e) {
            log.error("데이터 조회에 실패했습니다.", e);
            throw new Exception("데이터 조회에 실패했습니다.");
        }

        return result;
    }

    @Override
    public int insert(BestProd input) throws Exception {
        int result = 0;

        try {
            result = bestProdMapper.insert();
        } catch (Exception e) {
            log.error("데이터 저장에 실패했습니다.", e);
            throw new Exception("데이터 저장에 실패했습니다.");
        }

        return result;
    }

    @Override
    public List<BestProd> selectMList() throws Exception {
        List<BestProd> result = null;

        try {
            result = bestProdMapper.selectMList();
        } catch (Exception e) {
            log.error("데이터 조회에 실패했습니다.", e);
            throw new Exception("데이터 조회에 실패했습니다.");
        }

        return result;
    }
}