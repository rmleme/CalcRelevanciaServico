/*
DROP SEQUENCE ipt.PartnerLinkSeq;
DROP SEQUENCE ipt.ProcessoNegocioSeq;
DROP TABLE ipt.Multidigrafo;
DROP TABLE ipt.PartnerLink;
DROP TABLE ipt.ProcessoNegocio;
*/

CREATE TABLE ipt.ProcessoNegocio (
  id NUMBER(15) NOT NULL,
  nome VARCHAR2(64) NOT NULL,
  conteudo_bpel CLOB NOT NULL,
  url VARCHAR2(256) NOT NULL,
  CONSTRAINT ProcessoNegocio_PK PRIMARY KEY (id)
);

CREATE TABLE ipt.PartnerLink (
  id NUMBER(15) NOT NULL,
  id_processo_negocio NUMBER(15) NOT NULL,
  nome VARCHAR2(64) NOT NULL,
  conteudo_wsdl CLOB NOT NULL,
  url_servico VARCHAR2(256) NOT NULL,
  operacoes VARCHAR2(1024) NOT NULL,
  CONSTRAINT PartnerLink_PK PRIMARY KEY (id),
  CONSTRAINT PartnerLink_FK1 FOREIGN KEY (id_processo_negocio) REFERENCES ipt.ProcessoNegocio(id)
);

CREATE TABLE ipt.Multidigrafo (
  conteudo_serializado BLOB NOT NULL
);

CREATE SEQUENCE ipt.ProcessoNegocioSeq NOCACHE;

CREATE SEQUENCE ipt.PartnerLinkSeq NOCACHE;