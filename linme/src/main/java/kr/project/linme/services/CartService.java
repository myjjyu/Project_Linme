package kr.project.linme.services;

import java.util.List;

import kr.project.linme.models.Cart;

public interface CartService {
    public Cart addItem(Cart params) throws Exception;

    public Cart editItem(Cart params) throws Exception;

    public int deleteItem(Cart params) throws Exception;

    public Cart getItem(Cart params) throws Exception;

    public List<Cart> getList(Cart params) throws Exception;

    public int getCount(Cart params) throws Exception;

    public Cart uniqueCartCount(Cart parmas) throws Exception;

    public Cart editUniqueCart(Cart params) throws Exception;

    public int deleteByOverDays() throws Exception;
}
