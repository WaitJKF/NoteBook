package dbservice;

import java.util.List;
import java.util.Map;

/**
 * Created by 鞠坤峰 on 2018/3/29.
 */

public interface NoteService {
    public boolean addNote(Object[] orgs);
    public boolean delNote(Object[] orgs);
    public boolean upNote(Object[] orgs);
    public Map<String,String> viewNote(String[] str);
    public List<Map<String,String>> listNote(String[] str);
}
