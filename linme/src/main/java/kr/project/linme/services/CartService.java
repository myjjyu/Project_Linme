package kr.project.linme.services;

import java.util.List;

import kr.project.linme.models.Cart;

public interface CartService {
    /**
     * 장바구니에 상품 추가
     * @param params
     * @return
     * @throws Exception
     */
    public Cart addItem(Cart params) throws Exception;

    /**
     * 장바구니에 상품 수량 변경
     * @param params
     * @return
     * @throws Exception
     */
    public Cart editItem(Cart params) throws Exception;


    /**
     * 장바구니 상품 단일 삭제
     * @param params
     * @return
     * @throws Exception
     */
    public int deleteItem(Cart params) throws Exception;

    /**
     * 장바구니 상품 전체 삭제
     * @param input
     * @return
     * @throws Exception
     */
    public int deleteList(Cart params) throws Exception;


    /**
     * 장바구니 상품 단일 삭제
     * @param params
     * @return
     * @throws Exception
     */
    public Cart getItem(Cart params) throws Exception;

    /**
     * 장바구니 목록 조회
     * @param params
     * @return
     * @throws Exception
     */
    public List<Cart> getList(Cart params) throws Exception;

    /**
     * 장바구니 상품 중복 확인
     * @param params
     * @return
     * @throws Exception
     */
    public Cart editUniqueCart(Cart params) throws Exception;

    /**
     * 장바구니 상품의 수량 확인
     * @param params
     * @return
     * @throws Exception
     */
    public int selectCount(Cart params) throws Exception;

    /**
     * 장바구니에 담긴 상품의 총 금액을 조회
     * @param params
     * @return
     * @throws Exception
     */
    public int sumTotalPrice(Cart params) throws Exception;
}
