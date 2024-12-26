package kr.project.linme.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.project.linme.mappers.ProfitMapper;
import kr.project.linme.models.Profit;
import kr.project.linme.services.ProfitService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProfitServiceImpl implements ProfitService {

    @Autowired
    private ProfitMapper profitMapper;

    @Override
    public int addItem(Profit input) throws Exception {
        int rows = 0;

        try {
            rows = profitMapper.insert(input); // input 객체를 사용
            if (rows == 0) {
                throw new Exception("저장된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 저장에 실패했습니다.", e);
            throw e;
        }

        return rows; // 성공 시 영향을 받은 행 수 반환
    }

    @Override
    public Profit editItem(Profit input) throws Exception {
        int rows = 0;

        try {
            rows = profitMapper.update(input);

            if (rows == 0) {
                throw new Exception("수정된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 수정에 실패했습니다.", e);
            throw e;
        }

        return profitMapper.selectItem(input);
    }

    @Override
    public int deleteItem(Profit input) throws Exception {
        int rows = 0;

        try {
            rows = profitMapper.delete(input);

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
    public Profit getItem(Profit input) throws Exception {
        Profit output = null;

        try {
            output = profitMapper.selectItem(input);

            if (output == null) {
                throw new Exception("조회된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("조회에 실패했습니다.", e);
            throw e;
        }

        return output;
    }

    @Override
    public List<Profit> getList(Profit input) throws Exception {
        List<Profit> output = null;

        try {
            output = profitMapper.selectList(input);
        } catch (Exception e) {
            log.error("목록 조회에 실패했습니다.", e);
            throw e;
        }

        return output;
    }

    @Override
    public int getCount(Profit input) throws Exception {
        int output = 0;

        try {
            output = profitMapper.selectCount(input);
        } catch (Exception e) {
            log.error("데이터 집계에 실패했습니다.", e);
            throw e;
        }

        return output;
    }
}
