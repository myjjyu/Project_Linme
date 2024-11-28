package kr.project.linme.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.project.linme.mappers.ImgMapper;
import kr.project.linme.models.Img;
import kr.project.linme.services.ImgService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ImgServiceImpl implements ImgService {

    @Autowired
    private ImgMapper imgMapper;

    @Override
    public Img addItem(Img input) throws Exception {
        int rows = 0;

        try {
            rows = imgMapper.insert(input);

            if (rows == 0) {
                throw new Exception("저장된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 저장에 실패했습니다.", e);
            throw e;
        }

        return imgMapper.selectItem(input);
    }

    @Override
    public Img editItem(Img input) throws Exception {
        int rows = 0;

        try {
            rows = imgMapper.update(input);

            if (rows == 0) {
                throw new Exception("수정된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 수정에 실패했습니다.", e);
            throw e;
        }

        return imgMapper.selectItem(input);
    }

    @Override
    public int deleteItem(Img input) throws Exception {
        int rows = 0;

        try {
            rows = imgMapper.delete(input);

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
    public Img getItem(Img input) throws Exception {
        Img output = null;

        try {
            output = imgMapper.selectItem(input);

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
    public List<Img> getList(Img input) throws Exception {
        List<Img> output = null;

        try {
            output = imgMapper.selectList(input);
        } catch (Exception e) {
            log.error("데이터 목록 조회에 실패했습니다.", e);
            throw e;
        }

        return output;
    }

    @Override
    public int getCount(Img input) throws Exception {
        int output = 0;

        try {
            output = imgMapper.selectCount(input);
        } catch (Exception e) {
            log.error("데이터 집계에 실패했습니다.", e);
            throw e;
        }

        return output;
    }
}