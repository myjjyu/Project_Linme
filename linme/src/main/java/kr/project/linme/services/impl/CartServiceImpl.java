package kr.project.linme.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.project.linme.mappers.CartMapper;
import kr.project.linme.models.Cart;
import kr.project.linme.services.CartService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    /**
     * 장바구니에 상품 추가
     */
    @Override
    public Cart addItem(Cart input) throws Exception {
        int rows = 0;

        try {
            rows = cartMapper.insert(input);
            log.debug("rows : " + rows);

            if (rows == 0) {
                throw new Exception("저장된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 저장에 실패했습니다.", e);
            throw e;
        }

        return cartMapper.selectItem(input);
    }

    /**
     * 장바구니에 상품 수량 변경
     */
    @Override
    public Cart editItem(Cart input) throws Exception {
        int rows = 0;

        try {
            rows = cartMapper.update(input);

            if (rows == 0) {
                throw new Exception("수정된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 수정에 실패했습니다.", e);
            throw e;
        }

        return cartMapper.selectItem(input);
    }

    /**
     * 장바구니 상품 단일 조회
     */
    @Override
    public Cart getItem(Cart input) throws Exception {
        Cart output = null;

        try {
            output = cartMapper.selectItem(input);

            if (output == null) {
                throw new Exception("조회된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 조회에 실패했습니다.", e);
            throw e;
        }

        return output;
    }

    /**
     * 장바구니 목록 조회
     */
    @Override
    public List<Cart> getList(Cart input) throws Exception {
        List<Cart> output = null;

        try {
            output = cartMapper.selectList(input);
        } catch (Exception e) {
            log.error("데이터 목록 조회에 실패했습니다.", e);
            throw e;
        }

        return output;
    }

    /**
     * 장바구니 상품 단일 삭제
     */
    @Override
    public int deleteItem(Cart input) throws Exception {
        int rows = 0;

        try {
            rows = cartMapper.delete(input);

            if (rows == 0) {
                throw new Exception("삭제된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 삭제에 실패했습니다.", e);
            throw e;
        }

        return rows;
    }

    /**
     * 장바구니 상품 전체 삭제
     */
    @Override
    public int deleteList(Cart input) throws Exception {
        int rows = 0;

        try {
            rows = cartMapper.deleteList(input);

            if (rows == 0) {
                throw new Exception("삭제된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 삭제에 실패했습니다.", e);
            throw e;
        }

        return rows;
    }
    

    // @Override
    // public int getCount(Cart input) throws Exception {
    //     int output = 0;

    //     try {
    //         output = cartMapper.selectCount(input);
    //     } catch (Exception e) {
    //         log.error("데이터 집계에 실패했습니다.", e);
    //         throw e;
    //     }

    //     return output;
    // }

    
}