package dbservice;

import java.util.List;
import java.util.Map;

/**
 * Created by 鞠坤峰 on 2018/3/29.
 */

public abstract interface PersonService {
    public  boolean addPerson(Object[] orgs);

    public  boolean delPerson(Object[] orgs);

    public  boolean upPerson(Object[] orgs);

    public  Map<String, String> findPerson(String[] orgs);

    public  List<Map<String, String>> selectPerson(String[] orgs);
}
