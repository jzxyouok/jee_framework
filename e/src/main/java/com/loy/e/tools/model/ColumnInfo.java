package com.loy.e.tools.model;

import org.apache.commons.lang.StringUtils;
import com.loy.e.tools.util.ToolStringUtils;

/**
 * 
 * @author Loy Fu qq群 540553957
 * @since 1.7
 * @version 1.0.0
 * 
 */
public class ColumnInfo {
	
	protected String description;
	protected String fieldName;
	protected String formatter = "";
	protected boolean sortable  = false;
	String validate = "";
	protected EntityInfo entityInfo;
	
	public ColumnInfo(EntityInfo entityInfo){
		this.entityInfo = entityInfo;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	
	public String getEntityName() {
		return this.entityInfo.getEntityName();
	}
	
	
	
	public boolean isSortable() {
		return sortable;
	}

	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}

	/**
	 * 
	 * @return
	 */
	public String getCombineFieldName(){
		String[] temp = this.fieldName.split("\\.");
		for(int i=0;i<temp.length;i++){
			temp[i] = ToolStringUtils.firstCharUpper(temp[i]);
		}
		String s = StringUtils.join(temp);
		s = ToolStringUtils.firstCharLower(s);
		return s;
	}
	
	public String getEntityNameFirstCharLower(){
		return ToolStringUtils.firstCharLower(this.getEntityName());
	}
	
	public String getI18nKey(){
		return this.entityInfo.getPreI18n()+"."+this.getCombineFieldName();
	}

	public String getFormatter() {
		return formatter;
	}

	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}
	public String getInputId(){
		return fieldName.replace(".", "_");
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}
	
}
