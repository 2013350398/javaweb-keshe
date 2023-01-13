package main.searchcriteria;


import main.pojo.Verify;
import main.Util.DBUtil;

public class VerifySearchCriteria extends Verify implements SearchCriteria{
    @Override
    public String getCriteriaSQL(String preSql) {
        // add the search criteria
        StringBuilder criteriaSql = new StringBuilder(512);
        criteriaSql.append(preSql);
        if(this.getAchiev_no() != null)
            criteriaSql.append("achiev_no LIKE '%" + DBUtil.fixSqlFieldValue(this.getAchiev_no()) + "%' AND ");
        if(this.getSt_id() != null)
            criteriaSql.append("st_id LIKE '%" + DBUtil.fixSqlFieldValue(this.getSt_id()) + "%' AND ");
        if(this.getSubmit_time() != null)
            criteriaSql.append("submit_time LIKE '%" + DBUtil.fixSqlFieldValue(this.getSubmit_time()) + "%' AND ");
        if(this.getMe_id() != null)
            criteriaSql.append("me_id LIKE '%" + DBUtil.fixSqlFieldValue(this.getMe_id()) + "%' AND ");
        if(this.getFirst_time() != null)
            criteriaSql.append("first_time LIKE '%" + DBUtil.fixSqlFieldValue(this.getFirst_time()) + "%' AND ");
        if(this.getFirst_verify() != null)
            criteriaSql.append("first_verify = " + this.getFirst_verify() + " AND ");
        if(this.getAd_id() != null)
            criteriaSql.append("ad_id LIKE '%" + DBUtil.fixSqlFieldValue(this.getAd_id()) + "%' AND ");
        if(this.getLast_time() != null)
            criteriaSql.append("last_time LIKE '%" + DBUtil.fixSqlFieldValue(this.getLast_time()) + "%' AND ");
        if(this.getLast_verify() != null)
            criteriaSql.append("last_verify = " + this.getLast_verify() + " AND ");

        // remove unused 'AND'
        if(criteriaSql.substring(criteriaSql.length() - 5).equals(" AND "))
            criteriaSql.delete(criteriaSql.length() - 5, criteriaSql.length() - 1);
        if(criteriaSql.substring(criteriaSql.length() - 7).equals(" WHERE "))
            criteriaSql.delete(criteriaSql.length() - 7, criteriaSql.length() - 1);

        return criteriaSql.toString();
    }
}