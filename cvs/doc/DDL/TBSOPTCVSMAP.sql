DROP TABLE SOPAADMIN.TBSOPTCVSMAP CASCADE CONSTRAINTS;

CREATE TABLE "SOPAADMIN"."TBSOPTCVSMAP"
  (
    "M_SID"        NUMBER(10,0) NOT NULL,
    "RCSFILE"      VARCHAR2(500 BYTE) NOT NULL,
    "FILENAME"     VARCHAR2(100 BYTE) NOT NULL,
    "PROGRAMID"    VARCHAR2(50 BYTE),
    "MODULE"       VARCHAR2(10 BYTE),
    "CLIENTSERVER" CHAR(1 BYTE),
    "VERSIONHEAD"  VARCHAR2(20 BYTE) NOT NULL,
    CONSTRAINT "TBSOPTCVSMAP_PK" PRIMARY KEY ("M_SID") USING INDEX TABLESPACE "SOPA_INDEX",
    CONSTRAINT "TBSOPTCVSMAP_UK" UNIQUE ("RCSFILE") USING INDEX TABLESPACE "SOPA_INDEX"
  ) TABLESPACE "SOPA_DATA";