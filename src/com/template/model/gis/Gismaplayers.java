package com.template.model.gis;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "GISMAPLAYERS")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Gismaplayers implements java.io.Serializable {
	
	@Id
	@Column(name = "id", nullable = false)
	public String id;
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	
	@Column(name = "layerid", nullable = true)
	public String layerid;
	public String getlayerid()
	{
		return layerid;
	}
	public void setlayerid(String layerid)
	{
		this.layerid = layerid;
	}
	
	@Column(name = "mapid", nullable = true)
	public String mapid;
	public String getmapid()
	{
		return mapid;
	}
	public void setmapid(String mapid)
	{
		this.mapid = mapid;
	}
	
	@Column(name = "layersource", nullable = true)
	public String layersource;
	public String getlayersource()
	{
		return layersource;
	}
	public void setlayersource(String layersource)
	{
		this.layersource = layersource;
	}
	
	@Column(name = "layerstyle", nullable = true)
	public String layerstyle;
	public String getlayerstyle()
	{
		return layerstyle;
	}
	public void setlayerstyle(String layerstyle)
	{
		this.layerstyle = layerstyle;
	}
	
	@Column(name = "labelfields", nullable = true)
	public String labelfields;
	public String getlabelfields()
	{
		return labelfields;
	}
	public void setlabelfields(String labelfields)
	{
		this.labelfields = labelfields;
	}
	
	@Column(name = "infofields", nullable = true)
	public String infofields;
	public String getinfofields()
	{
		return infofields;
	}
	public void setinfofields(String infofields)
	{
		this.infofields = infofields;
	}
	
	@Column(name = "queryfields", nullable = true)
	public String queryfields;
	public String getqueryfields()
	{
		return queryfields;
	}
	public void setqueryfields(String queryfields)
	{
		this.queryfields = queryfields;
	}
	
	@Column(name = "note", nullable = true)
	public String note;
	public String getnote()
	{
		return note;
	}
	public void setnote(String note)
	{
		this.note = note;
	}
	

	@Column(name = "lorder", nullable = true)
	public String lorder;
	public String getlorder()
	{
		return lorder;
	}
	public void setlorder(String lorder)
	{
		this.lorder = lorder;
	}
	

	@Column(name = "layertype", nullable = true)
	public String layertype;
	public String getlayertype()
	{
		return layertype;
	}
	public void setlayertype(String layertype)
	{
		this.layertype = layertype;
	}
	
	@Column(name = "defaultchecked", nullable = true)
	public String defaultchecked;
	public String getdefaultchecked() {
		return defaultchecked;
	}
	public void setdefaultchecked(String defaultchecked) {
		this.defaultchecked = defaultchecked;
	}
	
	@Column(name = "showzooms", nullable = true)
	public String showzooms;
	public String getshowzooms() {
		return this.showzooms;
	}
	public void setshowzooms(String showzoom) {
		this.showzooms = showzoom;
	}
	
}

