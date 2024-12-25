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
    public BestProd addItem() throws Exception {
        try {
            // 인기 상품 10개 집계하여 삽입
            int result = bestProdMapper.insert();
            if (result == 0) {
                throw new Exception("인기 상품 집계에 실패했습니다.");
            }
        } catch (Exception e) {
            log.error("인기 상품 추가에 실패했습니다.", e);
            throw e;
        }
        return null; 
    }

    @Override
    public BestProd editItem(BestProd params) throws Exception {
        int rows = 0;
        try {
            rows = bestProdMapper.update(params);
            if (rows == 0) {
                throw new Exception("수정된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 수정에 실패했습니다.", e);
            throw e;
        }
        return bestProdMapper.selectItem(params); 
    }

    @Override
    public int deleteItem(BestProd params) throws Exception {
        int rows = 0;
        try {
            rows = bestProdMapper.delete(params);
            if (rows == 0) {
                throw new Exception("삭제된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 삭제에 실패했습니다.", e);
            throw e;
        }
        return rows; // 삭제된 행 수 리턴
    }

    @Override
    public BestProd getItem(BestProd params) throws Exception {
        BestProd result = null;
        try {
            result = bestProdMapper.selectItem(params);
            if (result == null) {
                throw new Exception("조회된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 조회에 실패했습니다.", e);
            throw e;
        }
        return result;
    }

    @Override
    public List<BestProd> getList() throws Exception {
        List<BestProd> result = null;
        try {
            result = bestProdMapper.selectList();
        } catch (Exception e) {
            log.error("목록 조회에 실패했습니다.", e);
            throw e;
        }
        return result;
    }

    @Override
    public int getCount() throws Exception {
        int count = 0;
        try {
            count = bestProdMapper.selectCount();
        } catch (Exception e) {
            log.error("데이터 집계에 실패했습니다.", e);
            throw e;
        }
        return count;
    }
}
