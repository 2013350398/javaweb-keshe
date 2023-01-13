package main.DAO;

import main.pojo.Evaluate;

import java.util.List;

public interface EvaluateDao {
    void addEvaluate(Evaluate evaluate);
    void updateEvaluate(Evaluate evaluate);
    void deleteEvaluate(String ev_id);
    Evaluate getEvaluate(String ev_id);
    List<Evaluate> EVALUATE_LIST(String st_id);
    List<Evaluate> TEVALUATE_LIST(String te_id);

}