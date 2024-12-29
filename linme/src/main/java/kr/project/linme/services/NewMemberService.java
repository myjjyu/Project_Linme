package kr.project.linme.services;

import java.util.List;

import kr.project.linme.models.NewMember;

public interface NewMemberService {
    
    public void addItem() throws Exception;

    public NewMember editItem(NewMember input) throws Exception;

    public int deleteItem() throws Exception;

    public NewMember getItem(NewMember input) throws Exception;

    public List<NewMember> getWeeklyNew() throws Exception;

    public List<NewMember> getMonthlyNew() throws Exception;

    public int getCount(NewMember input) throws Exception;
}
