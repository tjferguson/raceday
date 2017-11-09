--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.14
-- Dumped by pg_dump version 9.4.14
-- Started on 2017-11-09 02:15:58

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'SQL_ASCII';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 1 (class 3079 OID 11855)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2063 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 182 (class 1259 OID 24910)
-- Name: bracket; Type: TABLE; Schema: public; Owner: raceday; Tablespace: 
--

CREATE TABLE bracket (
    bracket_id integer NOT NULL,
    round_id integer NOT NULL,
    vech1_id integer NOT NULL,
    vech2_id integer,
    winner integer,
    prev_bracket integer
);


ALTER TABLE bracket OWNER TO raceday;

--
-- TOC entry 181 (class 1259 OID 24908)
-- Name: bracket_bracket_id_seq; Type: SEQUENCE; Schema: public; Owner: raceday
--

CREATE SEQUENCE bracket_bracket_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE bracket_bracket_id_seq OWNER TO raceday;

--
-- TOC entry 2064 (class 0 OID 0)
-- Dependencies: 181
-- Name: bracket_bracket_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: raceday
--

ALTER SEQUENCE bracket_bracket_id_seq OWNED BY bracket.bracket_id;


--
-- TOC entry 176 (class 1259 OID 24872)
-- Name: participant; Type: TABLE; Schema: public; Owner: raceday; Tablespace: 
--

CREATE TABLE participant (
    participant_id bigint NOT NULL,
    name character varying(255) NOT NULL,
    race_id integer NOT NULL
);


ALTER TABLE participant OWNER TO raceday;

--
-- TOC entry 175 (class 1259 OID 24870)
-- Name: participant_participant_id_seq; Type: SEQUENCE; Schema: public; Owner: raceday
--

CREATE SEQUENCE participant_participant_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE participant_participant_id_seq OWNER TO raceday;

--
-- TOC entry 2065 (class 0 OID 0)
-- Dependencies: 175
-- Name: participant_participant_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: raceday
--

ALTER SEQUENCE participant_participant_id_seq OWNED BY participant.participant_id;


--
-- TOC entry 174 (class 1259 OID 24864)
-- Name: race; Type: TABLE; Schema: public; Owner: raceday; Tablespace: 
--

CREATE TABLE race (
    race_id integer NOT NULL,
    race_name character varying(255) NOT NULL
);


ALTER TABLE race OWNER TO raceday;

--
-- TOC entry 173 (class 1259 OID 24862)
-- Name: race_race_id_seq; Type: SEQUENCE; Schema: public; Owner: raceday
--

CREATE SEQUENCE race_race_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE race_race_id_seq OWNER TO raceday;

--
-- TOC entry 2066 (class 0 OID 0)
-- Dependencies: 173
-- Name: race_race_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: raceday
--

ALTER SEQUENCE race_race_id_seq OWNED BY race.race_id;


--
-- TOC entry 180 (class 1259 OID 24896)
-- Name: round; Type: TABLE; Schema: public; Owner: raceday; Tablespace: 
--

CREATE TABLE round (
    round_id integer NOT NULL,
    race_id integer NOT NULL,
    round_num integer NOT NULL,
    completed boolean DEFAULT false NOT NULL
);


ALTER TABLE round OWNER TO raceday;

--
-- TOC entry 179 (class 1259 OID 24894)
-- Name: round_round_id_seq; Type: SEQUENCE; Schema: public; Owner: raceday
--

CREATE SEQUENCE round_round_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE round_round_id_seq OWNER TO raceday;

--
-- TOC entry 2067 (class 0 OID 0)
-- Dependencies: 179
-- Name: round_round_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: raceday
--

ALTER SEQUENCE round_round_id_seq OWNED BY round.round_id;


--
-- TOC entry 183 (class 1259 OID 24936)
-- Name: users; Type: TABLE; Schema: public; Owner: raceday; Tablespace: 
--

CREATE TABLE users (
    username character varying(25) NOT NULL,
    password_hash character varying(255) NOT NULL,
    active boolean DEFAULT true NOT NULL
);


ALTER TABLE users OWNER TO raceday;

--
-- TOC entry 178 (class 1259 OID 24885)
-- Name: vechicle; Type: TABLE; Schema: public; Owner: raceday; Tablespace: 
--

CREATE TABLE vechicle (
    vechicle_id integer NOT NULL,
    participant_id integer NOT NULL,
    tag character varying
);


ALTER TABLE vechicle OWNER TO raceday;

--
-- TOC entry 177 (class 1259 OID 24883)
-- Name: vechicle_vechicle_id_seq; Type: SEQUENCE; Schema: public; Owner: raceday
--

CREATE SEQUENCE vechicle_vechicle_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE vechicle_vechicle_id_seq OWNER TO raceday;

--
-- TOC entry 2068 (class 0 OID 0)
-- Dependencies: 177
-- Name: vechicle_vechicle_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: raceday
--

ALTER SEQUENCE vechicle_vechicle_id_seq OWNED BY vechicle.vechicle_id;


--
-- TOC entry 1915 (class 2604 OID 24913)
-- Name: bracket_id; Type: DEFAULT; Schema: public; Owner: raceday
--

ALTER TABLE ONLY bracket ALTER COLUMN bracket_id SET DEFAULT nextval('bracket_bracket_id_seq'::regclass);


--
-- TOC entry 1911 (class 2604 OID 24875)
-- Name: participant_id; Type: DEFAULT; Schema: public; Owner: raceday
--

ALTER TABLE ONLY participant ALTER COLUMN participant_id SET DEFAULT nextval('participant_participant_id_seq'::regclass);


--
-- TOC entry 1910 (class 2604 OID 24867)
-- Name: race_id; Type: DEFAULT; Schema: public; Owner: raceday
--

ALTER TABLE ONLY race ALTER COLUMN race_id SET DEFAULT nextval('race_race_id_seq'::regclass);


--
-- TOC entry 1913 (class 2604 OID 24899)
-- Name: round_id; Type: DEFAULT; Schema: public; Owner: raceday
--

ALTER TABLE ONLY round ALTER COLUMN round_id SET DEFAULT nextval('round_round_id_seq'::regclass);


--
-- TOC entry 1912 (class 2604 OID 24888)
-- Name: vechicle_id; Type: DEFAULT; Schema: public; Owner: raceday
--

ALTER TABLE ONLY vechicle ALTER COLUMN vechicle_id SET DEFAULT nextval('vechicle_vechicle_id_seq'::regclass);


--
-- TOC entry 2054 (class 0 OID 24910)
-- Dependencies: 182
-- Data for Name: bracket; Type: TABLE DATA; Schema: public; Owner: raceday
--

COPY bracket (bracket_id, round_id, vech1_id, vech2_id, winner, prev_bracket) FROM stdin;
\.


--
-- TOC entry 2069 (class 0 OID 0)
-- Dependencies: 181
-- Name: bracket_bracket_id_seq; Type: SEQUENCE SET; Schema: public; Owner: raceday
--

SELECT pg_catalog.setval('bracket_bracket_id_seq', 1, false);


--
-- TOC entry 2048 (class 0 OID 24872)
-- Dependencies: 176
-- Data for Name: participant; Type: TABLE DATA; Schema: public; Owner: raceday
--

COPY participant (participant_id, name, race_id) FROM stdin;
\.


--
-- TOC entry 2070 (class 0 OID 0)
-- Dependencies: 175
-- Name: participant_participant_id_seq; Type: SEQUENCE SET; Schema: public; Owner: raceday
--

SELECT pg_catalog.setval('participant_participant_id_seq', 1, false);


--
-- TOC entry 2046 (class 0 OID 24864)
-- Dependencies: 174
-- Data for Name: race; Type: TABLE DATA; Schema: public; Owner: raceday
--

COPY race (race_id, race_name) FROM stdin;
\.


--
-- TOC entry 2071 (class 0 OID 0)
-- Dependencies: 173
-- Name: race_race_id_seq; Type: SEQUENCE SET; Schema: public; Owner: raceday
--

SELECT pg_catalog.setval('race_race_id_seq', 1, false);


--
-- TOC entry 2052 (class 0 OID 24896)
-- Dependencies: 180
-- Data for Name: round; Type: TABLE DATA; Schema: public; Owner: raceday
--

COPY round (round_id, race_id, round_num, completed) FROM stdin;
\.


--
-- TOC entry 2072 (class 0 OID 0)
-- Dependencies: 179
-- Name: round_round_id_seq; Type: SEQUENCE SET; Schema: public; Owner: raceday
--

SELECT pg_catalog.setval('round_round_id_seq', 1, false);


--
-- TOC entry 2055 (class 0 OID 24936)
-- Dependencies: 183
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: raceday
--

COPY users (username, password_hash, active) FROM stdin;
\.


--
-- TOC entry 2050 (class 0 OID 24885)
-- Dependencies: 178
-- Data for Name: vechicle; Type: TABLE DATA; Schema: public; Owner: raceday
--

COPY vechicle (vechicle_id, participant_id, tag) FROM stdin;
\.


--
-- TOC entry 2073 (class 0 OID 0)
-- Dependencies: 177
-- Name: vechicle_vechicle_id_seq; Type: SEQUENCE SET; Schema: public; Owner: raceday
--

SELECT pg_catalog.setval('vechicle_vechicle_id_seq', 1, false);


--
-- TOC entry 1926 (class 2606 OID 24915)
-- Name: bracket_pkey; Type: CONSTRAINT; Schema: public; Owner: raceday; Tablespace: 
--

ALTER TABLE ONLY bracket
    ADD CONSTRAINT bracket_pkey PRIMARY KEY (bracket_id);


--
-- TOC entry 1920 (class 2606 OID 24877)
-- Name: pk_pid; Type: CONSTRAINT; Schema: public; Owner: raceday; Tablespace: 
--

ALTER TABLE ONLY participant
    ADD CONSTRAINT pk_pid PRIMARY KEY (participant_id);


--
-- TOC entry 1918 (class 2606 OID 24869)
-- Name: race_pkey; Type: CONSTRAINT; Schema: public; Owner: raceday; Tablespace: 
--

ALTER TABLE ONLY race
    ADD CONSTRAINT race_pkey PRIMARY KEY (race_id);


--
-- TOC entry 1924 (class 2606 OID 24902)
-- Name: round_pkey; Type: CONSTRAINT; Schema: public; Owner: raceday; Tablespace: 
--

ALTER TABLE ONLY round
    ADD CONSTRAINT round_pkey PRIMARY KEY (round_id);


--
-- TOC entry 1928 (class 2606 OID 24941)
-- Name: users_pkey; Type: CONSTRAINT; Schema: public; Owner: raceday; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (username);


--
-- TOC entry 1922 (class 2606 OID 24890)
-- Name: vechicle_pkey; Type: CONSTRAINT; Schema: public; Owner: raceday; Tablespace: 
--

ALTER TABLE ONLY vechicle
    ADD CONSTRAINT vechicle_pkey PRIMARY KEY (vechicle_id);


--
-- TOC entry 1935 (class 2606 OID 24942)
-- Name: fk_prev_bracket; Type: FK CONSTRAINT; Schema: public; Owner: raceday
--

ALTER TABLE ONLY bracket
    ADD CONSTRAINT fk_prev_bracket FOREIGN KEY (prev_bracket) REFERENCES bracket(bracket_id);


--
-- TOC entry 1929 (class 2606 OID 24878)
-- Name: fk_race; Type: FK CONSTRAINT; Schema: public; Owner: raceday
--

ALTER TABLE ONLY participant
    ADD CONSTRAINT fk_race FOREIGN KEY (race_id) REFERENCES race(race_id);


--
-- TOC entry 1930 (class 2606 OID 24903)
-- Name: fk_race; Type: FK CONSTRAINT; Schema: public; Owner: raceday
--

ALTER TABLE ONLY round
    ADD CONSTRAINT fk_race FOREIGN KEY (round_id) REFERENCES race(race_id);


--
-- TOC entry 1931 (class 2606 OID 24916)
-- Name: fk_round; Type: FK CONSTRAINT; Schema: public; Owner: raceday
--

ALTER TABLE ONLY bracket
    ADD CONSTRAINT fk_round FOREIGN KEY (round_id) REFERENCES round(round_id);


--
-- TOC entry 1932 (class 2606 OID 24921)
-- Name: fk_vech1; Type: FK CONSTRAINT; Schema: public; Owner: raceday
--

ALTER TABLE ONLY bracket
    ADD CONSTRAINT fk_vech1 FOREIGN KEY (vech1_id) REFERENCES vechicle(vechicle_id);


--
-- TOC entry 1933 (class 2606 OID 24926)
-- Name: fk_vech2; Type: FK CONSTRAINT; Schema: public; Owner: raceday
--

ALTER TABLE ONLY bracket
    ADD CONSTRAINT fk_vech2 FOREIGN KEY (vech2_id) REFERENCES vechicle(vechicle_id);


--
-- TOC entry 1934 (class 2606 OID 24931)
-- Name: fk_winner; Type: FK CONSTRAINT; Schema: public; Owner: raceday
--

ALTER TABLE ONLY bracket
    ADD CONSTRAINT fk_winner FOREIGN KEY (winner) REFERENCES vechicle(vechicle_id);


--
-- TOC entry 2062 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2017-11-09 02:15:59

--
-- PostgreSQL database dump complete
--

