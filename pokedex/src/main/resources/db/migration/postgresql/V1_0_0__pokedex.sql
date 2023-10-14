create table if not exists pokedex.pokemon(
  id uuid primary key,
  name text,
  status text,
  max_weight integer,
  min_weight integer,
  max_height integer,
  min_height integer,
  cp integer,
  hp integer,
  cry bytea
);

create table if not exists pokedex.pokemon_type(
  id uuid primary key,
  pokemon_id uuid,
  form_name text
);

create table if not exists pokedex.pokemon_sprite(
  id uuid primary key,
  pokemon_id uuid,
  sprite_name text,
  sprite_image bytea
);

INSERT INTO pokedex.pokemon (id,name,status, max_weight,min_weight,max_height,min_height,cp,hp,cry)
VALUES ('11ea531d-2001-41bd-9fb8-661aaf7846dc','bulbasaur','REGULAR',1000,900,1000,800,10111,12000,'aG9sYXF1ZRhbA==');

INSERT INTO pokedex.pokemon (id,name,status, max_weight,min_weight,max_height,min_height,cp,hp,cry)
VALUES ('d2d892de-10d4-42dc-be19-16e01f57501a','ivysaur','REGULAR',1001,901,1001,801,10112,12001,'aG9sYXF1ZRhbA==');

INSERT INTO pokedex.pokemon (id,name,status, max_weight,min_weight,max_height,min_height,cp,hp,cry)
VALUES ('5ad9799d-be4c-4446-b1d4-704d2753124c','venusaur','REGULAR',1002,902,1002,802,10113,12002,'aG9sYXF1ZRhbA==');

INSERT INTO pokedex.pokemon (id,name,status, max_weight,min_weight,max_height,min_height,cp,hp,cry)
VALUES ('fd4b802d-42f0-4934-b925-624d21ac7fd1','pikachu','REGULAR',1003,903,1003,803,10114,12003,'aG9sYXF1ZRhbA==');

INSERT INTO pokedex.pokemon_type (id, pokemon_id, form_name)
VALUES ('80f129c0-b7cb-416a-b93c-bfc476277d01','11ea531d-2001-41bd-9fb8-661aaf7846dc','grass');

INSERT INTO pokedex.pokemon_type (id, pokemon_id, form_name)
VALUES ('f853a8f0-4d2b-4eb0-beb7-3b0add41aaa6','11ea531d-2001-41bd-9fb8-661aaf7846dc','poison');

INSERT INTO pokedex.pokemon_type (id, pokemon_id, form_name)
VALUES ('2a868277-85d4-4859-abaf-e86bdfaa34c8','d2d892de-10d4-42dc-be19-16e01f57501a','grass');

INSERT INTO pokedex.pokemon_type (id, pokemon_id, form_name)
VALUES ('7d876da8-7a3d-49eb-8bf0-382991b73b9f','d2d892de-10d4-42dc-be19-16e01f57501a','poison');

INSERT INTO pokedex.pokemon_type (id, pokemon_id, form_name)
VALUES ('168108da-71c6-43bb-9125-b8996fb707a4','5ad9799d-be4c-4446-b1d4-704d2753124c','grass');

INSERT INTO pokedex.pokemon_type (id, pokemon_id, form_name)
VALUES ('b0de2929-f2fb-4904-8738-781e290dcfa6','5ad9799d-be4c-4446-b1d4-704d2753124c','poison');

INSERT INTO pokedex.pokemon_type (id, pokemon_id, form_name)
VALUES ('8685bc95-e7c2-44cb-b67e-de259d550e88','fd4b802d-42f0-4934-b925-624d21ac7fd1','electric');