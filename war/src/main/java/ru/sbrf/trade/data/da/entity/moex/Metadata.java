package ru.sbrf.trade.data.da.entity.moex;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Metadata{

    @JsonProperty("BOARDID")
    public BOARDID boardid = new BOARDID();
    @JsonProperty("TRADEDATE")
    public TRADEDATE tradedate = new TRADEDATE();
    @JsonProperty("SHORTNAME")
    public SHORTNAME sHORTNAME = new SHORTNAME();
    @JsonProperty("SECID")
    public SECID sECID = new SECID();
    @JsonProperty("NUMTRADES")
    public NUMTRADES nUMTRADES = new NUMTRADES();
    @JsonProperty("VALUE")
    public VALUE vALUE = new VALUE();
    @JsonProperty("OPEN")
    public OPEN oPEN = new OPEN();
    @JsonProperty("LOW")
    public LOW lOW = new LOW();
    @JsonProperty("HIGH")
    public HIGH hIGH = new HIGH();
    @JsonProperty("LEGALCLOSEPRICE")
    public LEGALCLOSEPRICE lEGALCLOSEPRICE = new LEGALCLOSEPRICE();
    @JsonProperty("WAPRICE")
    public WAPRICE wAPRICE = new WAPRICE();
    @JsonProperty("CLOSE")
    public CLOSE cLOSE = new CLOSE();
    @JsonProperty("VOLUME")
    public VOLUME vOLUME = new VOLUME();
    @JsonProperty("MARKETPRICE2")
    public MARKETPRICE2 mARKETPRICE2 = new MARKETPRICE2();
    @JsonProperty("MARKETPRICE3")
    public MARKETPRICE3 mARKETPRICE3 = new MARKETPRICE3();
    @JsonProperty("ADMITTEDQUOTE")
    public ADMITTEDQUOTE aDMITTEDQUOTE = new ADMITTEDQUOTE();
    @JsonProperty("MP2VALTRD")
    public MP2VALTRD mP2VALTRD = new MP2VALTRD();
    @JsonProperty("MARKETPRICE3TRADESVALUE")
    public MARKETPRICE3TRADESVALUE mARKETPRICE3TRADESVALUE = new MARKETPRICE3TRADESVALUE();
    @JsonProperty("ADMITTEDVALUE")
    public ADMITTEDVALUE aDMITTEDVALUE = new ADMITTEDVALUE();
    @JsonProperty("WAVAL")
    public WAVAL wAVAL = new WAVAL();
    @JsonProperty("TRADINGSESSION")
    public TRADINGSESSION tRADINGSESSION = new TRADINGSESSION();
    @JsonProperty("INDEX")
    public INDEX iNDEX = new INDEX();
    @JsonProperty("TOTAL")
    public TOTAL tOTAL = new TOTAL();
    @JsonProperty("PAGESIZE")
    public PAGESIZE pAGESIZE = new PAGESIZE();
}
