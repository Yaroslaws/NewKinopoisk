# language: ru
@withdrawal
Функция: Поиск фильма по параметрам

  @success
  Сценарий: Заходим на сайт
    Дано пользователь открывает сайт "https://www.kinopoisk.ru/"
    Тогда перехоим в разддел навигатор фильмов
    Тогда ищем комедии снятые в "США" с "1998" по "2000" рейтинг "7" ibm "7" рейтинг критиков "80" положительных рецензий от "90" до "95" Бюджет фильма от "50" до "100" миллионов $. Кассовые сборы от "25" млн $ в США


  @fail
  Сценарий: Тест не прошел