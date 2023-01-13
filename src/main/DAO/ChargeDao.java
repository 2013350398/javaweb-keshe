package main.DAO;

import main.pojo.Admin;
import main.pojo.charge;

import java.util.List;

public interface ChargeDao {
    List<charge> findCharges(charge s);
}