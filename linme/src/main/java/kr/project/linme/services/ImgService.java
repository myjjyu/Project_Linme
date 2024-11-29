package kr.project.linme.services;

import java.util.List;

import kr.project.linme.models.Img;

public interface ImgService {
    public Img addItem(Img params) throws Exception;

    public Img editItem(Img params) throws Exception;

    public int deleteItem(Img params) throws Exception;

    public Img getItem(Img params) throws Exception;

    public List<Img> getList(Img params) throws Exception;

    public int getCount(Img params) throws Exception;
}
