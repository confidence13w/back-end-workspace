-- sakila, 정렬은 결과 화면대로

-- 1. actor 테이블에서 first_name이 A로 시작하는 배우들만 조회 ##완료##
SELECT actor_id, first_name, last_name, last_update
FROM actor
WHERE substr(first_name, 1, 1) = 'A';

-- 2. film_list 테이블에서 category가 Sci-Fi 또는 Family면서
-- rating이 PG면서 영화 제목(title)에 GO가 포함되는 영화 제목 조회 ##완료##
SELECT title
FROM film_list
WHERE category IN ('Sci-Fi', 'Family')
      AND rating = 'PG'
      AND title LIKE '%GO%';

-- 3. film_list 테이블에서 fid가 7 이하면서 4, 6은 아닌 fid, title 조회 ##완료##
SELECT fid, title
FROM film_list
WHERE fid < 7 and fid != 4 and fid != 6;

-- 4. film_list 테이블에서 가격(price)은 2 이상 4 이하이면서 category가 Documentary거나 Animation이고 
-- 배우들(actors) 중 BOB가 포함되어 있는 영화 제목(title)만 조회 ##완료##
SELECT title
FROM film_list
WHERE 2 <= price and price <= 4
	  AND category IN ('Documentary', 'Animation')
      AND actors LIKE '%BOB%';

-- 5. address 테이블에서 district가 비어있지 않고 앞에 숫자 제외 주소만 10개 조회
SELECT address, district ' '
FROM address
WHERE district IS NOT NULL
ORDER BY district;

-- 6. customer_list 테이블에서 id가 6인 사람부터 10명 조회 ##완료##
SELECT id, name
FROM customer_list
WHERE id >= 6 and id <= 15;

-- 7. actor 테이블에서 J로 시작하는 이름과 글자수 조회 (공백 X, 정렬은 글자수가 많은 사람 순으로) ##완료##
SELECT concat(first_name, ' ', last_name) 이름,
       char_length(concat(first_name, last_name))
FROM actor
WHERE first_name LIKE 'J%'
ORDER BY char_length(concat(first_name, last_name)) DESC;

-- 8. film 테이블에서 description에서 of 이전 문장만 중복 없이 10개만 추출해서 조회
SELECT DISTINCT description
FROM film
WHERE description LIKE 'A Unbelieveable%';

-- 9. film 테이블에서 replacement_cost 최소 비용과 최대 비용 조회 ##완료##
SELECT 
	min(replacement_cost),
    max(replacement_cost)
FROM film;