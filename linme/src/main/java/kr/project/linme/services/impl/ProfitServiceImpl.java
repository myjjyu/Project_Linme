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
    public int addItem() throws Exception {
        int rows = profitMapper.insert();
        if (rows == 0) {
            throw new Exception("저장된 데이터가 없습니다.");
        }
        return rows;
    }

    @Override
    public Profit editItem(Profit input) throws Exception {
        int rows = profitMapper.update(input);
        if (rows == 0) {
            throw new Exception("수정된 데이터가 없습니다.");
        }
        return profitMapper.selectItem(input);
    }

    @Override
    public int deleteItem(Profit input) throws Exception {
        int rows = profitMapper.delete(input);
        if (rows == 0) {
            throw new Exception("삭제된 데이터가 없습니다.");
        }
        return rows;
    }

    @Override
    public Profit getItem(Profit input) throws Exception {
        Profit output = profitMapper.selectItem(input);
        if (output == null) {
            throw new Exception("조회된 데이터가 없습니다.");
        }
        return output;
    }


    @Override
    public List<Profit> getWeeklyProfit() throws Exception {
        return profitMapper.selectWeeklyProfit();
    }

    @Override
    public List<Profit> getMonthlyProfit() throws Exception {
        return profitMapper.selectMonthlyProfit();
    }

    @Override
    public int getCount(Profit input) throws Exception {
        return profitMapper.selectCount(input);
    }
}
