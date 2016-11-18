--
-- TOC entry 2199 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 189 (class 1259 OID 24732)
-- Name: Atividade; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "Atividade" (
    ati_codigo integer NOT NULL,
    ati_descricao character varying(255) NOT NULL,
    ati_dtinicio date NOT NULL,
    ati_dtfim date,
    fun_codigo integer NOT NULL,
    sta_codigo integer NOT NULL,
    sol_email character varying(70) NOT NULL
);


ALTER TABLE "Atividade" OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 24753)
-- Name: AtividadeClassificacao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "AtividadeClassificacao" (
    ati_codigo integer NOT NULL,
    cla_codigo integer NOT NULL
);


ALTER TABLE "AtividadeClassificacao" OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 24730)
-- Name: Atividade_ati_codigo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "Atividade_ati_codigo_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "Atividade_ati_codigo_seq" OWNER TO postgres;

--
-- TOC entry 2200 (class 0 OID 0)
-- Dependencies: 188
-- Name: Atividade_ati_codigo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "Atividade_ati_codigo_seq" OWNED BY "Atividade".ati_codigo;


--
-- TOC entry 185 (class 1259 OID 24716)
-- Name: Classificacao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "Classificacao" (
    cla_codigo integer NOT NULL,
    cla_nome character varying(50) NOT NULL,
    cla_ativa boolean NOT NULL
);


ALTER TABLE "Classificacao" OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 24714)
-- Name: Classificacao_cla_codigo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "Classificacao_cla_codigo_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "Classificacao_cla_codigo_seq" OWNER TO postgres;

--
-- TOC entry 2201 (class 0 OID 0)
-- Dependencies: 184
-- Name: Classificacao_cla_codigo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "Classificacao_cla_codigo_seq" OWNED BY "Classificacao".cla_codigo;


--
-- TOC entry 183 (class 1259 OID 24709)
-- Name: Solicitante; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "Solicitante" (
    sol_email character varying(70) NOT NULL,
    sol_nome character varying(70) NOT NULL,
    sol_telfone character varying(20) NOT NULL,
    sol_observacao character varying(255) NOT NULL
);


ALTER TABLE "Solicitante" OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 24703)
-- Name: Status; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "Status" (
    sta_codigo integer NOT NULL,
    sta_status character varying(20),
    sta_ativo boolean
);


ALTER TABLE "Status" OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 24701)
-- Name: Status_sta_codigo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "Status_sta_codigo_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "Status_sta_codigo_seq" OWNER TO postgres;

--
-- TOC entry 2202 (class 0 OID 0)
-- Dependencies: 181
-- Name: Status_sta_codigo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "Status_sta_codigo_seq" OWNED BY "Status".sta_codigo;


--
-- TOC entry 187 (class 1259 OID 24724)
-- Name: funcionario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE funcionario (
    fun_codigo integer NOT NULL,
    fun_nome character varying(70) NOT NULL,
    fun_dtcontracao date NOT NULL,
    fun_dtdemissao date,
    fun_ativo boolean NOT NULL,
    fun_senha character varying(15) NOT NULL,
    fun_tipo character varying(1) NOT NULL
);


ALTER TABLE funcionario OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 24722)
-- Name: funcionario_fun_codigo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE funcionario_fun_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE funcionario_fun_codigo_seq OWNER TO postgres;

--
-- TOC entry 2203 (class 0 OID 0)
-- Dependencies: 186
-- Name: funcionario_fun_codigo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE funcionario_fun_codigo_seq OWNED BY funcionario.fun_codigo;


--
-- TOC entry 2050 (class 2604 OID 24735)
-- Name: ati_codigo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "Atividade" ALTER COLUMN ati_codigo SET DEFAULT nextval('"Atividade_ati_codigo_seq"'::regclass);


--
-- TOC entry 2048 (class 2604 OID 24719)
-- Name: cla_codigo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "Classificacao" ALTER COLUMN cla_codigo SET DEFAULT nextval('"Classificacao_cla_codigo_seq"'::regclass);


--
-- TOC entry 2047 (class 2604 OID 24706)
-- Name: sta_codigo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "Status" ALTER COLUMN sta_codigo SET DEFAULT nextval('"Status_sta_codigo_seq"'::regclass);


--
-- TOC entry 2049 (class 2604 OID 24727)
-- Name: fun_codigo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY funcionario ALTER COLUMN fun_codigo SET DEFAULT nextval('funcionario_fun_codigo_seq'::regclass);


--
-- TOC entry 2190 (class 0 OID 24732)
-- Dependencies: 189
-- Data for Name: Atividade; Type: TABLE DATA; Schema: public; Owner: postgres
--