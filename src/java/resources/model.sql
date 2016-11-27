--
-- TOC entry 189 (class 1259 OID 24732)
-- Name: atividade; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE atividade (
    ati_codigo integer NOT NULL,
    ati_descricao character varying(255) NOT NULL,
    ati_dtinicio date NOT NULL,
    ati_dtfim date,
    fun_codigo integer NOT NULL,
    sta_codigo integer NOT NULL,
    sol_email character varying(70) NOT NULL
);


ALTER TABLE atividade OWNER TO postgres;

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

ALTER SEQUENCE "Atividade_ati_codigo_seq" OWNED BY atividade.ati_codigo;


--
-- TOC entry 185 (class 1259 OID 24716)
-- Name: classificacao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE classificacao (
    cla_codigo integer NOT NULL,
    cla_nome character varying(50) NOT NULL,
    cla_ativa boolean NOT NULL
);


ALTER TABLE classificacao OWNER TO postgres;

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

ALTER SEQUENCE "Classificacao_cla_codigo_seq" OWNED BY classificacao.cla_codigo;


--
-- TOC entry 182 (class 1259 OID 24703)
-- Name: status; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE status (
    sta_codigo integer NOT NULL,
    sta_status character varying(20),
    sta_ativo boolean
);


ALTER TABLE status OWNER TO postgres;

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

ALTER SEQUENCE "Status_sta_codigo_seq" OWNED BY status.sta_codigo;


--
-- TOC entry 190 (class 1259 OID 24753)
-- Name: atividadeclassificacao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE atividadeclassificacao (
    ati_codigo integer NOT NULL,
    cla_codigo integer NOT NULL
);


ALTER TABLE atividadeclassificacao OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 24724)
-- Name: funcionario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE funcionario (
    fun_codigo integer NOT NULL,
    fun_nome character varying(70) NOT NULL,
    fun_dtcontratacao date NOT NULL,
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
-- TOC entry 183 (class 1259 OID 24709)
-- Name: solicitante; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE solicitante (
    sol_email character varying(70) NOT NULL,
    sol_nome character varying(70) NOT NULL,
    sol_telfone character varying(20) NOT NULL,
    sol_observacao character varying(255) NOT NULL
);


ALTER TABLE solicitante OWNER TO postgres;

--
-- TOC entry 2050 (class 2604 OID 24735)
-- Name: ati_codigo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY atividade ALTER COLUMN ati_codigo SET DEFAULT nextval('"Atividade_ati_codigo_seq"'::regclass);


--
-- TOC entry 2048 (class 2604 OID 24719)
-- Name: cla_codigo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY classificacao ALTER COLUMN cla_codigo SET DEFAULT nextval('"Classificacao_cla_codigo_seq"'::regclass);


--
-- TOC entry 2049 (class 2604 OID 24727)
-- Name: fun_codigo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY funcionario ALTER COLUMN fun_codigo SET DEFAULT nextval('funcionario_fun_codigo_seq'::regclass);


--
-- TOC entry 2047 (class 2604 OID 24706)
-- Name: sta_codigo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY status ALTER COLUMN sta_codigo SET DEFAULT nextval('"Status_sta_codigo_seq"'::regclass);


--
-- TOC entry 2204 (class 0 OID 0)
-- Dependencies: 188
-- Name: Atividade_ati_codigo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"Atividade_ati_codigo_seq"', 1, false);


--
-- TOC entry 2205 (class 0 OID 0)
-- Dependencies: 184
-- Name: Classificacao_cla_codigo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"Classificacao_cla_codigo_seq"', 5, true);


--
-- TOC entry 2206 (class 0 OID 0)
-- Dependencies: 181
-- Name: Status_sta_codigo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"Status_sta_codigo_seq"', 1, false);

--
-- TOC entry 2062 (class 2606 OID 24757)
-- Name: AtividadeClassificacao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY atividadeclassificacao
    ADD CONSTRAINT "AtividadeClassificacao_pkey" PRIMARY KEY (ati_codigo, cla_codigo);


--
-- TOC entry 2060 (class 2606 OID 24737)
-- Name: atividade_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY atividade
    ADD CONSTRAINT atividade_pk PRIMARY KEY (ati_codigo);


--
-- TOC entry 2056 (class 2606 OID 24721)
-- Name: classificacao_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY classificacao
    ADD CONSTRAINT classificacao_pk PRIMARY KEY (cla_codigo);


--
-- TOC entry 2058 (class 2606 OID 24729)
-- Name: funcionario_ppk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT funcionario_ppk PRIMARY KEY (fun_codigo);


--
-- TOC entry 2054 (class 2606 OID 24713)
-- Name: solicitante_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY solicitante
    ADD CONSTRAINT solicitante_pk PRIMARY KEY (sol_email);


--
-- TOC entry 2052 (class 2606 OID 24708)
-- Name: status_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY status
    ADD CONSTRAINT status_pk PRIMARY KEY (sta_codigo);


--
-- TOC entry 2066 (class 2606 OID 24758)
-- Name: AtividadeClassificacao_ati_codigo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY atividadeclassificacao
    ADD CONSTRAINT "AtividadeClassificacao_ati_codigo_fkey" FOREIGN KEY (ati_codigo) REFERENCES atividade(ati_codigo);


--
-- TOC entry 2067 (class 2606 OID 24763)
-- Name: AtividadeClassificacao_cla_codigo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY atividadeclassificacao
    ADD CONSTRAINT "AtividadeClassificacao_cla_codigo_fkey" FOREIGN KEY (cla_codigo) REFERENCES classificacao(cla_codigo);


--
-- TOC entry 2063 (class 2606 OID 24738)
-- Name: atividade_funcionario_pk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY atividade
    ADD CONSTRAINT atividade_funcionario_pk FOREIGN KEY (fun_codigo) REFERENCES funcionario(fun_codigo);


--
-- TOC entry 2065 (class 2606 OID 24748)
-- Name: atividade_solicitante_pk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY atividade
    ADD CONSTRAINT atividade_solicitante_pk FOREIGN KEY (sol_email) REFERENCES solicitante(sol_email);


--
-- TOC entry 2064 (class 2606 OID 24743)
-- Name: atividade_status_pk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY atividade
    ADD CONSTRAINT atividade_status_pk FOREIGN KEY (sta_codigo) REFERENCES status(sta_codigo);