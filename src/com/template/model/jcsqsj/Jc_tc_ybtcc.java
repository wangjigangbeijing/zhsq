package com.template.model.jcsqsj;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "JC_TC_YBTCC")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Jc_tc_ybtcc implements java.io.Serializable {
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
@Column(name = "created_at", nullable = true)
public Date created_at;
public Date getcreated_at()
{
	return created_at;
}
public void setcreated_at(Date created_at)
{
	this.created_at = created_at;
}

@Column(name = "parkid", nullable = true)
public String parkID;
public String getparkID()
{
	return parkID;
}
public void setparkID(String parkID)
{
	this.parkID = parkID;
}
@Column(name = "parkname", nullable = true)
public String parkName;
public String getparkName()
{
	return parkName;
}
public void setparkName(String parkName)
{
	this.parkName = parkName;
}
@Column(name = "tradename", nullable = true)
public String tradeName;
public String gettradeName()
{
	return tradeName;
}
public void settradeName(String tradeName)
{
	this.tradeName = tradeName;
}
@Column(name = "jztype", nullable = true)
public String jztype;
public String getjztype()
{
	return jztype;
}
public void setjztype(String jztype)
{
	this.jztype = jztype;
}
@Column(name = "unitname", nullable = true)
public String unitName;
public String getunitName()
{
	return unitName;
}
public void setunitName(String unitName)
{
	this.unitName = unitName;
}
@Column(name = "unitaddres", nullable = true)
public String unitAddres;
public String getunitAddres()
{
	return unitAddres;
}
public void setunitAddres(String unitAddres)
{
	this.unitAddres = unitAddres;
}
@Column(name = "area", nullable = true)
public String area;
public String getarea()
{
	return area;
}
public void setarea(String area)
{
	this.area = area;
}
@Column(name = "admindep", nullable = true)
public String adminDep;
public String getadminDep()
{
	return adminDep;
}
public void setadminDep(String adminDep)
{
	this.adminDep = adminDep;
}
@Column(name = "ownerdep", nullable = true)
public String ownerDep;
public String getownerDep()
{
	return ownerDep;
}
public void setownerDep(String ownerDep)
{
	this.ownerDep = ownerDep;
}
@Column(name = "maintdep", nullable = true)
public String maintDep;
public String getmaintDep()
{
	return maintDep;
}
public void setmaintDep(String maintDep)
{
	this.maintDep = maintDep;
}
@Column(name = "berthnum", nullable = true)
public Integer berthNum;
public Integer getberthNum()
{
	return berthNum;
}
public void setberthNum(Integer berthNum)
{
	this.berthNum = berthNum;
}
@Column(name = "unberthnum", nullable = true)
public Integer UnberthNum;
public Integer getUnberthNum()
{
	return UnberthNum;
}
public void setUnberthNum(Integer UnberthNum)
{
	this.UnberthNum = UnberthNum;
}
@Column(name = "grberthnum", nullable = true)
public Integer GrberthNum;
public Integer getGrberthNum()
{
	return GrberthNum;
}
public void setGrberthNum(Integer GrberthNum)
{
	this.GrberthNum = GrberthNum;
}
@Column(name = "parkingnum", nullable = true)
public Integer ParkingNum;
public Integer getParkingNum()
{
	return ParkingNum;
}
public void setParkingNum(Integer ParkingNum)
{
	this.ParkingNum = ParkingNum;
}
@Column(name = "opennum", nullable = true)
public Integer openNum;
public Integer getopenNum()
{
	return openNum;
}
public void setopenNum(Integer openNum)
{
	this.openNum = openNum;
}
@Column(name = "parktime", nullable = true)
public String parkTime;
public String getparkTime()
{
	return parkTime;
}
public void setparkTime(String parkTime)
{
	this.parkTime = parkTime;
}
@Column(name = "chpilenum", nullable = true)
public Integer chpileNum;
public Integer getchpileNum()
{
	return chpileNum;
}
public void setchpileNum(Integer chpileNum)
{
	this.chpileNum = chpileNum;
}
@Column(name = "bafreenum", nullable = true)
public Integer BaFreeNum;
public Integer getBaFreeNum()
{
	return BaFreeNum;
}
public void setBaFreeNum(Integer BaFreeNum)
{
	this.BaFreeNum = BaFreeNum;
}
@Column(name = "mecnum", nullable = true)
public Integer MecNum;
public Integer getMecNum()
{
	return MecNum;
}
public void setMecNum(Integer MecNum)
{
	this.MecNum = MecNum;
}
@Column(name = "chargetype", nullable = true)
public String Chargetype;
public String getChargetype()
{
	return Chargetype;
}
public void setChargetype(String Chargetype)
{
	this.Chargetype = Chargetype;
}
@Column(name = "lolechtype", nullable = true)
public String LoLeChtype;
public String getLoLeChtype()
{
	return LoLeChtype;
}
public void setLoLeChtype(String LoLeChtype)
{
	this.LoLeChtype = LoLeChtype;
}
@Column(name = "shlechtype", nullable = true)
public String ShLeChtype;
public String getShLeChtype()
{
	return ShLeChtype;
}
public void setShLeChtype(String ShLeChtype)
{
	this.ShLeChtype = ShLeChtype;
}
@Column(name = "dydata", nullable = true)
public String DyData;
public String getDyData()
{
	return DyData;
}
public void setDyData(String DyData)
{
	this.DyData = DyData;
}
@Column(name = "picture", nullable = true)
public String picture;
public String getpicture()
{
	return picture;
}
public void setpicture(String picture)
{
	this.picture = picture;
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
}

