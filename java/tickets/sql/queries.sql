select DATE_DEPARTURE, DATE_ARRIVAL, a.NAME_CITY as DEPARTURE_CITY, b.NAME_CITY as ARRIVAL_CITY, PRICE_1_CLASS, PRICE_2_CLASS, NAME_COMPANY, MODEL, COUNT_1_CLASS, COUNT_2_CLASS from flights, aircrafts, companies, cities as a, cities as b where flights.id_aircraft=aircrafts.id_aircraft and aircrafts.id_company=companies.id_company and ID_DEPARTURE_CITY=a.ID_CITY and ID_ARRIVAL_CITY=b.ID_CITY

select
  date(a.date_departure) as c1,
  date(a.date_arrival) as c2,
  b.name_city as c3,
  c.name_city as c4,
  a.price_1_class as c5,
  a.price_2_class as c6,
  d.model as c7,
  d.count_1_class as c8,
  d.count_2_class as c9,
  e.name_company as c10
from flights as a join cities as b on a.id_departure_city=b.id_city
                  join cities as c on a.id_arrival_city=c.id_city
                  join aircrafts as d on a.id_aircraft=d.id_aircraft
                  join companies as e on d.id_company=e.id_company
