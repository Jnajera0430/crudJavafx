PGDMP     %    1                {            sena    15.2    15.2                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    16557    sena    DATABASE     z   CREATE DATABASE sena WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Colombia.1252';
    DROP DATABASE sena;
                postgres    false            �            1259    24750    tasks    TABLE     �   CREATE TABLE public.tasks (
    id integer NOT NULL,
    title character varying(50),
    description character varying(500),
    fk_user integer,
    fk_admintask character varying(50),
    state character varying(50)
);
    DROP TABLE public.tasks;
       public         heap    postgres    false            �            1259    24749    tasks_id_seq    SEQUENCE     �   CREATE SEQUENCE public.tasks_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.tasks_id_seq;
       public          postgres    false    217                       0    0    tasks_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.tasks_id_seq OWNED BY public.tasks.id;
          public          postgres    false    216            �            1259    16570    users    TABLE     �   CREATE TABLE public.users (
    name character varying,
    email character varying,
    age integer,
    address character varying,
    id integer NOT NULL
);
    DROP TABLE public.users;
       public         heap    postgres    false            �            1259    16569    users_id_seq    SEQUENCE     �   CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          postgres    false    215            	           0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public          postgres    false    214            k           2604    24753    tasks id    DEFAULT     d   ALTER TABLE ONLY public.tasks ALTER COLUMN id SET DEFAULT nextval('public.tasks_id_seq'::regclass);
 7   ALTER TABLE public.tasks ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    217    217            j           2604    16573    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    214    215    215                      0    24750    tasks 
   TABLE DATA           U   COPY public.tasks (id, title, description, fk_user, fk_admintask, state) FROM stdin;
    public          postgres    false    217   �       �          0    16570    users 
   TABLE DATA           >   COPY public.users (name, email, age, address, id) FROM stdin;
    public          postgres    false    215   �       
           0    0    tasks_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.tasks_id_seq', 7, true);
          public          postgres    false    216                       0    0    users_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.users_id_seq', 12, true);
          public          postgres    false    214            o           2606    24757    tasks tasks_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.tasks
    ADD CONSTRAINT tasks_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.tasks DROP CONSTRAINT tasks_pkey;
       public            postgres    false    217            m           2606    16577    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    215                 x�e�MN�@�דS� �hia�E�"Ħ��;`a&V1G�#����f~��=��,E�I$�BNt����zcg;�~�I��]2�2�n̙�T��`<��a�nS	������T-C���:4�a�Y}=���$ t����[���s�R3�yfn%sX�N�V6��}A��W�i�ե��eIq9������(r�6�a��0t,.�If��}�Ҏ�({���r&Rc �Ժܰ���
������k�4J�b�2���TU��%��      �   �   x�M�1
1��z�9�b��"��j���͐�d`0��3x1EY�}|�o��f��:��!������]�!R)�0�|pGŌMS�b@M3/|�0	�N�Y�/��a���E�����]���霔`N�z�}!��c��/�i7h     