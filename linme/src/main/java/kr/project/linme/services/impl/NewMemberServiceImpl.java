package kr.project.linme.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.project.linme.mappers.NewMemberMapper;
import kr.project.linme.models.NewMember;
import kr.project.linme.services.NewMemberService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NewMemberServiceImpl implements NewMemberService {

    @Autowired
    private NewMemberMapper newMemberMapper;

    @Override
    public void addItem() throws Exception {
        try {
            newMemberMapper.insert();
        } catch (Exception e) {
            log.error("데이터 저장에 실패했습니다.", e);
            throw e;
        }
    }

    @Override
    public NewMember editItem(NewMember input) throws Exception {
        int rows = 0;

        try {
            rows = newMemberMapper.update(input);

            if (rows == 0) {
                throw new Exception("수정된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 수정에 실패했습니다.", e);
            throw e;
        }

        return newMemberMapper.selectItem(input);
    }

    // 31일이 지난 데이터 삭제
    @Override
    public int deleteItem() throws Exception {
        int rows = 0;

        try {
            rows = newMemberMapper.delete();

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
    public NewMember getItem(NewMember input) throws Exception {
        NewMember result = null;

        try {
            result = newMemberMapper.selectItem(input);

            if (result == null) {
                throw new Exception("조회된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 조회에 실패했습니다.", e);
            throw e;
        }

        return result;
    }

    // 주간 신규회원 조회
    @Override
    public List<NewMember> getWeeklyNew() throws Exception {
        List<NewMember> result = null;

        try {
            result = newMemberMapper.selectWeeklyNew();
        } catch (Exception e) {
            log.error("주간 신규회원 조회에 실패했습니다.", e);
            throw e;
        }

        return result;
    }

    // 월간 신규회원 조회
    @Override
    public List<NewMember> getMonthlyNew() throws Exception {
        List<NewMember> result = null;

        try {
            result = newMemberMapper.selectMonthlyNew();
        } catch (Exception e) {
            log.error("월간 신규회원 조회에 실패했습니다.", e);
            throw e;
        }

        return result;
    }

    @Override
    public int getCount(NewMember input) throws Exception {
        int result = 0;

        try {
            result = newMemberMapper.selectCount(input);
        } catch (Exception e) {
            log.error("데이터 수 조회에 실패했습니다.", e);
            throw e;
        }

        return result;
    }
    
}
